package com.quebrada.listener;

import com.quebrada.QuebradaEngine;

/**
 * 简单的引擎时间兼容实现类，可以继承该类覆盖需要的方法
 * 
 * @author LiuJunGuang
 */
public abstract class SimpleEventListener implements EventListener {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.quebrada.listener.EventListener#onStart(com.quebrada.QuebradaEngine)
	 */
	@Override
	public void onStart(QuebradaEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.quebrada.listener.EventListener#onPause(com.quebrada.QuebradaEngine)
	 */
	@Override
	public void onPause(QuebradaEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.quebrada.listener.EventListener#onRestart(com.quebrada.QuebradaEngine)
	 */
	@Override
	public void onRestart(QuebradaEngine ge) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.quebrada.listener.EventListener#onStop(com.quebrada.QuebradaEngine)
	 */
	@Override
	public void onStop(QuebradaEngine ge) {
	}

}
