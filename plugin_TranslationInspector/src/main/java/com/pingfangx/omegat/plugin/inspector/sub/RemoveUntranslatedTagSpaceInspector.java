package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 移除未翻译的标签中的空格
 */
public class RemoveUntranslatedTagSpaceInspector extends RemoveRedundantSpaceInspector {
    public RemoveUntranslatedTagSpaceInspector() {
        super(PatternConstants.UNTRANSLATED_TAG_PATTERN);
    }
}
