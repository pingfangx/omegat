package org.omegat.filters2.html2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author pingfangx
 * @date 2019/1/8
 */
public class HTMLUtilsTest {

    @Test
    public void testParseGenerics() {
        //正确替换
        Map<String, String> testCaseMap = new LinkedHashMap<>();
        testCaseMap.put("List<?>", "List&lt;?&gt;");
        testCaseMap.put("List<? extends A", "List&lt;? extends A");
        testCaseMap.put("List<? super A", "List&lt;? super A");
        //空格不影响
        testCaseMap.put("List<?  super  A  ", "List&lt;?  super  A  ");
        // ... 也可以
        testCaseMap.put("List<? extends ...", "List&lt;? extends ...");
        // 多个不影响
        testCaseMap.put("Map<? super T,? extends R", "Map&lt;? super T,? extends R");
        //标签也规换
        testCaseMap.put("List<? <b2>extends</b2> A", "List&lt;? <b2>extends</b2> A");
        testCaseMap.forEach((s, s2) -> Assert.assertEquals(s2, HTMLUtils.parseGenerics(s)));

        //不合法形式，不替换
        List<String> illegalFormList = new ArrayList<>();
        //单个 <?> 不能包含空格
        illegalFormList.add("List<? >");
        illegalFormList.add("List< ?>");
        illegalFormList.add("List< ? >");
        //前面不能有空格，不符合 processing instruction
        illegalFormList.add("List< ? extends A");
        illegalFormList.forEach(s -> Assert.assertEquals(s, HTMLUtils.parseGenerics(s)));
    }
}