package com.quebrada.scheduler;

import com.quebrada.request.HttpRequest;
import com.quebrada.spider.SpiderThreadLocal;

/**
 * 被DeriveSchedulerContext替代，特指派生队列
 * 
 * @author huchengyi
 *
 */
@Deprecated
public class SchedulerContext {
	
	public static void into(HttpRequest request) {
		SpiderThreadLocal.get().getSpiderScheduler().into(request);
	}

}
