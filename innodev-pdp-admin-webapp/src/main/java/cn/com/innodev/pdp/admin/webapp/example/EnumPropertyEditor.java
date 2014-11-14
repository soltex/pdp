/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import java.beans.PropertyEditorSupport;

import com.vanstone.business.lang.EnumUtils;

/**
 * @author shipeng
 */
public class EnumPropertyEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (text == null || text.equals("")) {
			setValue(null);
		}
		Integer code = Integer.parseInt(text);
		ExampleState value = EnumUtils.getByCode(code, ExampleState.class);
		setValue(value);
	}

	@Override
	public String getAsText() {
		ExampleState exampleState = (ExampleState)getValue();
		if (exampleState == null) {
			return null;
		}
		return String.valueOf(exampleState.getCode());
	}
	
}
