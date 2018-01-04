package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseRegexInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

/**
 * 规范 1.1
 * 替换为敬语
 * 你 > 您
 * 但 你们 保留
 *
 * @author pingfangx
 * @date 2019/3/1
 */
public class ReplaceToHonorificsInspector extends BaseRegexInspector {

    public ReplaceToHonorificsInspector() {
        super(PatternConstants.HONORIFICS_PATTERN, PatternConstants.HONORIFICS_REPLACEMENT);
    }
}
