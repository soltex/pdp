/**
 * 
 */
package cn.com.innodev.pdp.framework.syslog.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.framework.syslog.BasicSearchBean;
import cn.com.innodev.pdp.framework.syslog.GlobalLogType;
import cn.com.innodev.pdp.framework.syslog.LogLevel;
import cn.com.innodev.pdp.framework.syslog.LogService;
import cn.com.innodev.pdp.framework.syslog.SearchLogBean;
import cn.com.innodev.pdp.framework.syslog.SystemLog;
import cn.com.innodev.pdp.framework.systask.SysTaskManager;

import com.vanstone.centralserver.common.MyAssert;
import com.vanstone.common.util.MessageUtil;
import com.vanstone.common.util.PinyinUtil;
import com.vanstone.dal.id.IDBuilder;
import com.vanstone.elasticsearch.ElasticsearchCallback;
import com.vanstone.elasticsearch.ElasticsearchCallbackWithoutResult;
import com.vanstone.elasticsearch.ElasticsearchTemplate;

/**
 * @author shipeng
 */
@Service("logService")
public class LogServiceImpl extends AbstractPlatformServiceMgr implements LogService {
	
	/** */
	private static final long serialVersionUID = 1977711005280243820L;
	
	private static Logger LOG = LoggerFactory.getLogger(LogServiceImpl.class);
	
