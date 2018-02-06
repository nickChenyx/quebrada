package com.quebrada.demo;

import com.quebrada.QuebradaEngine;
import com.quebrada.annotation.Quebrada;
import com.quebrada.annotation.HtmlField;
import com.quebrada.annotation.Request;
import com.quebrada.annotation.Text;
import com.quebrada.request.HttpRequest;
import com.quebrada.spider.HtmlBean;

@Quebrada(pipelines="consolePipeline")
public class CommonCrawler implements HtmlBean {

	private static final long serialVersionUID = -8870768223740844229L;

	@Request
	private HttpRequest request;
	
	@Text(own=false)
	@HtmlField(cssPath="body")
	private String body;

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	public static void main(String[] args) {
		QuebradaEngine.create()
		.classpath("com.quebrada.demo")
		.start("https://www.baidu.com/")
		.interval(2000)
		.start();
	}
}
