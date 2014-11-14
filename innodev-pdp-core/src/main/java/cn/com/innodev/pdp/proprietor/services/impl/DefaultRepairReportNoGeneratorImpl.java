/**
 * 
 */
package cn.com.innodev.pdp.proprietor.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;

import cn.com.innodev.pdp.framework.Constants;
import cn.com.innodev.pdp.framework.services.AbstractPlatformServiceMgr;
import cn.com.innodev.pdp.proprietor.persistence.PropReairReportNoDOMapper;
import cn.com.innodev.pdp.proprietor.persistence.object.PropReairReportNoDO;

/**
 * 
 * @author shipeng
 */
@Service("repairReportNoGenerator")
public class DefaultRepairReportNoGeneratorImpl extends AbstractPlatformServiceMgr implements RepairReportNoGenerator {
	
	/** */
	private static final long serialVersionUID = 2677899336664395562L;
	
	private static Logger LOG = LoggerFactory.getLogger(DefaultRepairReportNoGeneratorImpl.class);
	
	@Autowired
	private PropReairReportNoDOMapper propReairReportNoDOMapper;
	
	/* (non-Javadoc)
	 * @see cn.com.innodev.pdp.proprietor.services.impl.RepairReportNoGenerator#generateNo()
	 */
	@Override
	public String generateNo() {
		String nodb = this.execute(new TransactionCallback<String>() {
			@Override
			public String doInTransaction(TransactionStatus arg0) {
				PropReairReportNoDO model = new PropReairReportNoDO();
				propReairReportNoDOMapper.insert(model);
				return Integer.toString(model.getId());
			}
		});
		StringBuffer sb = new StringBuffer();
		if (nodb.length() < Constants.REPAIR_REPORT_NO_DEFAULT_LENGTH) {
			int pos = Constants.REPAIR_REPORT_NO_DEFAULT_LENGTH - nodb.length();
			sb.append(Constants.REPAIR_REPORT_NO_PREFIX);
			for (int i = 0; i < pos; i++) {
				sb.append(0);
			}
			sb.append(nodb);
		}else{
			sb.append(Constants.REPAIR_REPORT_NO_PREFIX);
		}
		return sb.toString();
	}

	@Override
	public void clearNos() {
		this.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				propReairReportNoDOMapper.deleteAll();
			}
		});
		LOG.info("Finish Clear Repair Report No In DB.");
	}
	
}
