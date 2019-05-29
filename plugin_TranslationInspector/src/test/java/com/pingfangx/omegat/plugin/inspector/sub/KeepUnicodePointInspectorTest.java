package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

/**
 * @author pingfangx
 * @date 2019/5/29
 */
public class KeepUnicodePointInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new KeepUnicodePointInspector();
    }

    @Override
    protected void addTestCase() {
        addTestCase("U+0000", "U +0000", "U+0000");
        addTestCase("U+0000", "U+ 0000", "U+0000");
        addTestCase("U+0000", "U+0000", "U+0000");
        addTestCase("U+0000U+FfFf", "U + 0000U + FfFf", "U+0000U+FfFf");
        //前后空格
        addTestCase("Test U+0000.", "测试U +0000。", "测试 U+0000。");

        addTestCase("\\u0000", "\\ u0000", "\\u0000");
        addTestCase("\\u0000", "\\u 0000", "\\u0000");
        addTestCase("\\u0000", "\\u0000", "\\u0000");
        addTestCase("\\u0000\\uFfFf", "\\ u 0000\\ u FfFf", "\\u0000\\uFfFf");
        addTestCase("Test \\u0000.", "测试\\u 0000。", "测试 \\u0000。");
    }
}