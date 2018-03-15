package com.zc.cris.springmvc.crud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {
	
	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handArithmeticException(Exception exception) {
		
		System.out.println("^^^^^^^^" + exception.getMessage());
		
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("exception", exception);
		return mv;
	}
	
	
}
