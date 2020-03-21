package com.tomhuwd.lost.util;

/**
 * hhh
 * url工具类
 */
public class UrlUtils {

    // 判断url是否符合规范
    public static boolean UrlIsFitness(String url){
        // 判断是否有请求头
        if(url.lastIndexOf("http") != -1) return true;

        return false;
    }
}
