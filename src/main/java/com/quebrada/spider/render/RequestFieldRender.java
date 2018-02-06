package com.quebrada.spider.render;

import java.lang.reflect.Field;
import java.util.Set;

import com.quebrada.request.HttpRequest;
import net.sf.cglib.beans.BeanMap;

import org.reflections.ReflectionUtils;

import com.quebrada.annotation.Request;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderBean;

public class RequestFieldRender implements FieldRender {

	@Override
	@SuppressWarnings({"unchecked" })
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Set<Field> requestFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Request.class));
		for(Field field : requestFields) {
			beanMap.put(field.getName(), request);
		}
	}
	
}
