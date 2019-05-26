package com.pingfangx.omegat.plugin;

import org.omegat.core.machinetranslators.MachineTranslators;
import org.omegat.gui.exttrans.MTConfigDialog;
import org.omegat.util.JsonParser;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * @author pingfangx
 * @date 2018/1/3
 */
public class BaiduTranslatorX extends BaseTranslatorX {
    private static final String NAME = "百度翻译X";
    private static final String ALLOW_BAIDU_TRANSLATE_X = "allow_baidu_translate_x";
    private static final String PROPERTY_BAIDU_APP_ID = "baidu.app.id";
    private static final String PROPERTY_BAIDU_SECURITY_KEY = "baidu.security.key";

    @Override
    protected String getPreferenceName() {
        return ALLOW_BAIDU_TRANSLATE_X;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    protected String getTranslationFromNet(String sourceLang, String targetLang, String text) {
        String appId = getCredential(PROPERTY_BAIDU_APP_ID);
        if (appId == null || appId.isEmpty()) {
            return "need config " + PROPERTY_BAIDU_APP_ID;
        }
        String securityKey = getCredential(PROPERTY_BAIDU_SECURITY_KEY);
        if (securityKey == null || securityKey.isEmpty()) {
            return "need config " + PROPERTY_BAIDU_SECURITY_KEY;
        }
        BaiduTransApi baiduTransApi = new BaiduTransApi(appId, securityKey);
        return baiduTransApi.getTransResult(text, sourceLang, targetLang);
    }

    /**
     * 解析
     * {"from":"en","to":"zh","trans_result":[{"src":"test","dst":"\u6d4b\u8bd5"}]}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected String getJsonResults(String result) {
        if (result == null) {
            return null;
        }
        try {
            Map<String, Object> rootNode = (Map<String, Object>) JsonParser.parse(result);
            List<Object> translationsList = (List<Object>) rootNode.get("trans_result");
            Map<String, String> translationNode = (Map<String, String>) translationsList.get(0);
            return translationNode.get("dst");
        } catch (Exception e) {
            return BaseTranslatorUtils.processException(e);
        }
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public void showConfigurationUI(Window parent) {
        MTConfigDialog dialog = new MTConfigDialog(parent, getName()) {
            @Override
            protected void onConfirm() {
                boolean temporary = panel.temporaryCheckBox.isSelected();
                String appId = panel.valueField1.getText().trim();
                setCredential(PROPERTY_BAIDU_APP_ID, appId, temporary);
                String securityKey = panel.valueField2.getText().trim();
                setCredential(PROPERTY_BAIDU_SECURITY_KEY, securityKey, temporary);
            }
        };
        dialog.panel.valueLabel1.setText(PROPERTY_BAIDU_APP_ID);
        dialog.panel.valueField1.setText(getCredential(PROPERTY_BAIDU_APP_ID));
        dialog.panel.valueLabel2.setText(PROPERTY_BAIDU_SECURITY_KEY);
        dialog.panel.valueField2.setText(getCredential(PROPERTY_BAIDU_SECURITY_KEY));
        dialog.panel.temporaryCheckBox.setSelected(isCredentialStoredTemporarily(PROPERTY_BAIDU_APP_ID));
        dialog.show();
    }

    @SuppressWarnings("unused")
    public static void loadPlugins() {
        MachineTranslators.add(new BaiduTranslatorX());
    }

    @SuppressWarnings("unused")
    public static void unloadPlugins() {
    }
}
