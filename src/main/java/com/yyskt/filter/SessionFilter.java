package com.yyskt.filter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class SessionFilter extends ZuulFilter {
	
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Object run() throws ZuulException {
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		return null;
	}
	@Override
	public String filterType() {
		// TODO Auto-generated method stub
		return "pre";
	}
	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}
}
