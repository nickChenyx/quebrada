package com.quebrada.dynamic;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QuebradaClassLoader extends ClassLoader {
	
	private static final Log LOG = LogFactory.getLog(QuebradaClassLoader.class);
	
	private Map<String, Class<?>> classes;
	
	private static QuebradaClassLoader instance;
	
	/**
	 * 创建一个新的QuebradaClassLoader
	 * @return QuebradaClassLoader
	 */
	public static synchronized QuebradaClassLoader create() {
		if(instance != null) {
			instance.classes.clear();
		}
		ClassLoader parent = Thread.currentThread().getContextClassLoader();
		if(parent != null) {
			instance = new QuebradaClassLoader(parent);
		} else {
			instance = new QuebradaClassLoader();
		}
		return instance;
	}
	
	public static synchronized QuebradaClassLoader get() {
		if(instance == null) {
			instance = create();
		}
		return instance;
	}
	
	public QuebradaClassLoader() {
		classes = new HashMap<String, Class<?>>();
	}
	
	public QuebradaClassLoader(ClassLoader parent) {
		super(parent);
		classes = new HashMap<String, Class<?>>();
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> clazz = classes.get(name);
		if(clazz == null) {
			throw new ClassNotFoundException(name);
		}
		LOG.debug("find from QuebradaClassLoader : " + name);
		return clazz;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		LOG.debug("load from QuebradaClassLoader : " + name);
		return super.loadClass(name);
	}

	public void addClass(String key, Class<?> clazz) {
		classes.put(key, clazz);
	}

	public Map<String, Class<?>> getClasses() {
		return classes;
	}

}
