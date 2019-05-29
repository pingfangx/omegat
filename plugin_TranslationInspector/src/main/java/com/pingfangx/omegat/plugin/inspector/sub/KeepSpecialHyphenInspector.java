package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseRegexInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 特殊的连字符谷歌翻译无法正常处理，进行还原
 *
 * @author pingfangx
 * @date 2019/5/29
 */
public class KeepSpecialHyphenInspector extends BaseRegexInspector {
    public KeepSpecialHyphenInspector() {
        super(PatternConstants.SPECIAL_HYPHEN_PATTERN_CN, PatternConstants.SPECIAL_HYPHEN);
    }

    @Override
    public String inspect(String en, String cn) {
        if (!en.contains(replacement)) {
            //如果英文不包含，不需要替换
            return cn;
        }
        return super.inspect(en, cn);
    }
}
