package com.quebrada.spider.render.html;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.reflections.ReflectionUtils;

import com.quebrada.annotation.Ajax;
import com.quebrada.downloader.DownloadException;
import com.quebrada.downloader.DownloaderContext;
import com.quebrada.request.HttpRequest;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.JsonBean;
import com.quebrada.spider.SpiderBean;
import com.quebrada.spider.render.FieldRender;
import com.quebrada.spider.render.FieldRenderException;
import com.quebrada.spider.render.Render;
import com.quebrada.spider.render.RenderContext;
import com.quebrada.spider.render.RenderType;
import com.quebrada.utils.ReflectUtils;
import com.quebrada.utils.UrlMatcher;

import net.sf.cglib.beans.BeanMap;

/**
 * 渲染@Ajax属性
 * 
 * @author huchengyi
 *
 */
public class AjaxFieldRender implements FieldRender {

	@Override
	@SuppressWarnings("unchecked")
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean) {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		Set<Field> ajaxFields = ReflectionUtils.getAllFields(bean.getClass(), ReflectionUtils.withAnnotation(Ajax.class));
		for (Field ajaxField : ajaxFields) {
			Object value = injectAjaxField(request, beanMap, ajaxField);
			if(value != null) {
				fieldMap.put(ajaxField.getName(), value);
			}
		}
		beanMap.putAll(fieldMap);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object injectAjaxField(HttpRequest request, BeanMap beanMap, Field field) {
		Class clazz = field.getType();
		// ajax的属性类型必须是spiderBean
		Ajax ajax = field.getAnnotation(Ajax.class);
		String url = ajax.url();
		url = UrlMatcher.replaceParams(url, request.getParameters());
		url = UrlMatcher.replaceFields(url, beanMap);
		HttpRequest subRequest = request.subRequest(url);
		HttpResponse subReponse = null;
		try {
			subReponse = DownloaderContext.download(subRequest);
			RenderType type = RenderType.HTML;
			if (ReflectUtils.haveSuperType(clazz, JsonBean.class)) {
				type = RenderType.JSON;
			}
			Render render = RenderContext.getRender(type);
			return render.inject(clazz, subRequest, subReponse);
		} catch (DownloadException ex) {
			//throw new FieldRenderException(field, ex.getMessage(), ex);
			FieldRenderException.log(field, ex.getMessage(), ex);
			return null;
		} finally {
			if(subReponse != null) {
				subReponse.close();
			}
		}
	}
}
