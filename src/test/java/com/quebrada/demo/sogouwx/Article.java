package com.quebrada.demo.sogouwx;

import com.quebrada.annotation.Quebrada;
import com.quebrada.annotation.HtmlField;
import com.quebrada.annotation.Text;
import com.quebrada.spider.HtmlBean;


@Quebrada(matchUrl="http://mp.weixin.qq.com/s?{params}", pipelines="consolePipeline")
public class Article implements HtmlBean {

	private static final long serialVersionUID = 5310736056776105882L;

	@Text(own=false)
	@HtmlField(cssPath="body")
	private String body;

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
}
