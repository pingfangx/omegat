package com.pingfangx.omegat.plugin.inspector;

import java.util.List;

/**
 * @author pingfangx
 * @date 2019/3/1
 */
public class TranslationInspectorTest extends BaseInspectorTest {

    @Override
    protected ITranslationInspector getTestInspector() {
        return TranslationInspector.getsInstance();
    }

    @Override
    protected void addTestCase(List<String[]> testCaseList) {
        testCaseList.add(new String[]{"test...", "测试…", "测试..."});
        //测试在英文中标点符号符号前有空格就不替换，但末尾的会替换
        testCaseList.add(new String[]{"test ignore .class files.", "测试忽略.class文件.", "测试忽略 .class 文件。"});
    }
}