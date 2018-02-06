package com.quebrada.pipeline;

import com.quebrada.spider.SpiderBean;

public interface PipelineFactory {
	
	public Pipeline<? extends SpiderBean> getPipeline(String name);

}
