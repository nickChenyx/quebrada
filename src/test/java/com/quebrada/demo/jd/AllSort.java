package com.quebrada.demo.jd;

import java.util.List;

import com.quebrada.QuebradaEngine;
import com.quebrada.annotation.Quebrada;
import com.quebrada.annotation.HtmlField;
import com.quebrada.annotation.Request;
import com.quebrada.request.HttpGetRequest;
import com.quebrada.request.HttpRequest;
import com.quebrada.spider.HtmlBean;

@Quebrada(matchUrl="https://www.jd.com/allSort.aspx", pipelines={"consolePipeline", "allSortPipeline"})
public class AllSort implements HtmlBean {

	private static final long serialVersionUID = 665662335318691818L;
	
	@Request
	private HttpRequest request;

	//手机
	@HtmlField(cssPath=".category-items > div:nth-child(1) > div:nth-child(2) > div.mc > div.items > dl")
	private List<Category> mobile;
	
	//家用电器
	@HtmlField(cssPath=".category-items > div:nth-child(1) > div:nth-child(3) > div.mc > div.items > dl")
	private List<Category> domestic;
	
	//母婴
	@HtmlField(cssPath=".category-items > div:nth-child(2) > div:nth-child(2) > div.mc > div.items > dl")
	private List<Category> baby;

	public List<Category> getMobile() {
		return mobile;
	}

	public void setMobile(List<Category> mobile) {
		this.mobile = mobile;
	}

	public List<Category> getDomestic() {
		return domestic;
	}

	public void setDomestic(List<Category> domestic) {
		this.domestic = domestic;
	}

	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}

	public List<Category> getBaby() {
		return baby;
	}

	public void setBaby(List<Category> baby) {
		this.baby = baby;
	}

	public static void main(String[] args) {
		//先获取分类列表
		HttpGetRequest start = new HttpGetRequest("https://www.jd.com/allSort.aspx");
		start.setCharset("GBK");
		QuebradaEngine.create()
		.classpath("com.quebrada.demo.jd")
		//开始抓取的页面地址
		.start(start)
		//开启几个爬虫线程
		.thread(1)
		//单个爬虫每次抓取完一个请求后的间隔时间
		.interval(2000)
		.run();
		
		
		//分类列表下的商品列表采用3线程抓取
		QuebradaEngine.create()
		.classpath("com.quebrada.demo.jd")
		//开始抓取的页面地址
		.start(AllSortPipeline.sortRequests)
		//开启几个爬虫线程
		.thread(3)
		//单个爬虫每次抓取完一个请求后的间隔时间
		.interval(2000)
		.start();
	}
}
