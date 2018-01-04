package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/5
 */
public class RemoveSymbolSurroundedSpaceInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new RemoveSymbolSurroundedSpaceInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        //斜线
        String en = "I/O";
        testCaseList.add(new String[]{en, "I / O", en});
        testCaseList.add(new String[]{en, "I/ O", en});
        testCaseList.add(new String[]{en, "I /O", en});
        testCaseList.add(new String[]{"test I/O test", "测试 I  /  O 测试", "测试 I/O 测试"});
        //无法替换
        testCaseList.add(new String[]{en, "I / BO", "I / BO"});
        //多个
        testCaseList.add(new String[]{"I/O and O/I", "I / O 与 O / I", "I/O 与 O/I"});

        //连字符
        testCaseList.add(new String[]{"test-test", "test - test", "test-test"});
        testCaseList.add(new String[]{"<c0>try</c0>-with", "<c0>try</c0> - with", "<c0>try</c0>-with"});
        testCaseList.add(new String[]{"test<c0>-try-</c0>with", "test <c0> - try - </c0> with", "test<c0>-try-</c0>with"});
    }
}