package com.quebrada.demo.images;

import java.util.List;

import com.quebrada.QuebradaEngine;
import com.quebrada.annotation.Quebrada;
import com.quebrada.annotation.HtmlField;
import com.quebrada.annotation.Image;
import com.quebrada.annotation.PipelineName;
import com.quebrada.pipeline.Pipeline;
import com.quebrada.spider.HtmlBean;

@PipelineName("imageListDemo")
@Quebrada(matchUrl = "http://canlian.jiading.gov.cn/gyzc/zcxmdt/content_430614", pipelines = "imageListDemo")
public class ImageListDemo implements HtmlBean, Pipeline<ImageListDemo> {

	private static final long serialVersionUID = -5583524962096502124L;
	
	@Image
	@HtmlField(cssPath = ".conTxt p img")
	public List<String> pics;

	public List<String> getPics() {
		return pics;
	}

	public void setPics(List<String> pics) {
		this.pics = pics;
	}

	@Override
	public void process(ImageListDemo test) {
		System.out.println(test.getPics());
	}

	public static void main(String[] args) {
        QuebradaEngine.create()
                .classpath("com.quebrada.demo.images")
                .start("http://canlian.jiading.gov.cn/gyzc/zcxmdt/content_430614")
                .interval(1000)
                .run();
	}
}
