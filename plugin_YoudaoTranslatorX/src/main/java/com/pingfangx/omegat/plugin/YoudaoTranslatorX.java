package com.pingfangx.omegat.plugin;

import org.omegat.core.machinetranslators.MachineTranslators;
import org.omegat.util.JsonParser;
import org.omegat.util.WikiGet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 有道翻译
 *
 * @author pingfangx
 * @date 2019/5/25
 */
public class YoudaoTranslatorX extends BaseTranslatorX {
    private static final String NAME = "有道翻译X";
    private static final String ALLOW_YOUDAO_TRANSLATE_X = "allow_youdao_translate_x";

    @Override
    protected String getTranslationFromNet(String sourceLang, String targetLang, String text) {
        String url = YoudaoApi.URL;
        Map<String, String> params = YoudaoApi.generateParams(sourceLang, targetLang, text);
        Map<String, String> headers = YoudaoApi.generateHeaders();
        try {
            return WikiGet.post(url, params, headers);
        } catch (IOException e) {
            return BaseTranslatorUtils.processException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected String getJsonResults(String translation) {
        if (translation == null) {
            return null;
        }
        try {
            Map<String, Object> rootNode = (Map<String, Object>) JsonParser.parse(translation);
            List<Object> translationsList = (List<Object>) rootNode.get("translateResult");
            translationsList = (List<Object>) translationsList.get(0);
            Map<String, String> translationNode = (Map<String, String>) translationsList.get(0);
            return translationNode.get("tgt");
        } catch (Exception e) {
            return BaseTranslatorUtils.processException(e);
        }
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected String getPreferenceName() {
        return ALLOW_YOUDAO_TRANSLATE_X;
    }

    @SuppressWarnings("unused")
    public static void loadPlugins() {
        MachineTranslators.add(new YoudaoTranslatorX());
    }

    @SuppressWarnings("unused")
    public static void unloadPlugins() {
    }
}
