package com.pingfangx.omegat.plugin;

import org.omegat.core.machinetranslators.MachineTranslators;
import org.omegat.util.JsonParser;
import org.omegat.util.WikiGet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author pingfangx
 * @date 2018/1/3
 */
public class GoogleTranslatorX extends BaseTranslatorX {
    private static final String NAME = "谷歌翻译X";
    private static final String ALLOW_GOOGLE_TRANSLATE_X = "allow_google_translate_x";

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected String getPreferenceName() {
        return ALLOW_GOOGLE_TRANSLATE_X;
    }

    @Override
    protected String getTranslationFromNet(String sourceLang, String targetLang, String text) {
        try {
            String tk = GoogleTk.getTk(text);
            String query = URLEncoder.encode(text, "utf-8");
            String url = String.format("https://translate.google.cn/translate_a/single?client=t" +
                    "&sl=%s&tl=%s&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca" +
                    "&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&clearbtn=1&otf=1&pc=1" +
                    "&srcrom=0&ssel=0&tsel=0&kc=2&tk=%s&q=%s", sourceLang, targetLang, tk, query);
            Map<String, String> headers = new TreeMap<>();
            headers.put("X-HTTP-Method-Override", "GET");
            headers.put("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; en-US; rv:1.9.1.6) Gecko/20091201 Firefox/3.5.6");
            return WikiGet.post(url, null, headers);
        } catch (IOException e) {
            return BaseTranslatorUtils.processException(e);
        }
    }

    /**
     * 解析谷歌翻译的结果
     * [[["测试","test",null,null,2],[null,null,"Cèshì","test"]],[["名词",...
     */
    @Override
    @SuppressWarnings("unchecked")
    protected String getJsonResults(String result) {
        if (result == null) {
            return null;
        }
        StringBuilder translation = new StringBuilder();
        try {
            //有多个列表，后面的列表还词性等
            List<Object> translationList = (List<Object>) JsonParser.parse(result);
            //取第一个列表，包含多个列表
            List<Object> firstTranslationList = (List<Object>) translationList.get(0);
            //遍历每一个列表
            for (Object o : firstTranslationList) {
                //每一个列表可能为翻译，拼音等
                List<Object> translationValue = (List<Object>) o;
                if (translationValue.size() == 5) {
                    //大小为 5 的表示翻译，可能需要拼接
                    translation.append(translationValue.get(0));
                }
            }
            return translation.toString();
        } catch (Exception e) {
            return BaseTranslatorUtils.processException(e);
        }
    }

    @SuppressWarnings("unused")
    public static void loadPlugins() {
        MachineTranslators.add(new GoogleTranslatorX());
    }

    @SuppressWarnings("unused")
    public static void unloadPlugins() {
    }
}

