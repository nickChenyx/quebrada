package com.quebrada.listener;

import com.quebrada.QuebradaEngine;

/**
 * 爬虫引擎生命周期监听器
 * 
 * @author LiuJunGuang
 */
public interface EventListener {

	/**
	 * 开始启动时，回调
	 * 
	 * @param ge QuebradaEngine
	 */
	public void onStart(QuebradaEngine ge);

	/**
	 * 暂停时，回调
	 * 
	 * @param ge QuebradaEngine
	 */
	public void onPause(QuebradaEngine ge);

	/**
	 * 恢复抓取时，回调
	 * 
	 * @param ge QuebradaEngine
	 */
	public void onRestart(QuebradaEngine ge);

	/**
	 * 引擎停止时，回调
	 * 
	 * @param ge QuebradaEngine
	 */
	public void onStop(QuebradaEngine ge);
}
