package org.omegat.filters2.html2.tag;

import org.htmlparser.tags.CompositeTag;

/**
 * @author pingfangx
 * @date 2019/5/23
 */
public class PreTag extends CompositeTag {
    private static final long serialVersionUID = 205771241317102485L;
    private static final String[] mIds = new String[]{"PRE"};

    public PreTag() {
    }

    public String[] getIds() {
        return (mIds);
    }
}
