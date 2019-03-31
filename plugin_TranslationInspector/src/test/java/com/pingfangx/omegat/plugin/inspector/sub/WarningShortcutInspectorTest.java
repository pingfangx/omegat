package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

public class WarningShortcutInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new WarningShortcutInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"test", "测试", "测试"});
        testCaseList.add(new String[]{"test&", "测试", "测试"});
        testCaseList.add(new String[]{"tes&t", "测试", "测试【警告：可能缺少快捷键(&T)】"});
        //多个不处理
        testCaseList.add(new String[]{"&tes&t", "测试", "测试"});
        //忽略的不处理
        testCaseList.add(new String[]{"&shortcut:", "&shortcut:", "&shortcut:"});
    }
}