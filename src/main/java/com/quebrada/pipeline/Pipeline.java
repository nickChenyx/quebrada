package com.quebrada.pipeline;

import com.quebrada.spider.SpiderBean;

public interface Pipeline<T extends SpiderBean> {

	public void process(T bean);

}
