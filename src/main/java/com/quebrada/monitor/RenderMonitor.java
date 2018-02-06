package com.quebrada.monitor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RenderMonitor {
	
	private static Map<String, AtomicLong> statistics = new ConcurrentHashMap<String, AtomicLong>();
	
	private static Lock lock = new ReentrantLock();
	
	public static void incrException(String quebradaName) {
		getQuebrada(quebradaName).incrementAndGet();
	}
	
	public static AtomicLong getQuebrada(String quebradaName) {
		AtomicLong al = statistics.get(quebradaName);
		if(al != null) {
			return al;
		}
		lock.lock();
		try {
			al = statistics.get(quebradaName);
			if(al == null) {
				al = new AtomicLong(0);
				statistics.put(quebradaName, al);
			}
		} finally {
			lock.unlock();
		}
		return al;
	}
	
	public static Map<String, AtomicLong> getStatistics() {
		return statistics;
	}
}
