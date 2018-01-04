package com.pingfangx.omegat.plugin;

import com.pingfangx.omegat.plugin.inspector.TranslationInspector;
import org.omegat.core.machinetranslators.BaseTranslate;
import org.omegat.util.Language;

/**
 * @author pingfangx
 * @date 2018/1/9
 */
public abstract class BaseTranslatorX extends BaseTranslate {

    @Override
    protected String translate(Language sLang, Language tLang, String text) {
        //截断
        text = text.length() > 5000 ? text.substring(0, 4997) + "..." : text;

        //缓存
        String prev = getFromCache(sLang, tLang, text);
        if (prev != null) {
            return prev;
        }

        //翻译
        String sourceLang = sLang.getLanguageCode().substring(0, 2).toLowerCase();
        String targetLang = tLang.getLanguageCode().substring(0, 2).toLowerCase();
        String result = translate(sourceLang, targetLang, text);
        System.out.println(getName() + " 翻译结果：" + result);
        //检查
        result = inspect(text, result);
        if (result == null || result.equals("")) {
            return "";
        }
        putToCache(sLang, tLang, text, result);
        return result;
    }

    /**
     * 格式化翻译后的结果
     * 现在就是检查功能
     */
    public static String inspect(String en, String cn) {
        // java 实现的检查
        return TranslationInspector.getsInstance().inspect(en, cn);
    }

    /**
     * 除缓存处理外，直接翻译
     * 处理翻译前后的处理
     */
    public String translate(String sourceLang, String targetLang, String text) {
        return getJsonResults(getTranslationFromNet(sourceLang, targetLang, text));
    }


    /**
     * 从网络获取翻译
     */
    protected String getTranslationFromNet(String sourceLang, String targetLang, String text) {
        return text;
    }

    /**
     * 解析网络获取的翻译结果
     */
    protected String getJsonResults(String translation) {
        return translation;
    }

}
