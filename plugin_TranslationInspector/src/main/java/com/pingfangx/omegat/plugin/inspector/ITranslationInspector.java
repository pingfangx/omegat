package com.pingfangx.omegat.plugin.inspector;

/**
 * 翻译检查
 *
 * @author pingfangx
 * @date 2019/1/14
 */
public interface ITranslationInspector {
    /**
     * 是否可用，用于可能自动添加，或是动态修改的情况
     */
    default boolean isEnable() {
        return true;
    }

    /**
     * 检测翻译是否准确
     *
     * @param en 英文
     * @param cn 中文
     * @return 处理后的中文
     */
    default String inspect(String en, String cn) {
        return cn;
    }
}
