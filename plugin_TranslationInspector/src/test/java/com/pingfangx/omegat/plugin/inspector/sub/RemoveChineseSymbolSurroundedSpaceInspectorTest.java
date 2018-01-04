package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/6
 */
public class RemoveChineseSymbolSurroundedSpaceInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new RemoveChineseSymbolSurroundedSpaceInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        for (String cnSymbol : PatternConstants.CN_SYMBOLS) {
            testCaseList.add(new String[]{"", "test " + cnSymbol, "test" + cnSymbol});
        }
    }
}