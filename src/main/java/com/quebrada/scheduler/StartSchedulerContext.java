package com.quebrada.scheduler;

import com.quebrada.request.HttpRequest;
import com.quebrada.spider.SpiderThreadLocal;

/**
 * 初始队列队列上下文，可以在运行时将请求放入初始队列
 * 
 * @author huchengyi
 *
 */
public class StartSchedulerContext {
	
	public static void into(HttpRequest request) {
		SpiderThreadLocal.get().getEngine().getScheduler().into(request);
	}

}
