package com.pingfangx.omegat.plugin.inspector.util;

import java.util.regex.Pattern;

/**
 * 正则
 *
 * @author pingfangx
 * @date 2019/3/4
 */
public class PatternConstants {
    /**
     * 中文，也可以使用 P ，这里简单限制范围
     */
    public static final String CHINESE_REGEX = "[\\u4e00-\\u9fa5]";
    /**
     * 英文，需要与中文间添加空格的内容
     */
    public static final String ENGLISH_REGEX = "[A-Za-z0-9\\[\\]%<>{}']";
    /**
     * 标签
     */
    public static final String TAG_REGEX = "</?\\w\\d+>";
    /**
     * 空格
     */
    public static final String SPACE_REGEX = "\\s*";


    /**
     * 查找的英文符号
     */
    public static final String[] EN_SYMBOLS = new String[]{",", ".", "!", "?", ":", ";"};
    /**
     * 替换的中文符号
     */
    public static final String[] CN_SYMBOLS = new String[]{"，", "。", "！", "？", "：", "；"};

    /**
     * 英文省略号
     */
    public static final String EN_ELLIPSIS = "...";
    /**
     * 中文省略号
     */
    public static final String CN_ELLIPSIS = "…";

    /**
     * OmegaT 解析的 i 标签
     */
    public static final Pattern ITALIC_TAG_PATTERN = Pattern.compile("^i\\d+$");
    /**
     * OmegaT 解析的成对标签
     * 因为标签翻译时可能大小写混乱，所以忽略大小写
     */
    public static final Pattern PAIRED_TAG_PATTERN = Pattern.compile("<(\\w\\d+)>(.+?)</\\1>", Pattern.CASE_INSENSITIVE);
    /**
     * 符号周围的空格
     * 英文 标签 符号 标签 英文
     * 假设标签是不含空格的，应该该检查应该放在移除标签空格之后
     */
    public static final Pattern SYMBOL_SURROUNDED_SPACE_PATTERN = Pattern.compile(String.format("%1$s%2$s(%3$s%2$s)?[-/](%2$s%3$s)?%2$s%1$s", ENGLISH_REGEX, SPACE_REGEX, TAG_REGEX));
    /**
     * 单个标签，用于移除标签内的空格
     * 各部分之间都可能有空格
     */
    public static final Pattern TAG_SURROUNDED_SPACE_PATTERN = Pattern.compile(String.format("<%1$s/?%1$s\\w%1$s\\d+%1$s>", SPACE_REGEX));

    /**
     * 敬语匹配
     */
    public static final Pattern HONORIFICS_PATTERN = Pattern.compile("你(?!们)");
    /**
     * 敬语替换内容
     */
    public static final String HONORIFICS_REPLACEMENT = "您";
}
