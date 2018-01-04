package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.regex.Pattern;

/**
 * 添加空格
 * 中文与英文之前添加空格
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class AddSpaceInspector extends BaseInspector {
    /**
     * 中文 英文
     */
    private final Pattern pattern1 = Pattern.compile(String.format("(%s)(%s)", PatternConstants.CHINESE_REGEX, PatternConstants.ENGLISH_REGEX));
    /**
     * 英文 中文
     */
    private final Pattern pattern2 = Pattern.compile(String.format("(%s)(%s)", PatternConstants.ENGLISH_REGEX, PatternConstants.CHINESE_REGEX));

    @Override
    public String inspect(String en, String cn) {
        //补充空格
        cn = pattern1.matcher(cn).replaceAll("$1 $2");
        cn = pattern2.matcher(cn).replaceAll("$1 $2");
        return cn;
    }
}
