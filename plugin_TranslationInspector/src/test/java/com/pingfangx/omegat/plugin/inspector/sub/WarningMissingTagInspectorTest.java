package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

public class WarningMissingTagInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new WarningMissingTagInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"<p1>test</p1>", "<p1>测试<p1>", "<p1>测试<p1>【警告：缺少标签[</p1>]】"});
        testCaseList.add(new String[]{"<p1>test</p1>", "测试", "测试【警告：缺少标签[<p1>, </p1>]】"});
        testCaseList.add(new String[]{"<p1>test</p1>", "<p1>测试</P1>", "<p1>测试</P1>【警告：标签不正确[</P1>]】"});
    }
}