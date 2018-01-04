package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/11
 */
public class ReplaceSpecialDashInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new ReplaceSpecialDashInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        String specialDash = ReplaceSpecialDashInspector.SPECIAL_DASH;
        testCaseList.add(new String[]{"test" + specialDash, "测试??", "测试" + specialDash});
        testCaseList.add(new String[]{"test" + specialDash, "测试？", "测试" + specialDash});
    }
}