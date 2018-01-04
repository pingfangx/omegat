package com.pingfangx.omegat.plugin.inspector;

/**
 * 基类
 *
 * @author pingfangx
 * @date 2019/1/14
 */
public class BaseInspector implements ITranslationInspector {

    /**
     * 输出调试信息
     */
    public void logDebug(String text, Object... args) {
        System.out.println(String.format(text, args));
    }
}
