package com.quebrada.dynamic;

import org.reflections.adapters.JavaReflectionAdapter;
import org.reflections.vfs.Vfs.File;

public class QuebradaJavaReflectionAdapter extends JavaReflectionAdapter {

	@Override
	public Class<?> getOfCreateClassObject(File file) throws Exception {
		return getOfCreateClassObject(file, QuebradaClassLoader.get());
	}

}
