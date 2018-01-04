package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspectorTest;
import com.pingfangx.omegat.plugin.inspector.ITranslationInspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class RemoveTagSpaceInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return new RemoveTagSpaceInspector();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        //移除标签中的空格
        testCaseList.add(new String[]{"<p1>test</p1>", "< p 1 > 测试 < / p 1 >", "<p1> 测试 </p1>"});
        testCaseList.add(new String[]{"<p1>te<st</p1>", "< p 1 > 测<试 < / p 1 >", "<p1> 测<试 </p1>"});
    }
}