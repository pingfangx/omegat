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

    private List<TestCase> mTestCaseList;

    @Test
    public void testInspector() {
        ITranslationInspector inspector = getTestInspector();
        if (inspector == null) {
            return;
        }
        //旧版
        List<String[]> testCaseList = new ArrayList<>();
        addTestCase(testCaseList);
        testCaseList.forEach(strings -> {
            if (strings != null && strings.length == 3) {
                System.out.println(String.format("检查 【%s】【%s】，期望【%s】", strings[0], strings[1], strings[2]));
                Assert.assertEquals(strings[2], inspector.inspect(strings[0], strings[1]));
            }
        });

        //新版
        mTestCaseList = new ArrayList<>();
        addTestCase();
        mTestCaseList.forEach(testCase -> {
            System.out.println(String.format("检查 【%s】【%s】，期望【%s】", testCase.en, testCase.cn, testCase.expected));
            Assert.assertEquals(testCase.expected, inspector.inspect(testCase.en, testCase.cn));
        });
    }

    protected abstract ITranslationInspector getTestInspector();

    /**
     * 添加测试用例
     * 每个 String[] 含 3 个元素，测试英文，测试中文，期望输出
     *
     * @param testCaseList 往该 list 中添加测试用例即可
     */
    protected void addTestCase(List<String[]> testCaseList) {
    }

    /**
     * 添加测试用例，可调用 {@link #addTestCase(String, String, String)}
     */
    protected void addTestCase() {
    }

    /**
     * 添加测试用例
     *
     * @param en       英文
     * @param cn       中文
     * @param expected 期望
     */
    public void addTestCase(String en, String cn, String expected) {
        mTestCaseList.add(new TestCase(en, cn, expected));
    }

    private class TestCase {
        private String en;
        private String cn;
        private String expected;

        public TestCase(String en, String cn, String expected) {
            this.en = en;
            this.cn = cn;
            this.expected = expected;
        }
    }
}