/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import com.vanstone.common.util.CommonDateUtil;

/**
 * @author shipeng
 */
public class DatePropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals("")) {
			setValue(null);
			return;
		}
		setValue(CommonDateUtil.string2Date(text, "yyyy-MM-dd"));
	}
	
	@Override
	public String getAsText() {
		Object value = this.getValue();
		if (value == null) {
			return null;
		}
		if (!(value instanceof Date)) {
			throw new IllegalArgumentException();
		}
		return CommonDateUtil.date2String((Date)value, "yyyy-MM-dd");
	}
	
}
