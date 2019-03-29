package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class KeepSymbolInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new KeepSymbolInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"test...", "测试…", "测试..."});
        testCaseList.add(new String[]{"test...", "测试……", "测试..."});
        testCaseList.add(new String[]{"test...--test", "测试……——测试", "测试...--测试"});
        testCaseList.add(new String[]{"test...", "测试......", "测试..."});
    }
}