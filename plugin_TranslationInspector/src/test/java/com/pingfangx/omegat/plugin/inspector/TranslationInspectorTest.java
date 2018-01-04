package com.pingfangx.omegat.plugin.inspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class TranslationInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return TranslationInspector.getsInstance();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {

    }
}