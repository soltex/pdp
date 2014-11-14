/**
 * 
 */
package cn.com.innodev.pdp.admin.webapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vanstone.elasticsearch.ElasticsearchTemplate;
import com.vanstone.webframework.AbstractBaseSpringAction;

/**
 * @author shipeng
 */
@Controller("indexAction")
public class IndexAction extends AbstractBaseSpringAction {
	
	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;
	
	@RequestMapping("/index")
	public String index(ModelMap modelMap) {
		return "/index";
	}
	
}
