package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class AddSpaceInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new AddSpaceInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"", "你好test测试", "你好 test 测试"});
        testCaseList.add(new String[]{"", "你好 test测试", "你好 test 测试"});
        testCaseList.add(new String[]{"", "你好test 测试", "你好 test 测试"});
        //不同符号
        testCaseList.add(new String[]{"", "测a0试", "测 a0 试"});
        testCaseList.add(new String[]{"", "你[]们", "你 [] 们"});
        testCaseList.add(new String[]{"", "你%们", "你 % 们"});
        testCaseList.add(new String[]{"", "你''们", "你 '' 们"});
        testCaseList.add(new String[]{"", "你<>们", "你 <> 们"});
        testCaseList.add(new String[]{"", "你{}们", "你 {} 们"});
    }
}