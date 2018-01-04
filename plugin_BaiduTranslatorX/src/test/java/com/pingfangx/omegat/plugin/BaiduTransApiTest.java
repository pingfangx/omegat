package com.pingfangx.omegat.plugin;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * BaiduTransApi Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>01/03/2018</pre>
 */
public class BaiduTransApiTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: getTransResult(String query, String from, String to)
     */
    @Test
    public void testGetTransResult() throws Exception {
        String appId = "";
        String securityKey = "";
        BaiduTransApi api = new BaiduTransApi(appId, securityKey);

        String query = "test";
        System.out.println(api.getTransResult(query, "en", "zh"));
    }


}
