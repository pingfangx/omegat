package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseWarningInspector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static com.pingfangx.omegat.plugin.inspector.util.PatternConstants.TAG_PATTERN;

public class WarningMissingTagInspector extends BaseWarningInspector {
    @Override
    protected String generateWarning(String en, String cn) {
        List<String> missingTagList = new ArrayList<>();
        List<String> incorrectTagList = new ArrayList<>();
        Matcher enMatcher = TAG_PATTERN.matcher(en);
        outer:
        while (enMatcher.find()) {
            String enTag = enMatcher.group();
            Matcher cnMatcher = TAG_PATTERN.matcher(cn);
            while (cnMatcher.find()) {
                String cnTag = cnMatcher.group();
                //忽略大小写，翻译时可能错误地翻译为大写
                if (enTag.equalsIgnoreCase(cnTag)) {
                    //如果 tag 相等
                    if (!enTag.equals(cnTag)) {
                        //大小写不相等
                        incorrectTagList.add(cnTag);
                    }
                    //已经找到相等的，不必要循环，继续外层循环
                    continue outer;
                }
            }
            //循环结束未跳出，缺少标签
            missingTagList.add(enTag);

        }

        //拼装结果
        StringBuilder stringBuilder = new StringBuilder();
        if (missingTagList.size() > 0) {
            stringBuilder.append("缺少标签");
            stringBuilder.append(Arrays.toString(missingTagList.toArray()));
        }
        if (incorrectTagList.size() > 0) {
            stringBuilder.append("标签不正确");
            stringBuilder.append(Arrays.toString(incorrectTagList.toArray()));
        }
        if (stringBuilder.length() > 0) {
            return stringBuilder.toString();
        }
        return null;
    }
}
