/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp.example;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.innodev.pdp.framework.web.DWZAjaxObject;

import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * 
 * @author shipeng
 */
@Controller
@RequestMapping("/example")
public class ExampleAction extends AbstractBaseSpringAction {
	
	@Override
	@InitBinder
	protected void initBinder(ServletRequestDataBinder binder) throws Exception {
		super.initBinder(binder);
		binder.registerCustomEditor(ExampleState.class, new EnumPropertyEditor());
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
		System.out.println("init binder.");
	}
	
	@RequestMapping("/add-example")
	public String addExample(ModelMap modelMap, @ModelAttribute("exampleForm")ExampleForm exampleForm) {
		ExampleState[] exampleStates = ExampleState.values();
		modelMap.put("exampleStates", exampleStates);
		return "/example/add-example";
	}
	
	@RequestMapping("/add-example-action")
	@ResponseBody
	public DWZAjaxObject addExampleAction(@ModelAttribute("exampleForm") ExampleForm exampleForm, ModelMap modelMap) {
		System.out.println(exampleForm);
		return DWZAjaxObject.createSuccessObject("成功信息");
	}
}
