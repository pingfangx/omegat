package com.pingfangx.omegat.plugin.inspector.util;

import java.util.regex.Matcher;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class RegexUtils {
    /**
     * 替换当前匹配
     * 如果不是第一个匹配项，则 replaceFirst 无效，替换掉第一个
     * <p>
     * appendReplacement 是记录位置的，appendTail 整体添加后续部分，只能调用一次
     * 否则下一次调用 appendReplacement 只会添加从前一个匹配项到当前匹配项的替换内容
     * 也就是说，要替换多个，就应该使用 replaceAll 而不是该方法
     *
     * @param replacement 因为是正则替换，可能需要转义
     */
    public static String replaceCurrentMatch(Matcher matcher, String replacement) {
        return replaceCurrentMatch(matcher, replacement, true);
    }

    public static String replaceCurrentMatch(Matcher matcher, String replacement, boolean autoEscape) {
        if (autoEscape) {
            replacement = replacement.replace("\\", "\\\\")
                    .replace("$", "\\$");
        }
        StringBuffer sb = new StringBuffer();
        matcher.appendReplacement(sb, replacement);
        matcher.appendTail(sb);
        return sb.toString();
    }
}
