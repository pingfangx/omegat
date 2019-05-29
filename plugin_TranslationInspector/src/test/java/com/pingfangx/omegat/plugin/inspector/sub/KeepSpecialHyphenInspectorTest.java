package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

/**
 * @author pingfangx
 * @date 2019/5/29
 */
public class KeepSpecialHyphenInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new KeepSpecialHyphenInspector();
    }

    @Override
    protected void addTestCase() {
        super.addTestCase();
        addTestCase("test \u0096 test", "测试??测试", "测试 \u0096 测试");
        addTestCase("test \u0096 test", "测试 ??测试", "测试 \u0096 测试");
        addTestCase("test \u0096 test", "测试?? 测试", "测试 \u0096 测试");
        addTestCase("test \u0096 test", "测试 ?? 测试", "测试 \u0096 测试");

        //不包含，不处理
        addTestCase("test ?? test", "测试??测试", "测试??测试");
        //中间空格，不处理
        addTestCase("test \u0096 test", "测试 ? ? 测试", "测试 ? ? 测试");
    }
}