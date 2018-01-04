package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseRegexInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;
import com.pingfangx.omegat.plugin.inspector.util.RegexUtils;

import java.util.regex.Matcher;

/**
 * 保留 OmegaT 标签
 * OmegaT 通常会划分标签，标签一般是不需要翻译的
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class KeepOmegaTTagInspector extends BaseRegexInspector {
    public KeepOmegaTTagInspector() {
        super(PatternConstants.PAIRED_TAG_PATTERN);
    }

    @Override
    public String inspect(String en, String cn) {
        Matcher enMatcher = pattern.matcher(en);
        while (enMatcher.find()) {
            String enTag = enMatcher.group(1);
            Matcher cnMatcher = pattern.matcher(cn);
            while (cnMatcher.find()) {
                String cnTag = cnMatcher.group(1);
                //忽略大小写，翻译时可能错误地翻译为大写
                if (enTag.equalsIgnoreCase(cnTag)) {
                    //如果 tag 相等，替换内容
                    String replacement = String.format("<%s>%s</%s>", enTag, getReplaceContent(enMatcher, cnMatcher, enTag), enTag);
                    cn = RegexUtils.replaceCurrentMatch(cnMatcher, replacement);
                    //只替换一个，跳出循环
                    break;
                }
            }
        }
        return cn;
    }

    /**
     * 获取要替换的标签中的内容
     * 默认实现为保留英文
     */
    protected String getReplaceContent(Matcher enMatcher, Matcher cnMatcher, String tag) {
        return enMatcher.group(2);
    }
}
