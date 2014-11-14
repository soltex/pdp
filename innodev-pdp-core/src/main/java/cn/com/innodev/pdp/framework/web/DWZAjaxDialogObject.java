/**
 * 
 */
package cn.com.innodev.pdp.framework.web;


/**
 * 
 * @author shipeng
 */
public class DWZAjaxDialogObject extends DWZAjaxObject {
	
	private boolean closeDialog = true;
	
	private DWZAjaxDialogObject(String  statusCode, boolean closedialog) {
		super(statusCode);
		this.closeDialog = closedialog;
		this.setDialog(true);
	}
	
	/**
	 * 创建对象
	 * @param dwzStatusCode
	 * @return
	 */
	public static DWZAjaxDialogObject create(DWZStatusCode dwzStatusCode, boolean closeDialog) {
		return new DWZAjaxDialogObject(dwzStatusCode.getCode(), closeDialog);
	}

	/**
	 * 是否关闭对话框
	 * @return
	 */
	public boolean isCloseDialog() {
		return closeDialog;
	}
	
}
