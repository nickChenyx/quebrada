package com.quebrada.downloader;

import com.quebrada.request.HttpRequest;
import com.quebrada.response.HttpResponse;

public interface AfterDownload {
	
	public void process(HttpRequest request, HttpResponse response);

}
