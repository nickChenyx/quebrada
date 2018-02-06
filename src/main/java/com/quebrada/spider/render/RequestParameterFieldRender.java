package com.quebrada.spider.render;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.quebrada.request.HttpRequest;
import net.sf.cglib.beans.BeanMap;

import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import com.quebrada.annotation.RequestParameter;
import com.quebrada.request.HttpPostRequest;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderBean;
import com.quebrada.spider.conversion.Conversion;

public class RequestParameterFieldRender implements FieldRender {

	@Override
	@SuppressWarnings({ "unchecked" })
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> requestParameterFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(RequestParameter.class));
		for(Field field : requestParameterFields) {
			RequestParameter requestParameter = field.getAnnotation(RequestParameter.class);
			String key = requestParameter.value();
			if(StringUtils.isEmpty(key)) {
				key = field.getName();
			}
			String src = request.getParameter(key);
			if(request instanceof HttpPostRequest && StringUtils.isEmpty(src)) {
				HttpPostRequest postRequest = (HttpPostRequest)request;
				src = postRequest.getField(key);
			}
			try {
				Object value = Conversion.getValue(field.getType(), src);
				fieldMap.put(field.getName(), value);
			} catch(Exception ex) {
				//throw new FieldRenderException(field, src, ex);
				FieldRenderException.log(field, src, ex);
			}
		}
		beanMap.putAll(fieldMap);
	}
	
}
