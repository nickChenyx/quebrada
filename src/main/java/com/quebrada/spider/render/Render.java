package com.quebrada.spider.render;

import com.quebrada.request.HttpRequest;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderBean;

/**
 * 渲染器，将下载下来的各种格式内容对象化，并将需要继续抓取的链接抽取出来
 * 
 * @author huchengyi
 *
 */
public interface Render {

	public SpiderBean inject(Class<? extends SpiderBean> clazz, HttpRequest request, HttpResponse response);
	
	public void requests(HttpRequest request, SpiderBean bean);
}
