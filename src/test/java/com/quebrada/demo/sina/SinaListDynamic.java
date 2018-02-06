package com.quebrada.demo.sina;

import com.alibaba.fastjson.JSONObject;
import com.quebrada.QuebradaEngine;
import com.quebrada.annotation.PipelineName;
import com.quebrada.dynamic.DynamicQuebrada;
import com.quebrada.pipeline.JsonPipeline;

@PipelineName("sinaListDynamicPipeline")
public class SinaListDynamic extends JsonPipeline {
	
	@Override
	public void process(JSONObject jo) {
		System.out.println(jo);		
	}
	
	public static void main(String[] args) {
		
		Class<?> item = DynamicQuebrada.html()
				.stringField("url").csspath("a").build()
				.stringField("tag").csspath("a").text().build()
				.register();
		
		DynamicQuebrada.html()
		.quebrada("http://news.sina.com.cn/china/", "sinaListDynamicPipeline")
		.listField("items", item).csspath("#subShowContent1_static .news-item h2 a").build()
		.register();
		
		QuebradaEngine.create()
		.classpath("com.quebrada.demo.sina")
		.start("http://news.sina.com.cn/china/")
		.run();
	}

}