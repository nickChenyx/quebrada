package com.quebrada.demo.sogouwx;

import com.quebrada.annotation.QuebradaClass;
import com.quebrada.downloader.BeforeDownload;
import com.quebrada.request.HttpRequest;

@QuebradaClass(Article.class)
public class BeforeArticleDownloader implements BeforeDownload {

	@Override
	public void process(HttpRequest request) {
		request.clearCookie();
		request.clearHeader();
	}

}
