package com.yyskt.filter;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class SessionFilter extends ZuulFilter {
	
	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public Object run() throws ZuulException {
		System.out.println(1);
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
