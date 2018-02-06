package com.quebrada.spider.render;

import com.quebrada.request.HttpRequest;
import net.sf.cglib.beans.BeanMap;

import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderBean;

public interface FieldRender {
	
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean);

}
