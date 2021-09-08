package org.tlabs.pma.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class PMAExceptionHandler {
	
	@ExceptionHandler(value=BadCredentialsException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public ModelAndView invalidLoginErrorhandler(HttpServletRequest request, Exception exp) {
		
		ModelAndView model = new ModelAndView();
		
		model.setViewName("error-login");
		model.addObject("error-message", "This is Sample Error Message");
		
		return model;
		
	}
	
	
	@ExceptionHandler(value = UsernameNotFoundException.class)
	public String internalServerErrorhandler(HttpServletRequest req, Exception ex) {
		
		System.out.println("Controller Advice");
		
		System.out.println("Session Creation Time::  " + req.getSession().getCreationTime());

		System.out.println("Request Url: " + req.getRequestURL());
		
		System.out.println(ex.getMessage());
		
		return "error-login";
	}
	

}
