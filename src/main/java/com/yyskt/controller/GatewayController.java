package com.yyskt.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "gateway测试")
@RestController
public class GatewayController {

	@Autowired
	private HttpServletResponse response; 
	
	
	@ApiOperation(value = "session测试",tags = "session测试")
	@GetMapping("/")
	public InputStream test() throws IOException
	{
		 response.setCharacterEncoding("utf-8");
         response.setContentType("text/html");
         PrintWriter out = response.getWriter();
         // 第1步、使用File类找到一个文件
         File f= new File("src/main/resources/index.html") ;    // 声明File对象
         // 第2步、通过子类实例化父类对象
         InputStream input = null ;    // 准备好一个输入的对象
         input = new FileInputStream(f)  ;    // 通过对象多态性，进行实例化
         BufferedReader bufferedReader = new BufferedReader(
 		new InputStreamReader(input));
 		String line = null;
 		while ((line = bufferedReader.readLine()) != null) { 
             out.write(line);
 		}
         // 第4步、关闭输出流
         input.close() ;                        // 关闭输出流\
         out.close();
		return null;
				
	}


	@ApiOperation(value = "测试",tags = "测试")
	@GetMapping("/test")
	public String test1() throws IOException
	{
		return "yes!";
				
	}
}
