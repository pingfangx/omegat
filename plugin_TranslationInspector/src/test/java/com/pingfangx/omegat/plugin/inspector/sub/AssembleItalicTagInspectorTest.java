package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class AssembleItalicTagInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new AssembleItalicTagInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        //无斜体，父类处理，保留标签内容
        testCaseList.add(new String[]{"<p1>test</p1>", "<p1>测试</p1>", "<p1>test</p1>"});
        //正常处理
        testCaseList.add(new String[]{"<i1>test</i1>", "<i1>测试</i1>", "<i1>test (测试)</i1>"});
        //不区分大写小，因为翻译可能会错误修改标签大小写
        testCaseList.add(new String[]{"<i1>test</i1>", "<I1>测试</I1>", "<i1>test (测试)</i1>"});
        //序号可以任意，可能不只一个
        testCaseList.add(new String[]{"<i123>test</i123>", "<i123>测试</i123>", "<i123>test (测试)</i123>"});
    }
}