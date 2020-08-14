package com.yyskt.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public class SessionFilter {
	
	@Autowired
	HttpServletRequest request;
	public void main(String[] args) {
		
		HttpSession session = request.getSession();
		session.setAttribute("AA", "BB");
	}
}
