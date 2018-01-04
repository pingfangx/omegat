package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseRegexInspector;
import com.pingfangx.omegat.plugin.inspector.util.RegexUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 移除多余的的空格
 * 机器翻译时，有时原本没有空格的，却被错误地加上，于是匹配并将其去除
 *
 * @author pingfangx
 * @date 2019/3/5
 */
public class RemoveRedundantSpaceInspector extends BaseRegexInspector {
    public RemoveRedundantSpaceInspector(Pattern pattern) {
        super(pattern);
    }

    @Override
    public String inspect(String en, String cn) {
        Matcher enMatcher = pattern.matcher(en);
        while (enMatcher.find()) {
            String enGroup = enMatcher.group();
            Matcher cnMatcher = pattern.matcher(cn);
            while (cnMatcher.find()) {
                String cnGroup = cnMatcher.group();
                if (cnGroup.contains(" ")) {
                    //包含空格
                    String cnGroupWithoutSpace = cnGroup.replace(" ", "");
                    //忽略大小写，翻译时可能错误地翻译为大写
                    if (enGroup.equalsIgnoreCase(cnGroupWithoutSpace)) {
                        //去掉空格后相等，替换为英文的
                        cn = RegexUtils.replaceCurrentMatch(cnMatcher, enGroup);
                        //只替换一个，跳出内部循环
                        break;
                    }
                }
            }
        }
        return cn;
    }
}
