/**
 * 
 */
package cn.com.innodev.pdp.business.sdk.adminservice.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vanstone.framework.business.ServiceManagerFactory;

import cn.com.innodev.pdp.business.sdk.adminservice.auth.AuthManager;
import cn.com.innodev.pdp.framework.Constants;

/**
 * @author shipeng
 */
public class AdminAuthFilter implements Filter {

	private static Logger LOG = LoggerFactory.getLogger(AdminAuthFilter.class);
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest)request;
		HttpServletResponse servletResponse = (HttpServletResponse)response;
		String requestURI = servletRequest.getRequestURI();
		LOG.debug("Request Admin Console {}",requestURI);
		
		AuthManager authManager = ServiceManagerFactory.getInstance().getService(AuthManager.SERVICE);
		boolean isLogin = authManager.isAdminLogin(servletRequest);
		if (isLogin) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		if (requestURI.indexOf(Constants.AUTH_ADMIN_LOGIN_PAGE) != -1 || requestURI.indexOf(Constants.AUTH_ADMIN_LOGOUT_PAGE) != -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		servletResponse.sendRedirect("/" + Constants.AUTH_ADMIN_LOGIN_PAGE + "." + Constants.PAGE_SUFFIX);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		LOG = null;
	}
	
}
