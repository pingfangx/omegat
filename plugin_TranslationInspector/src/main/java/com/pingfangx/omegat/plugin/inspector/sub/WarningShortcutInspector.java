package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseWarningInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.regex.Matcher;

/**
 * 警告快捷方式
 */
public class WarningShortcutInspector extends BaseWarningInspector {
    private static final String[] IGNORE_WORDS = new String[]{
            "shortcut:",
            "productName;",
            "majorVersion;",
            "minorVersion;",
            "majorMinorVersion;",
            "settingsPath;",
    };

    @Override
    protected String generateWarning(String en, String cn) {
        Matcher enMatcher = PatternConstants.AND_SHORTCUT_PATTERN.matcher(en);
        int count = 0;
        while (enMatcher.find()) {
            count++;
        }
        if (count != 1) {
            //没有找到或多于一个，都不处理
            return null;
        }
        enMatcher.reset();
        if (enMatcher.find()) {
            for (String ignoreWord : IGNORE_WORDS) {
                if (en.contains("&" + ignoreWord)) {
                    //包含忽略词
                    return null;
                }
            }
            String letter = enMatcher.group(1);
            //转为大写
            letter = letter.toUpperCase();
            String suffix = String.format("(&%s)", letter);
            if (!en.endsWith(suffix)) {
                return "可能缺少快捷键" + suffix;
            }
        }
        return null;
    }
}