	@Autowired
	private SysTaskManager sysTaskManager;
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.syslog.LogService#addLog(boolean, cn.com.innodev.pdp.framework.common.SysModule, cn.com.innodev.pdp.framework.syslog.LogLevel, java.util.Map, java.util.Map, java.lang.String, java.lang.Object[])
	 */
	@Override
	public SystemLog addLog(boolean asyn, GlobalLogType globalLogType, LogLevel logLevel, Map<String, String> requestParams,
			Map<String, String> runtimeParams, String logPattern, Object... objects) {
		MyAssert.notNull(globalLogType);
		String logContent = MessageUtil.pattern2String(logPattern, objects);
		final SystemLog systemLog = new SystemLog();
		systemLog.setLogContent(logContent);
		if (logLevel != null) {
			systemLog.setLogLevel(logLevel);
		}
		systemLog.setGlobalLogType(globalLogType);
		if (!CollectionUtils.isEmpty(requestParams)) {
			systemLog.addRequestParams(requestParams);
		}
		if (!CollectionUtils.isEmpty(runtimeParams)) {
			systemLog.addRuntimeParams(runtimeParams);
		}
		this.sysTaskManager.executeTask(new Callable<SystemLog>() {
			@Override
			public SystemLog call() throws Exception {
				elasticsearchTemplate.execute(new ElasticsearchCallbackWithoutResult() {
					@Override
					public void doInElasticSearchWithoutResult(Client client) {
						systemLog.setId(IDBuilder.base58Uuid());
						systemLog.setSysInsertTime(new Date());
						client.prepareIndex(Constants.ES_PLATFORM_SYSTEM_LOG_INDEX_NAME, Constants.ES_PLATFORM_SYSTEM_LOG_MAPPING_NAME)
							.setSource(systemLog.toJson()).execute().actionGet();
					}
				});
				return null;
			}
		}, true);
		return systemLog;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.syslog.LogService#addLog(boolean, cn.com.innodev.pdp.framework.common.SysModule, cn.com.innodev.pdp.framework.syslog.LogLevel, javax.servlet.http.HttpServletRequest, java.util.Map, java.lang.String, java.lang.Object[])
	 */
	@SuppressWarnings("unchecked")
	@Override
	public SystemLog addLog(boolean asyn, GlobalLogType globalLogType,
			LogLevel logLevel, HttpServletRequest servletRequest,
			Map<String, String> runtimeParaams, String logPattern,
			Object... objects) {
		Map<String, String> requestParams = null;
		if (servletRequest != null) {
			requestParams = servletRequest.getParameterMap();
		}
		return this.addLog(asyn, globalLogType, logLevel, requestParams, runtimeParaams, logPattern, objects);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.syslog.LogService#deleteLog(java.lang.String)
	 */
	@Override
	public void deleteLog(final String logId) {
		MyAssert.hasText(logId);
		this.elasticsearchTemplate
				.execute(new ElasticsearchCallbackWithoutResult() {
					@Override
					public void doInElasticSearchWithoutResult(Client client) {
						client.prepareDelete(
								Constants.ES_PLATFORM_SYSTEM_LOG_INDEX_NAME,
								Constants.ES_PLATFORM_SYSTEM_LOG_MAPPING_NAME,
								logId).setRefresh(true).execute().actionGet();
					}
				});
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.syslog.LogService#deleteLog(cn.com.innodev.pdp.framework.syslog.SearchLogBean)
	 */
	@Override
	public void deleteLogs(final BasicSearchBean bean) {
		if (bean == null || !bean.existCondition()) {
			LOG.info("Don't exist search condition.");
			return;
		}
		this.elasticsearchTemplate.execute(new ElasticsearchCallbackWithoutResult() {
			@Override
			public void doInElasticSearchWithoutResult(Client client) {
				BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
				if (bean.getStartSysInsertTime() != null) {
					boolQueryBuilder.must(QueryBuilders.rangeQuery("sysInsertTime").gt(bean.getStartSysInsertTime().getTime()));
				}
				if (bean.getEndSysInsertTime() != null) {
					boolQueryBuilder.must(QueryBuilders.rangeQuery("sysInsertTime").lt(bean.getEndSysInsertTime().getTime()));
				}
				if (bean.existLogLevel()) {
					boolQueryBuilder.must(QueryBuilders.inQuery("logLevel", bean.getLogLevels()));
				}
				if (bean.existSysModules()) {
					boolQueryBuilder.must(QueryBuilders.inQuery("sysModule", bean.getSysModules()));
				}
				client.prepareDeleteByQuery(Constants.ES_PLATFORM_SYSTEM_LOG_INDEX_NAME).setTypes(Constants.ES_PLATFORM_SYSTEM_LOG_MAPPING_NAME)
					.setQuery(boolQueryBuilder).execute().actionGet();
			}
		});
	}
	
	@Override
	public void deleteAllLogs() {
		this.elasticsearchTemplate.execute(new ElasticsearchCallbackWithoutResult() {
			@Override
			public void doInElasticSearchWithoutResult(Client client) {
				client.prepareDeleteByQuery(Constants.ES_PLATFORM_SYSTEM_LOG_INDEX_NAME).setTypes(Constants.ES_PLATFORM_SYSTEM_LOG_MAPPING_NAME)
					.setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.syslog.LogService#searchSystemLogs(cn.com.innodev.pdp.framework.syslog.SearchLogBean, long, long)
	 */
	@Override
	public Collection<SystemLog> searchSystemLogs(final SearchLogBean searchLogBean, final long offset, final long limit) {
		Collection<SystemLog> systemLogs = this.elasticsearchTemplate.execute(new ElasticsearchCallback<Collection<SystemLog>>() {
			@SuppressWarnings("unchecked")
			@Override
			public Collection<SystemLog> doInElasticsearch(Client client) {
				SearchRequestBuilder searchRequestBuilder = client.prepareSearch(Constants.ES_PLATFORM_SYSTEM_LOG_INDEX_NAME).setTypes(Constants.ES_PLATFORM_SYSTEM_LOG_MAPPING_NAME);
				if (searchLogBean != null && searchLogBean.existCondition()) {
					BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
					if (searchLogBean.getStartSysInsertTime() != null) {
						boolQueryBuilder.must(QueryBuilders.rangeQuery("sysInsertTime").gt(searchLogBean.getStartSysInsertTime().getTime()));
					}
					if (searchLogBean.getEndSysInsertTime() != null) {
						boolQueryBuilder.must(QueryBuilders.rangeQuery("sysInsertTime").lt(searchLogBean.getEndSysInsertTime().getTime()));
					}
					if (!StringUtils.isBlank(searchLogBean.getKey())) {
						BoolQueryBuilder logContentBoolQueryBuilder = QueryBuilders.boolQuery();
						logContentBoolQueryBuilder.should(QueryBuilders.fuzzyLikeThisFieldQuery("logContent_ansj").likeText(searchLogBean.getKey()));
						logContentBoolQueryBuilder.should(QueryBuilders.fuzzyLikeThisFieldQuery("logContent_ik").likeText(searchLogBean.getKey()));
						logContentBoolQueryBuilder.should(QueryBuilders.prefixQuery("logContent_pinyin", PinyinUtil.cnstr2pinyinstr(searchLogBean.getKey())));
						boolQueryBuilder.must(logContentBoolQueryBuilder);
					}
					if (searchLogBean.existLogLevel()) {
						boolQueryBuilder.must(QueryBuilders.inQuery("logLevel", searchLogBean.getLogLevels()));
					}
					if (searchLogBean.existSysModules()) {
						boolQueryBuilder.must(QueryBuilders.inQuery("sysMoudle", searchLogBean.getSysModules()));
					}
					searchRequestBuilder.setQuery(boolQueryBuilder);
					
				}
				searchRequestBuilder.setFrom((int)offset).setSize((int)limit).setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
				SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
				if (searchResponse == null || searchResponse.getHits() == null || searchResponse.getHits().getTotalHits() <= 0) {
					return null;
				}
				Collection<SystemLog> systemLogs = new ArrayList<SystemLog>();
				for (SearchHit hit : searchResponse.getHits()) {
					Map<String, Object> sourceMap = hit.getSource();
					SystemLog systemLog = new SystemLog();
					systemLog.setId((String)sourceMap.get("id"));
					systemLog.setSysInsertTime(new Date((Long)sourceMap.get("sysInsertTime")));
					systemLog.setLogContent((String)sourceMap.get("logContent_ansj"));
					systemLog.setGlobalLogType(Enum.valueOf(GlobalLogType.class, ((String)sourceMap.get("globalLogType"))));
					systemLog.setLogLevel(Enum.valueOf(LogLevel.class, ((String)sourceMap.get("logLevel"))));
					Map<String, String> requestParams = (Map<String, String>)sourceMap.get("requestParams");
					systemLog.addRequestParams(requestParams);
					Map<String, String> runtimeParams = (Map<String, String>)sourceMap.get("runtimeParams");
					systemLog.addRuntimeParams(runtimeParams);
					systemLogs.add(systemLog);
				}
				return systemLogs;
			}
		});
		return systemLogs;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.framework.syslog.LogService#searchTotalSystemLogs(cn.com.innodev.pdp.framework.syslog.SearchLogBean)
	 */
	@Override
	public long searchTotalSystemLogs(final SearchLogBean searchLogBean) {
		Long total = this.elasticsearchTemplate.execute(new ElasticsearchCallback<Long>() {
			@Override
			public Long doInElasticsearch(Client client) {
				SearchRequestBuilder searchRequestBuilder = client.prepareSearch(Constants.ES_PLATFORM_SYSTEM_LOG_INDEX_NAME).setTypes(Constants.ES_PLATFORM_SYSTEM_LOG_MAPPING_NAME);
				if (searchLogBean != null && searchLogBean.existCondition()) {
					BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
					if (searchLogBean.getStartSysInsertTime() != null) {
						boolQueryBuilder.must(QueryBuilders.rangeQuery("sysInsertTime").gt(searchLogBean.getStartSysInsertTime().getTime()));
					}
					if (searchLogBean.getEndSysInsertTime() != null) {
						boolQueryBuilder.must(QueryBuilders.rangeQuery("sysInsertTime").lt(searchLogBean.getEndSysInsertTime().getTime()));
					}
					if (!StringUtils.isBlank(searchLogBean.getKey())) {
						boolQueryBuilder.must(QueryBuilders.fuzzyLikeThisFieldQuery("logContent").likeText(searchLogBean.getKey()));
					}
					if (searchLogBean.existLogLevel()) {
						boolQueryBuilder.must(QueryBuilders.inQuery("logLevel", searchLogBean.getLogLevels()));
					}
					if (searchLogBean.existSysModules()) {
						boolQueryBuilder.must(QueryBuilders.inQuery("sysMoudle", searchLogBean.getSysModules()));
					}
					searchRequestBuilder.setQuery(boolQueryBuilder);
				}
				if (searchLogBean != null && !StringUtils.isEmpty(searchLogBean.getKey()) && !StringUtils.isBlank(searchLogBean.getKey())) {
					searchRequestBuilder.addSort("_score", SortOrder.DESC);
				}else{
					searchRequestBuilder.addSort("sysInsertTime", SortOrder.DESC);
				}
				searchRequestBuilder.setSearchType(SearchType.COUNT);
				SearchResponse searchResponse = searchRequestBuilder.execute().actionGet();
				if (searchResponse == null || searchResponse.getHits() == null || searchResponse.getHits().getTotalHits() <= 0) {
					return 0L;
				}
				return searchResponse.getHits().getTotalHits();
			}
		});
		return total;
	}
}
