package com.quebrada.spider.render;

import com.quebrada.spider.render.html.HtmlRender;
import com.quebrada.spider.render.json.JsonRender;
import org.reflections.Reflections;

public class DefaultRenderFactory extends RenderFactory {
	
	public DefaultRenderFactory(Reflections reflections) {
		super(reflections);
	}

	public HtmlRender createHtmlRender() {
		return new HtmlRender();
	}
	
	public JsonRender createJsonRender() {
		return new JsonRender();
	}
	
}
