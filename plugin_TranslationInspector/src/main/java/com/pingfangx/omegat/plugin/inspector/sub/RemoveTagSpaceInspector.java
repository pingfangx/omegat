package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 移除 tag 间的空格
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class RemoveTagSpaceInspector extends RemoveRedundantSpaceInspector {
    public RemoveTagSpaceInspector() {
        super(PatternConstants.TAG_SURROUNDED_SPACE_PATTERN);
    }
}
