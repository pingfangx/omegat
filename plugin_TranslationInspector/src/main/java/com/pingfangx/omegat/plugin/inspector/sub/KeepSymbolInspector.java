package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 保留的标点符号，不需要替换
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class KeepSymbolInspector extends BaseInspector {
    /**
     * 保留的英文符号
     */
    private final String[] keepEnSymbols = new String[]{PatternConstants.EN_ELLIPSIS, "--"};
    /**
     * 错误替换的中文符号
     * 替换时先替换两个，再替换一个，所以单个符号即可
     */
    private final String[] errorReplacedCnSymbols = new String[]{PatternConstants.CN_ELLIPSIS, "—"};

    @Override
    public String inspect(String en, String cn) {
        for (int i = 0; i < keepEnSymbols.length; i++) {
            String keepEnSymbol = keepEnSymbols[i];
            if (en.contains(keepEnSymbol)) {
                //包含英文符号
                String doubleEllipsis = PatternConstants.EN_ELLIPSIS + PatternConstants.EN_ELLIPSIS;
                if (en.endsWith(PatternConstants.EN_ELLIPSIS) && !en.endsWith(doubleEllipsis)) {
                    //如果英文以单个省略号结尾，但中文却以两个省略号结尾
                    if (cn.endsWith(doubleEllipsis)) {
                        cn = cn.replace(doubleEllipsis, PatternConstants.EN_ELLIPSIS);
                    }
                }
                String cnSymbol = errorReplacedCnSymbols[i];
                if (cn.contains(cnSymbol)) {
                    //中文中包含，将其替换
                    cn = cn.replace(cnSymbol + cnSymbol, keepEnSymbol);
                    cn = cn.replace(cnSymbol, keepEnSymbol);
                }
            }
        }
        return cn;
    }
}
