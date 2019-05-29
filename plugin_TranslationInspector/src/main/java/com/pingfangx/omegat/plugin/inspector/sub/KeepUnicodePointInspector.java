package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * U+ 形式的 Unicode 代码点被添加空格，进行还原
 *
 * @author pingfangx
 * @date 2019/5/29
 */
public class KeepUnicodePointInspector extends BaseInspector {

    @Override
    public String inspect(String en, String cn) {
        //两者没有合到一起，因为后面要使用 group ，如果使用 | 合到一起，group 会添加并无法统一
        cn = findAndReplace(en, cn, PatternConstants.UNICODE_POINT_PATTERN);
        cn = findAndReplace(en, cn, PatternConstants.UNICODE_CHARACTER_PATTERN);
        return cn;
    }

    private String findAndReplace(String en, String cn, Pattern pattern) {
        Matcher enMatcher = pattern.matcher(en);
        while (enMatcher.find()) {
            // 正则 \ 和 + 需要转义
            String group1 = enMatcher.group(1).replace("\\", "\\\\");
            String group2 = enMatcher.group(2).replace("+", "\\+");
            String group3 = enMatcher.group(3);
            // 3 个部分，每部分都可能被添加空格，或者缺空格
            String cnFind = String.format("\\s?%s\\s?%s\\s?%s\\s?", group1, group2, group3);
            Matcher cnMatcher = Pattern.compile(cnFind).matcher(cn);
            if (cnMatcher.find()) {
                //直接替换为英文组，即是否有空格以英文为准，一般英文间都会有空格，所以不用担心中英文间添加空格的问题
                String cnReplace = enMatcher.group();
                //替换时只需要转义 \
                cnReplace = cnReplace.replace("\\", "\\\\");
                cn = cnMatcher.replaceAll(cnReplace);
            }
        }
        return cn;
    }
}
