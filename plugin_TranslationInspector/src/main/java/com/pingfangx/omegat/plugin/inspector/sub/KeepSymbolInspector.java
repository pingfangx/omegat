package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;

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
    private final String[] keepEnSymbols = new String[]{"...", "--"};
    /**
     * 错误替换的中文符号
     * 替换时先替换两个，再替换一个，所以单个符号即可
     */
    private final String[] errorReplacedCnSymbols = new String[]{"…", "—"};

    @Override
    public String inspect(String en, String cn) {
        for (int i = 0; i < keepEnSymbols.length; i++) {
            String keepEnSymbol = keepEnSymbols[i];
            if (en.contains(keepEnSymbol)) {
                //包含英文符号
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
