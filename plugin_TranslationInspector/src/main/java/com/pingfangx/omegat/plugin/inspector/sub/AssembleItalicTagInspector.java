package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.regex.Matcher;

/**
 * 组成斜体标签的内容
 * 斜体一般为专有名词，最好展示英文，也展示中文
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class AssembleItalicTagInspector extends KeepOmegaTTagInspector {
    /**
     * 斜体的时候，组成为 英文 (中文)
     */
    @Override
    protected String getReplaceContent(Matcher enMatcher, Matcher cnMatcher, String tag) {
        if (PatternConstants.ITALIC_TAG_PATTERN.matcher(tag).matches()) {
            return String.format("%s (%s)", enMatcher.group(2), cnMatcher.group(2));
        } else {
            return super.getReplaceContent(enMatcher, cnMatcher, tag);
        }
    }
}
