package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import org.omegat.core.Core;
import org.omegat.gui.glossary.GlossaryEntry;
import org.omegat.gui.glossary.IGlossaries;
import org.omegat.util.StringUtil;

import java.util.List;

/**
 * 规范 1.2
 * 根据词汇表的术语替换
 * 复用 OmegaT 的词汇文件
 * 词汇格式项应该为
 * 英文	中文	[不使用的翻译|]备注
 * 以 tab 分隔，第 3 列为如果不包启 | 则视为纯备注
 *
 * @author pingfangx
 * @date 2018/5/21
 */
public class ReplaceByGlossaryInspector extends BaseInspector {
    private static ReplaceByGlossaryInspector sInstance;

    private ReplaceByGlossaryInspector() {
    }

    public static ReplaceByGlossaryInspector getInstance() {
        if (sInstance == null) {
            synchronized (ReplaceByGlossaryInspector.class) {
                if (sInstance == null) {
                    sInstance = new ReplaceByGlossaryInspector();
                }
            }
        }
        return sInstance;
    }

    /**
     * 检查
     */
    @Override
    public String inspect(String en, String cn) {
        IGlossaries glossaries = Core.getGlossary();
        if (glossaries != null) {
            //这里只返回显示的，已经匹配了一遍
            List<GlossaryEntry> displayedEntries = glossaries.getDisplayedEntries();
            if (displayedEntries != null) {
                for (GlossaryEntry glossaryEntry : displayedEntries) {
                    cn = inspect(en, cn, glossaryEntry);
                }
            }
        }
        return cn;
    }

    /**
     * 检查
     *
     * @param glossaryEntry 已经显示的匹配项，后面还是判断了是否包含
     */
    public String inspect(String en, String cn, GlossaryEntry glossaryEntry) {
        if (glossaryEntry == null) {
            return cn;
        }

        //英文
        String srcText = glossaryEntry.getSrcText();
        if (StringUtil.isEmpty(srcText)) {
            return cn;
        }

        //如果不包含，直接返回，如果匹配项用的相似匹配，这里可能不包含
        //修改，模糊匹配也可以替换
//        if (!en.contains(srcText)) {
//            return cn;
//        }

        //中文
        String locText = glossaryEntry.getLocText();
        if (StringUtil.isEmpty(locText)) {
            return cn;
        }

        //没有可替换的
        String[] cnWords = locText.split(",");
        if (cnWords.length < 1) {
            return cn;
        }

        String firstCnWord = cnWords[0];

        //注释
        boolean hasTranslation = false;
        String commentText = glossaryEntry.getCommentText();
        if (!StringUtil.isEmpty(commentText) && commentText.contains("|")) {
            //包含 | 才认为有不使用的翻译，否则认为是纯注释
            String[] comments = commentText.split("\\|")[0].split(",");
            //包含单词 srcText
            for (String comment : comments) {
                if (StringUtil.isEmpty(comment)) {
                    continue;
                }
                if (cn.contains(comment)) {
                    //包含不适用的翻译
                    System.out.println(String.format("术语检查：\n【%s】\n【%s】→【%s】", cn, comment, firstCnWord));
                    cn = cn.replace(comment, firstCnWord);
                    hasTranslation = true;
                }
            }
        }
        if (!hasTranslation) {
            for (String cnWord : cnWords) {
                if (cn.contains(cnWord)) {
                    hasTranslation = true;
                    break;
                }
            }
        }
        if (!hasTranslation) {
            System.out.println(String.format("术语检查：\n%s\n%s\n【%s】应该翻译为【%s】", en, cn, srcText, locText));
        }
        return cn;
    }
}
