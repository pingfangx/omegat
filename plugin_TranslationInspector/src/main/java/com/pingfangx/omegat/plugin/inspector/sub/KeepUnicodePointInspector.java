package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseRegexInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * U+ 形式的 Unicode 代码点被添加空格，进行还原
 *
 * @author pingfangx
 * @date 2019/5/29
 */
public class KeepUnicodePointInspector extends BaseRegexInspector {
    public KeepUnicodePointInspector() {
        //虽然不判断英文中是否有，直接替换不太好，但是省事就这样了
        super(PatternConstants.UNICODE_POINT_PATTERN, PatternConstants.UNICODE_POINT_REPLACEMENT);
    }
}
