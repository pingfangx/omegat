package org.omegat.filters2.html2.tip;

import org.apache.commons.io.FilenameUtils;
import org.omegat.filters2.AbstractFilter;
import org.omegat.filters2.html2.HTMLFilter2;
import org.omegat.filters2.html2.HTMLOptions;
import org.omegat.filters2.html2.HTMLWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用于输出 tips
 */
public class TipHTMLWriter extends HTMLWriter {

    /**
     * 如是是 tips 文件，用来在 body 后添加一行标题
     */
    private static final Pattern HTML_BODY_PATTERN = Pattern.compile("<body.*?>", Pattern.CASE_INSENSITIVE);

    /**
     * 反馈地址
     */
    private static final String FEEDBACK_URL = String.format("<a href='%s'>[%s]</a>", "https://www.pingfangx.com/xx/translation/feedback?from=tips", "汉化反馈");

    /**
     * tips 文件名
     */
    private String tipsFileName;
    /**
     * 翻译过的 tips 文件名
     */
    private String translatedTipsFileName;

    public TipHTMLWriter(String fileName, String encoding, HTMLOptions options) throws FileNotFoundException, UnsupportedEncodingException {
        super(fileName, encoding, options);
    }

    /**
     * 处理文件时，翻译文件名
     */
    public void onProcessFile(AbstractFilter filter) {
        if (tipsFileName != null && translatedTipsFileName == null) {
            if (filter instanceof HTMLFilter2) {
                translatedTipsFileName = ((HTMLFilter2) filter).privateProcessEntry(tipsFileName, null);
            }
        }
    }

    /**
     * 检查是否是 tips 文件，如果是，则会重命名
     */
    @Override
    protected String paresFileName(String fileName) {
        File file = new File(fileName);
        if (file.getName().endsWith(".html")) {
            File parentFile = file.getParentFile();
            if (parentFile.getName().equals("tips")) {
                tipsFileName = FilenameUtils.getBaseName(fileName);
                //是 tips，目录添加上 _zh_CN
                File newParentFile = new File(parentFile.getPath() + "_zh_CN");
                if (!newParentFile.exists()) {
                    newParentFile.mkdirs();
                }
                return new File(newParentFile, file.getName()).getPath();
            }
        }
        return fileName;
    }

    @Override
    protected String parseContentForFinalWrite(String contents) {
        if (tipsFileName != null) {
            //输入 tip 时不需要转义 & ，tip 将在解析时将相关变量替换
            contents = contents.replace("&amp;", "&");

            //添加标题
            Matcher matcherBody = HTML_BODY_PATTERN.matcher(contents);
            if (matcherBody.find()) {
                String translation = translatedTipsFileName;
                if (translation == null) {
                    translation = tipsFileName;
                }
                String addTitle = String.format("\n    <h1>%s(%s) %s</h1>", tipsFileName, translation, FEEDBACK_URL);
                contents = matcherBody.replaceFirst(matcherBody.group() + addTitle);
            }
        }
        return contents;
    }
}
