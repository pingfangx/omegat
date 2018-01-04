package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;
import com.pingfangx.omegat.plugin.inspector.util.PatternConstants;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class ReplaceEndSymbolInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new ReplaceEndSymbolInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        //末尾有或者没有，都替换
        testCaseList.add(new String[]{"test.", "测试", "测试。"});
        testCaseList.add(new String[]{"test.", "测试.", "测试。"});
        testCaseList.add(new String[]{"test.", "测试。", "测试。"});
        //错误添回
        testCaseList.add(new String[]{"test.", "测试?", "测试?。"});
        //不同符号
        String[] enSymbols = PatternConstants.EN_SYMBOLS;
        String[] cnSymbols = PatternConstants.CN_SYMBOLS;
        for (int i = 0; i < enSymbols.length; i++) {
            testCaseList.add(new String[]{"test" + enSymbols[i], "测试" + enSymbols[i], "测试" + cnSymbols[i]});
        }
    }
}