package com.pingfangx.omegat.plugin.inspector;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public abstract class BaseInspectorTest {
    @Test
    public void testInspector() {
        ITranslationInspector inspector = getTestInspector();
        if (inspector == null) {
            return;
        }
        List<String[]> testCaseList = new ArrayList<>();
        addTestCase(testCaseList);
        testCaseList.forEach(strings -> {
            if (strings != null && strings.length == 3) {
                System.out.println(String.format("检查 【%s】【%s】，期望【%s】", strings[0], strings[1], strings[2]));
                Assert.assertEquals(strings[2], inspector.inspect(strings[0], strings[1]));
            }
        });
    }

    protected abstract ITranslationInspector getTestInspector();

    /**
     * 添加测试用例
     * 每个 String[] 含 3 个元素，测试英文，测试中文，期望输出
     */
    protected abstract void addTestCase(List<String[]> testCaseList);


}