package com.quebrada.demo.sogouwx;

import java.util.List;

import com.quebrada.QuebradaEngine;
import com.quebrada.annotation.Quebrada;
import com.quebrada.annotation.HtmlField;
import com.quebrada.annotation.RequestParameter;
import com.quebrada.request.HttpGetRequest;
import com.quebrada.spider.HtmlBean;

@Quebrada(matchUrl="http://weixin.sogou.com/weixin?type=2&query={keyword}", pipelines={"consolePipeline"})
public class SogouWX implements HtmlBean {

	private static final long serialVersionUID = 7504646787612579665L;

	@RequestParameter
	private String keyword;
	
	@HtmlField(cssPath=".results .wx-rb")
	private List<WeiXin> weixins;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public List<WeiXin> getWeixins() {
		return weixins;
	}

	public void setWeixins(List<WeiXin> weixins) {
		this.weixins = weixins;
	}
	
	public static void main(String[] args) {
		HttpGetRequest start = new HttpGetRequest("http://weixin.sogou.com/weixin?type=2&query=%E6%B7%B1%E5%9C%B3");
		start.addCookie("SNUID", "1D22392EF4F6C4A92076C208F4DE3AAB");
		start.addCookie("SUID", "EED1CDDA6B20900A00000000570E1872");
		start.addCookie("IPLOC", "CN1100");
		//start.addCookie("SUV", "1460541527037365");
		start.addHeader("Host", "weixin.sogou.com");
		start.addHeader("Upgrade-Insecure-Requests", "1");
		start.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		start.addHeader("Accept-Encoding", "gzip, deflate, sdch");
		start.addHeader("Cache-Control", "max-age=0");
		QuebradaEngine.create()
		.classpath("com.quebrada.demo.sogouwx")
		.start(start)
		//.start("http://mp.weixin.qq.com/s?__biz=MzAwMjIyODIwNA==&mid=2650194319&idx=7&sn=5fbba7eb7f393508461468ea5c412ba5&3rd=MzA3MDU4NTYzMw==&scene=6")
		.interval(5000)
		.run();
	}
}
