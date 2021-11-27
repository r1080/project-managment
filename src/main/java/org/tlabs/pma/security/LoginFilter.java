package org.tlabs.pma.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginFilter implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		LOGGER.info("Custom Filter in Spring Security Filter Chain");
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        LOGGER.info("Request In Filter Chain {} " , req.getMethod());
       		
		chain.doFilter(request, response);
		
		
		LOGGER.info("Response In Filter Chain {} " , res.getStatus());
		
	}
	
	

}
