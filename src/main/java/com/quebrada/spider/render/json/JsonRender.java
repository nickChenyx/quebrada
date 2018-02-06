package com.quebrada.spider.render.json;

import com.quebrada.request.HttpRequest;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderBean;
import net.sf.cglib.beans.BeanMap;

import com.quebrada.spider.render.AbstractRender;

/**
 * 将下载下来的json映射到bean中
 * 
 * @author huchengyi
 *
 */
public class JsonRender extends AbstractRender {
	
	private JsonFieldRender jsonFieldRender;
	
	public JsonRender() {
		super();
		this.jsonFieldRender = new JsonFieldRender();
	}

	@Override
	public void fieldRender(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		jsonFieldRender.render(request, response, beanMap, bean);
	}

}