package com.quebrada.demo.jd;

import com.quebrada.annotation.PipelineName;
import com.quebrada.pipeline.Pipeline;
import com.quebrada.request.HttpRequest;
import com.quebrada.scheduler.SchedulerContext;
import org.apache.commons.lang3.StringUtils;

@PipelineName("productListPipeline")
public class ProductListPipeline implements Pipeline<ProductList> {

	@Override
	public void process(ProductList productList) {
		HttpRequest currRequest = productList.getRequest();
		//下一页继续抓取
		int currPage = productList.getCurrPage();
		int nextPage = currPage + 1;
		int totalPage = productList.getTotalPage();
		if(nextPage <= totalPage) {
			String nextUrl = "";
			String currUrl = currRequest.getUrl();
			if(currUrl.indexOf("page=") != -1) {
				nextUrl = StringUtils.replaceOnce(currUrl, "page=" + currPage, "page=" + nextPage);
			} else {
				nextUrl = currUrl + "&" + "page=" + nextPage;
			}
			SchedulerContext.into(currRequest.subRequest(nextUrl));
		}
	}

}
