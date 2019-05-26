package com.pingfangx.omegat.plugin;

/**
 * @author pingfangx
 * @date 2019/5/26
 */
public class BaseTranslatorUtils {
    /**
     * 处理异常，返回信息
     */
    public static String processException(Throwable e) {
        e.printStackTrace();
        return e.getLocalizedMessage();
    }
}
