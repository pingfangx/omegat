package com.pingfangx.omegat.plugin.inspector;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式检查
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class BaseRegexInspector extends BaseInspector {
    protected final Pattern pattern;
    protected final String replacement;

    public BaseRegexInspector(Pattern pattern) {
        this(pattern, null);
    }

    public BaseRegexInspector(Pattern pattern, String replacement) {
        this.pattern = pattern;
        this.replacement = replacement;
    }

    @Override
    public String inspect(String en, String cn) {
        if (pattern != null && replacement != null) {
            Matcher matcher = pattern.matcher(cn);
            if (matcher.find()) {
                return matcher.replaceAll(replacement);
            }
        }
        return cn;
    }
}
