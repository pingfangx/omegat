package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 移除中文标点周围的符号
 *
 * @author pingfangx
 * @date 2019/3/6
 */
public class RemoveChineseSymbolSurroundedSpaceInspector extends ReplaceEndSymbolInspector {
    @Override
    public String inspect(String en, String cn) {
        for (String cnSymbol : PatternConstants.CN_SYMBOLS) {
            cn = cn.replaceAll(String.format("%1$s(%2$s)%1$s", PatternConstants.SPACE_REGEX, cnSymbol), "$1");
        }
        return cn;
    }
}
