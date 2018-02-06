package com.quebrada.pipeline;

import com.alibaba.fastjson.JSON;
import com.quebrada.annotation.PipelineName;
import com.quebrada.spider.SpiderBean;

@PipelineName("consolePipeline")
public class ConsolePipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		System.out.println(JSON.toJSONString(bean));
	}

}
