package com.quebrada.utils;

import com.quebrada.spider.SpiderThreadLocal;

public class EngineRetUtil{

    public static Object getRet(){
        return SpiderThreadLocal.get().getEngine().getRet();
    }

    public static void setRet(Object o){
        SpiderThreadLocal.get().getEngine().setRet(o);
    }

}
