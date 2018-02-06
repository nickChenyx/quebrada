package com.quebrada.downloader;


import com.quebrada.downloader.proxy.Proxys;
import com.quebrada.downloader.proxy.ProxysContext;
import com.quebrada.request.HttpPostRequest;
import com.quebrada.request.HttpRequest;
import com.quebrada.response.HttpResponse;
import com.quebrada.spider.SpiderThreadLocal;
import com.quebrada.utils.UrlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.util.CharArrayBuffer;

import java.io.*;
import java.net.Proxy;


/**
 * @author nickChen
 * @since 2018-02-05 16:44.
 */
@com.quebrada.annotation.Downloader("hutoolDownloader")
public class HutoolHttpClientDownloader extends AbstractDownloader {

    private static Log log = LogFactory.getLog(HutoolHttpClientDownloader.class);

    @Override
    public HttpResponse download(HttpRequest request, int timeout) throws DownloadException {
        if (log.isDebugEnabled()) {
            log.debug("downloading..." + request.getUrl());
        }
        cn.hutool.http.HttpRequest reqObj = null;
        if (request instanceof HttpPostRequest) {
            // TODO: 添加 Post 相关
            HttpPostRequest post = (HttpPostRequest) request;
            reqObj = cn.hutool.http.HttpRequest.post(post.getUrl());
        } else {
            reqObj = cn.hutool.http.HttpRequest.get(request.getUrl());
        }
        //header
        boolean isMobile = SpiderThreadLocal.get().getEngine().isMobile();
        reqObj.header("User-Agent", UserAgent.getUserAgent(isMobile));
        reqObj.addHeaders(request.getHeaders());

        //request config
        reqObj.timeout(timeout);
        reqObj.setFollowRedirects(false);

        //proxy
        // FIXME: 代理配置
//        reqObj.setProxy(proxy());

        //request and response
        // FIXME: 默认使用 cookies 键来存 cookies 字符串，暂时没有更好的办法
        reqObj.cookie(request.getCookies().get("cookies"));
        cn.hutool.http.HttpResponse response = reqObj.execute();
        int status = response.getStatus();
        HttpResponse resp = new HttpResponse();
        resp.setStatus(status);
        try {

            if (status == 302 || status == 301) {
                String redirectUrl = response.header("Location");
                resp.setContent(UrlUtils.relative2Absolute(request.getUrl(), redirectUrl));
            } else if (status == 200) {
                ByteArrayInputStream raw = toByteInputStream(response.bodyStream());
                resp.setRaw(raw);
                String contentType = response.header("Content-Type");
                resp.setContentType(contentType);
                if (!isImage(contentType)) {
                    String charset = getCharset(request.getCharset(), contentType);
                    resp.setCharset(charset);
                    //String content = EntityUtils.toString(responseEntity, charset);
                    String content = getContent(raw, response.body().length(), charset);
                    // response.body();
                    resp.setContent(content);
                }
            } else {
                //404，500等
                throw new DownloadServerException("" + status);
            }
        } catch (IOException e) {
            throw new DownloadException(e);
        }
        return resp;
    }

    // TODO: 加上代理
    private Proxy proxy() {
        Proxys proxys = ProxysContext.get();
//        Proxy proxy = new Proxy();
        return null;
    }

    @Override
    public void shutdown() {

    }

    public String getContent(InputStream instream, long contentLength, String charset) throws IOException {
        if (instream == null) {
            return null;
        }
        try {
            int i = (int) contentLength;
            if (i < 0) {
                i = 4096;
            }
            Reader reader = new InputStreamReader(instream, charset);
            CharArrayBuffer buffer = new CharArrayBuffer(i);
            char[] tmp = new char[1024];
            int l;
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.reset();
        }
    }

    private boolean isImage(String contentType) {
        if (contentType == null) {
            return false;
        }
        if (contentType.toLowerCase().startsWith("image")) {
            return true;
        }
        return false;
    }
}
