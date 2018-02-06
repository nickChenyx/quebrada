package com.quebrada.demo.jd;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.List;

import com.quebrada.annotation.FieldRenderName;
import com.quebrada.request.HttpRequest;
import com.quebrada.spider.render.CustomFieldRender;
import net.sf.cglib.beans.BeanMap;

import com.alibaba.fastjson.JSON;
import com.quebrada.downloader.DownloaderContext;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderBean;

@FieldRenderName("jdPricesFieldRender")
public class JdPricesFieldRender implements CustomFieldRender {

	@Override
	public void render(HttpRequest request, HttpResponse response, BeanMap beanMap, SpiderBean bean, Field field) {
		ProductList jd = (ProductList)bean;
		StringBuffer sb = new StringBuffer();
		/*for(String code : jd.getCodes()) {
			sb.append("J_").append(code).append(",");
		}*/
		String skuIds = sb.toString();
		try {
			skuIds = URLEncoder.encode(skuIds, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String url = "http://p.3.cn/prices/mgets?skuIds="+skuIds;
		HttpRequest subRequest = request.subRequest(url);
		try {
			HttpResponse subReponse = DownloaderContext.download(subRequest);
			String json = subReponse.getContent();
			List<JDPrice> prices = JSON.parseArray(json, JDPrice.class);
			beanMap.put(field.getName(), prices);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}


}
