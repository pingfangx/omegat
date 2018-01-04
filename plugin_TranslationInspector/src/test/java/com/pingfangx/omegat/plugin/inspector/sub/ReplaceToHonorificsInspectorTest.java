package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class ReplaceToHonorificsInspectorTest extends BaseInspectorTest {


    @Override
    protected ITranslationInspector getTestInspector() {
        return new ReplaceToHonorificsInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"", "你你们好", "您你们好"});
    }
}