package com.yyskt.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import com.netflix.hystrix.exception.HystrixTimeoutException;

@Component
public class MyFallbackProvider implements FallbackProvider{

	@Autowired
	private HttpServletResponse response; 
	
	@Override
	public String getRoute() {
		 // 表明是为哪个微服务提供回退，*表示为所有微服务提供回退，使用serviceId的话，则为单个微服务
		return "*";
	}

	public ClientHttpResponse fallbackResponse(String route, Throwable cause){
		if(cause instanceof HystrixTimeoutException) {
            return response(HttpStatus.GATEWAY_TIMEOUT);
        } else {
            return response(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	private ClientHttpResponse response(final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpHeaders getHeaders() {
                // headers 设定
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
                headers.setContentType(mt);
                return headers;
            }
            
            @Override
            public InputStream getBody() throws IOException {
            	 response.setCharacterEncoding("utf-8");
                 response.setContentType("text/html");
                 PrintWriter out = response.getWriter();
                 // 第1步、使用File类找到一个文件
                 File f= new File("src/main/resources/html/error.html") ;    // 声明File对象
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
                 //响应体
//               return new ByteArrayInputStream("用户微服务不可用，请稍后再试".getBytes());
                 return null;
            }
            
            @Override
            public String getStatusText() throws IOException {
                // 返回状态文本
                return status.getReasonPhrase();
            }
            
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }
            
            @Override
            public int getRawStatusCode() throws IOException {
                // 返回数字类型的状态码
            	System.out.println(status.value());
                return status.value();
            }
            
            @Override
            public void close() {
            }
        };
    }

}
