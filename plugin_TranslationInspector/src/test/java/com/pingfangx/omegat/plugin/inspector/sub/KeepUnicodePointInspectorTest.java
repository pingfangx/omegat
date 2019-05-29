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
    }
}