/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.system;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.business.sdk.adminservice.system.SystemManager;
import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.web.DWZAjaxObject;

import com.vanstone.centralserver.common.conf.VanstoneConf;
import com.vanstone.elasticsearch.conf.ESConfig;
import com.vanstone.fs.Setting;
import com.vanstone.redis.conf.RedisPoolConf;
import com.vanstone.weedfs.conf.WeedFSConf;

/**
 * 
 * @author shipeng
 */
@Controller
@RequestMapping("/system")
public class SystemAction {
	
	@Autowired
	private SystemManager systemManager;
	
	@RequestMapping("/view-systeminfo")
	public String viewSystemInfo(ModelMap modelMap) {
		Setting localFSSetting = this.systemManager.getLocalFSInfo();
		WeedFSConf weedFSConf = this.systemManager.getWeedFSInfo();
		ESConfig esConfig = this.systemManager.getElasticsearchInfo();
		Map<String,RedisPoolConf> redisDataMap = this.systemManager.getRedisInfo();
		VanstoneConf centralServerConf = this.systemManager.getCentralServerInfo();
		
		modelMap.put("localFSSetting", localFSSetting);
		modelMap.put("weedFSConf", weedFSConf);
		modelMap.put("esConfig", esConfig);
		modelMap.put("redisDataMap", redisDataMap);
		modelMap.put("centralServerConf", centralServerConf);
		
		String es_address = esConfig.getAddressDetails()[0].getAddr();
		String esHeaderPluginName = "http://" + es_address + Constants.ELASTICSEARCH_PORT_AND_HEAD_PLUGIN_NAME;
		modelMap.put("esHeaderPluginName", esHeaderPluginName);
		
		return "/system/view-systeminfo";
	}
	
	@RequestMapping("/redis-flushall")
	@ResponseBody
	public DWZAjaxObject redisFlushall() {
		this.systemManager.flushAllOfRedis();
		return DWZAjaxObject.createSuccessObject("Redis缓冲已清理。");
	}
}
