package com.quebrada.pipeline;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.quebrada.spider.SpiderBean;

public abstract class JsonPipeline implements Pipeline<SpiderBean> {

	@Override
	public void process(SpiderBean bean) {
		process(JSON.parseObject(JSON.toJSONString(bean)));
	}
	
	public abstract void process(JSONObject jo);

}
