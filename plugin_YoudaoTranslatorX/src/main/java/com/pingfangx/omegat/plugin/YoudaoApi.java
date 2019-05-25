package com.pingfangx.omegat.plugin;


import java.util.HashMap;
import java.util.Map;

/**
 * from: https://blog.csdn.net/hujingshuang/article/details/80177784
 * @author pingfangx
 * @date 2019/5/25
 */
public class YoudaoApi {
    public static final String URL = "http://fanyi.youdao.com/translate_o?smartresult=dict&smartresult=rule";

    public static Map<String, String> generateParams(String sourceLang, String targetLang, String text) {
        long now = System.currentTimeMillis();
        String f = String.valueOf(now + (long) (Math.random() * 10 + 1));

        String u = "fanyideskweb";
        String c = "ebSeFb%=XZ%T[KZ)c(sy!";
        String sign = MD5.md5(u + text + f + c);

        Map<String, String> params = new HashMap<>();
        params.put("i", text);
        params.put("from", sourceLang);
        params.put("to", targetLang);
        params.put("smartresult", "dict");
        params.put("client", "fanyideskweb");
        params.put("salt", f);
        params.put("sign", sign);
        params.put("doctype", "json");
        params.put("version", "2.1");
        params.put("keyfrom", "fanyi.web");
        params.put("action", "FY_BY_CLICKBUTTION");
        params.put("typoResult", "false");
        return params;
    }

    public static Map<String, String> generateHeaders() {
        Map<String, String> headers = new HashMap<>();
        long now = System.currentTimeMillis();
        headers.put("Cookie", "OUTFOX_SEARCH_USER_ID_NCOO=1537643834.9570553; OUTFOX_SEARCH_USER_ID=1799185238@10.169.0.83; fanyi-ad-id=43155; fanyi-ad-closed=1; JSESSIONID=aaaBwRanNsqoobhgvaHmw; _ntes_nnid=07e771bc10603d984c2dc8045a293d30,1525267244050; ___rl__test__cookies=" + String.valueOf(now));
        headers.put("Referer", "http://fanyi.youdao.com/");
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
        return headers;
    }
}
