package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class KeepParenthesesInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new KeepParenthesesInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"(test)", "（测试）", "(测试)"});
        testCaseList.add(new String[]{"(test)", "（测试)", "(测试)"});
        //不替换
        testCaseList.add(new String[]{"（test）", "（测试）", "（测试）"});
    }
}