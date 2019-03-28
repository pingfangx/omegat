package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 替换句子中的标点符号
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class ReplaceSymbolInspector extends ReplaceEndSymbolInspector {
    @Override
    public String inspect(String en, String cn) {
        for (int i = 0; i < PatternConstants.EN_SYMBOLS.length; i++) {
            String enSymbol = PatternConstants.EN_SYMBOLS[i];
            enSymbol = enSymbol.replace(".", "\\.");
            enSymbol = enSymbol.replace("?", "\\?");
            //后面不跟标点符号，表示不能是多个相连（如省略号...）
            Pattern pattern = Pattern.compile(String.format("(%1$s)%2$s(?!%2$s)", PatternConstants.CHINESE_REGEX, enSymbol));
            Matcher matcher = pattern.matcher(cn);
            if (matcher.find()) {
                cn = matcher.replaceAll("$1" + PatternConstants.CN_SYMBOLS[i]);
            }
        }
        return cn;
    }
}
