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

            //以空格开头，后接标点，再接单词，说明该符号不是标点符号，则不需要替换
            Pattern ignorePattern = Pattern.compile("(?<=\\s)" + enSymbol + "\\w+");
            Matcher ignoreMatcher = ignorePattern.matcher(en);
            if (ignoreMatcher.find()) {
                //不带前导空格，则添加
                //该标点需要在此处添加，而不由中英文之前的检查器添加，否则末尾的标点就会添加空格
                cn = cn.replaceAll("(?<!\\s)(" + enSymbol + "\\w+)", " $1");
                //不管是否替换都 continue，该标点不再处理
                continue;
            }

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
