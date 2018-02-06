package com.quebrada.downloader;

import com.quebrada.request.HttpRequest;

public interface BeforeDownload {
	
	public void process(HttpRequest request);

}
