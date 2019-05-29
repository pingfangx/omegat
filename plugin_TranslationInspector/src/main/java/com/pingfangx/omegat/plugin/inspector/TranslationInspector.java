package com.pingfangx.omegat.plugin.inspector;

import com.pingfangx.omegat.plugin.inspector.sub.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * 翻译检查，容器类，可添加多个检测器
 *
 * @author pingfangx
 * @date 2019/1/14
 */
public class TranslationInspector extends BaseInspector {
    private static TranslationInspector sInstance;
    private List<ITranslationInspector> mInspectorList;

    private TranslationInspector() {
        mInspectorList = new ArrayList<>();

        addDefaultInspector();
    }

    public static TranslationInspector getsInstance() {
        if (sInstance == null) {
            synchronized (TranslationInspector.class) {
                if (sInstance == null) {
                    sInstance = new TranslationInspector();
                }
            }
        }
        return sInstance;
    }

    /**
     * 添加默认的检查器
     */
    protected void addDefaultInspector() {
        //敬语
        addInspector(new ReplaceToHonorificsInspector());
        //括号
        addInspector(new KeepParenthesesInspector());
        //破折号
        addInspector(new ReplaceSpecialDashInspector());
        //标点符号
        addInspector(new KeepSymbolInspector());
        addInspector(new ReplaceSymbolInspector());
        addInspector(new ReplaceEndSymbolInspector());
        addInspector(new RemoveChineseSymbolSurroundedSpaceInspector());
        //移除标签周围的空格
        addInspector(new RemoveTagSpaceInspector());
        //斜体继承了 KeepOmegaTTagInspector
        addInspector(new AssembleItalicTagInspector());
        //addInspector(new KeepOmegaTTagInspector());
        //移除符号周围的空格，放到移除标签周围的空格之后
        addInspector(new RemoveSymbolSurroundedSpaceInspector());
        //移除未翻译标签的空格
        addInspector(new RemoveUntranslatedTagSpaceInspector());
        //空格
        addInspector(new AddSpaceInspector());

        //特殊连字符，放于添加空格后
        addInspector(new KeepSpecialHyphenInspector());

        //术语，放到最后，因为可能标签中有文字，让前面的检查器处理完再进行替换
        addInspector(ReplaceByGlossaryInspector.getInstance());

        //以下为警告
        addInspector(new WarningMissingTagInspector());
        addInspector(new WarningShortcutInspector());
    }

    /**
     * 添加检查器
     * 添加时不判断 enable 可能动态设置，迭代时再判断
     */
    public void addInspector(@NotNull ITranslationInspector inspector) {
        mInspectorList.add(inspector);
    }

    @Override
    public String inspect(String en, String cn) {
        boolean printSource = false;
        if (mInspectorList != null && mInspectorList.size() > 0) {
            for (ITranslationInspector inspector : mInspectorList) {
                if (inspector.isEnable()) {
                    String original = cn;
                    cn = inspector.inspect(en, cn);
                    if (!cn.equals(original)) {
                        if (!printSource) {
                            printSource = true;
                            logDebug("检查：\n%s\n%s", en, original);
                        }
                        logDebug("%s:【%s】-> 【%s】", inspector.getClass().getSimpleName(), original, cn);
                    }
                }
            }
        }
        return cn;
    }
}
