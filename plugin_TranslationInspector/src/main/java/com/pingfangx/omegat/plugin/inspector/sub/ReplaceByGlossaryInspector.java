package com.pingfangx.omegat.plugin.inspector.sub;

import com.pingfangx.omegat.plugin.inspector.BaseInspector;
import org.omegat.core.Core;
import org.omegat.gui.glossary.GlossaryEntry;
import org.omegat.gui.glossary.IGlossaries;
import org.omegat.util.StringUtil;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            //因为正则可能包含 | 取最后一个
            String[] comments = commentText.substring(0, commentText.lastIndexOf("|")).split(",");
            //包含单词 srcText
            for (String comment : comments) {
                if (StringUtil.isEmpty(comment)) {
                    continue;
                }
                //包含不适用的翻译
                boolean replace = cn.contains(comment);
                if (!comment.contains(firstCnWord)) {
                    //如果不使用的翻译不包含要使用的翻译，那么如果中文中已经有正确的翻译了，就不替换
                    //避免可能包含正确的翻译，但恰好存在另一个词语，其翻译为不适用的词
                    //比如 test experiment 测试实验，不能因为包含实验就替换，而是包含测试就不再替换
                    replace &= !cn.contains(firstCnWord);
                }
                if (replace) {
                    System.out.println(String.format("术语检查：\n【%s】\n【%s】→【%s】", cn, comment, firstCnWord));
                    cn = cn.replace(comment, firstCnWord);
                    hasTranslation = true;
                }
                //添加正则支持，一般正则不会直接存在于译文中，所以前面的查找替换也保留
                Matcher matcher = Pattern.compile(comment).matcher(cn);
                if (matcher.find()) {
                    //不完整，因为 replaceAll 可能替换别的情况
                    String find = matcher.group();
                    cn = matcher.replaceAll(firstCnWord);
                    System.out.println(String.format("术语检查：\n【%s】\n【%s】：【%s】→【%s】", cn, comment, find, firstCnWord));
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