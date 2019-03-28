package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/4
 */
public class ReplaceSymbolInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new ReplaceSymbolInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        String[] enSymbols = PatternConstants.EN_SYMBOLS;
        String[] cnSymbols = PatternConstants.CN_SYMBOLS;
        for (int i = 0; i < enSymbols.length; i++) {
            testCaseList.add(new String[]{"test" + enSymbols[i] + "test", "测试" + enSymbols[i] + "测试", "测试" + cnSymbols[i] + "测试"});
        }
        //测试多个
        String enSymbol = enSymbols[0];
        String cnSymbol = cnSymbols[0];
        testCaseList.add(new String[]{"test" + enSymbol + "test" + enSymbol + "test", "测试" + enSymbol + "测" + enSymbol + "测试", "测试" + cnSymbol + "测" + cnSymbol + "测试"});
        //测试省略号不替换
        testCaseList.add(new String[]{"test...", "测试...", "测试..."});
    }
}