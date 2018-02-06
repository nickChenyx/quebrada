package com.quebrada.scheduler;

import com.quebrada.request.HttpRequest;
import com.quebrada.spider.SpiderThreadLocal;

/**
 * 派生队列上下文，可以在运行时将请求放入派生队列
 * 
 * @author huchengyi
 *
 */
public class DeriveSchedulerContext {
	
	public static void into(HttpRequest request) {
		SpiderThreadLocal.get().getSpiderScheduler().into(request);
	}

}
