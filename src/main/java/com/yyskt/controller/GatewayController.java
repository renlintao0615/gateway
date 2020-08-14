package com.yyskt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "gateway测试")
@RestController
@RequestMapping("/yy")
public class GatewayController {
	
	@Autowired
	HttpServletRequest request;
	
	@ApiOperation(value = "session测试",tags = "session测试")
	@GetMapping("/test")
	public String test()
	{
		Object attribute = request.getSession().getAttribute("AA");
		return attribute.toString();
				
	}

}
