package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;

/**
 * 替换特殊的破折号
 *
 * @author pingfangx
 * @date 2019/3/11
 */
public class ReplaceSpecialDashInspector extends BaseInspector {

    public static final String SPECIAL_DASH = "\u0097";

    @Override
    public String inspect(String en, String cn) {
        if (en.contains(SPECIAL_DASH)) {
            if (!cn.contains(SPECIAL_DASH)) {
                cn = cn.replace("??", SPECIAL_DASH)
                        .replace("？", SPECIAL_DASH);
            }
        }
        return cn;
    }
}
