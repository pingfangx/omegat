package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 规范 2
 * 替换结尾的标点符号
 * 以英文结尾，替换为以中文结尾
 * 在中间可能情况比较多，就不替换
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class ReplaceEndSymbolInspector extends BaseInspector {

    @Override
    public String inspect(String en, String cn) {
        for (int i = 0; i < PatternConstants.EN_SYMBOLS.length; i++) {
            String enSymbol = PatternConstants.EN_SYMBOLS[i];
            if (en.endsWith(enSymbol)) {
                if (en.endsWith(PatternConstants.EN_ELLIPSIS)) {
                    //省略号不处理
                    continue;
                }
                //以某一英文字符结尾
                String cnSymbol = PatternConstants.CN_SYMBOLS[i];
                if (cn.endsWith(enSymbol)) {
                    //中文以英文符号结尾，替换
                    cn = cn.substring(0, cn.length() - 1) + cnSymbol;
                } else if (!cn.endsWith(cnSymbol)) {
                    //不以中文符号结尾，添加
                    cn += cnSymbol;
                }
                //可以 break
                break;
            }
        }
        return cn;
    }
}
