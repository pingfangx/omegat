package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class KeepOmegaTTagInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new KeepOmegaTTagInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        //正常替换
        testCaseList.add(new String[]{"<p1>test</p1>", "<p1>测试</p1>", "<p1>test</p1>"});
        //可以替换多个，且可以顺序不定
        testCaseList.add(new String[]{"<p1>test</p1><p2>test2</p2>", "<p2>测试2</p2><p1>测试</p1>", "<p2>test2</p2><p1>test</p1>"});
        //不区分 tag 大小写
        testCaseList.add(new String[]{"<p1>test</p1>", "<P1>测试</P1>", "<p1>test</p1>"});
        //替换时的转义
        testCaseList.add(new String[]{"<c0>\\n</c0>", "<c0>\\n</c0>", "<c0>\\n</c0>"});
    }
}