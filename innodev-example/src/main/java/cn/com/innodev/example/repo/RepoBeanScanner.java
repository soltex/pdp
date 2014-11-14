/**
 * 
 */
package cn.com.innodev.example.repo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * 仓库中Bean扫描器
 * @author shipeng
 */
public class RepoBeanScanner implements BeanDefinitionRegistryPostProcessor{

	@Override
	public void postProcessBeanFactory( ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postProcessBeanDefinitionRegistry(
			BeanDefinitionRegistry registry) throws BeansException {
		// TODO Auto-generated method stub
		
	}
	
}
