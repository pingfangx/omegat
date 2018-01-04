package com.pingfangx.omegat.plugin;

import org.junit.Test;

/**
 * BaseTranslatorX Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/09/2018</pre>
 */
public class BaseTranslatorXTest {

    /**
     * Method: formatTranslation(String en, String cn)
     */
    @Test
    public void testFormatTranslation() {
        String en = "\"test()\"";
        String cn = "“测试（）”";

        long start = System.currentTimeMillis();
        String result = BaseTranslatorX.inspect(en, cn);
        long end = System.currentTimeMillis();

        System.out.println(String.format("en=%s\ncn=%s\nresult=%s\nspend=%d ms", en, cn, result, end - start));
    }


} 
