package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

public class RemoveUntranslatedTagSpaceInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new RemoveUntranslatedTagSpaceInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"<code>test</code>", "<code> test </code>", "<code>test</code>"});
    }
}