package com.pingfangx.omegat.plugin.inspector;

/**
 * 用于警告的检测器，无法作出修改，只能警告
 */
public abstract class BaseWarningInspector extends BaseInspector {
    @Override
    public String inspect(String en, String cn) {
        String warning = generateWarning(en, cn);
        if (warning != null && warning.length() > 0) {
            return buildWarningResult(en, cn, warning);
        }
        return cn;
    }


    /**
     * 生成警告
     *
     * @return 如果不为 null 则警告
     */
    protected abstract String generateWarning(String en, String cn);

    /**
     * 拼接警告结果
     */
    protected String buildWarningResult(String en, String cn, String warning) {
        return String.format(getWarningResultPattern(), cn, warning);
    }

    /**
     * 警告结果的模板
     */
    protected String getWarningResultPattern() {
        return "%1$s【警告：%2$s】";
    }
}
