package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 符号周围的空格
 * 翻译时可能会错误地加上
 * 如
 * I/O
 * <try>-with-resources
 *
 * @author pingfangx
 * @date 2019/3/5
 */
public class RemoveSymbolSurroundedSpaceInspector extends RemoveRedundantSpaceInspector {
    public RemoveSymbolSurroundedSpaceInspector() {
        super(PatternConstants.SYMBOL_SURROUNDED_SPACE_PATTERN);
    }
}
