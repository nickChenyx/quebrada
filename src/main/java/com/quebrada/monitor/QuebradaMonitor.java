package com.quebrada.monitor;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.quebrada.QuebradaEngine;

public class QuebradaMonitor {
	
	/**
	 * 爬虫启动时间
	 */
	private static String startTime;
	
	/**
	 * 初始抓取地址数量
	 */
	private static int starUrlCount;
	
	/**
	 * 线程数量
	 */
	private static int threadCount;
	
	/**
	 * 抓取间隔时间
	 */
	private static int interval;
	
	public static String getStartTime() {
		return startTime;
	}

	public static void setStartTime(String startTime) {
		QuebradaMonitor.startTime = startTime;
	}

	public static int getStarUrlCount() {
		return starUrlCount;
	}

	public static void setStarUrlCount(int starUrlCount) {
		QuebradaMonitor.starUrlCount = starUrlCount;
	}

	public static int getThreadCount() {
		return threadCount;
	}

	public static void setThreadCount(int threadCount) {
		QuebradaMonitor.threadCount = threadCount;
	}

	public static int getInterval() {
		return interval;
	}

	public static void setInterval(int interval) {
		QuebradaMonitor.interval = interval;
	}

	public static void monitor(QuebradaEngine engine) {
		setStartTime(DateFormatUtils.format(engine.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		setStarUrlCount(engine.getStartRequests().size());
		setThreadCount(engine.getThreadCount());
		setInterval(engine.getInterval());
	}
}
