package com.zc.cris.springmvc.crud.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 	
 * @ClassName：MyInterceptor.java
 * @Description：TODO (自己定义一个拦截器)
 * @Project Name：springmvc-2-restful
 * @Package Name: com.zc.cris.springmvc.crud.interceptor
 * @Author：zc-cris
 * @version: v1.0
 * @Copyright: zc-cris
 * @email: 17623887386@163.com
 */
public class MyInterceptor implements HandlerInterceptor{
	
	/**
	 * 
	 * @MethodName: preHandle
	 * @Description: TODO (该方法在目标方法调用之前被调用)
	 * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 * @Author：zc-cris
	 */
	/*
	 * 若返回值为false，则不会再调用后续的拦截器和目标方法
	 * 若返回值为true，则继续调用后面的拦截器（不一定会调用目标方法，还得看后续拦截器的脸色）
	 * 
	 * 可以考虑做权限验证，日志，事务等
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("MyInterceptor : preHandle");
		return true;
	}
	
	/**
	 * 
	 * @MethodName: postHandle
	 * @Description: TODO (调用目标方法之后，渲染视图之前)
	 * @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	 * @Author：zc-cris
	 */
	/*
	 * 可以对请求域中的属性或者视图对象做出修改
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		System.out.println("MyInterceptor : postHandle");
	}
	
	/**
	 * 
	 * @MethodName: afterCompletion
	 * @Description: TODO (渲染视图之后被调用)
	 * @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	 * @Author：zc-cris
	 */
	/*
	 * 主要用于释放资源
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("MyInterceptor : afterCompletion");
	}
	
	
	
}
