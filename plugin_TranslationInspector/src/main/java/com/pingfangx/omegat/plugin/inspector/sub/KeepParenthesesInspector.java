package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import org.apache.commons.lang.StringUtils;

/**
 * 规范 2.2
 * 保留括号
 * () 不替换为 （）如果被错误替换，则替换回去
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class KeepParenthesesInspector extends BaseInspector {
    @Override
    public String inspect(String en, String cn) {
        //如果本身带中文括号则不替换
        int enCount = StringUtils.countMatches(en, "(") + StringUtils.countMatches(en, ")");
        //查找中文的时候，统一已翻译为中文的和保留英文的数量
        int cnCount = StringUtils.countMatches(cn, "（")
                + StringUtils.countMatches(cn, "）")
                + StringUtils.countMatches(cn, "(")
                + StringUtils.countMatches(cn, ")");
        if (enCount == cnCount) {
            cn = cn.replace("（", "(").replace("）", ")");
        }
        return cn;
    }
}
