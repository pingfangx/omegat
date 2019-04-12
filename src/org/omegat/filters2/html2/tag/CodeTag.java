package org.omegat.filters2.html2.tag;

import org.htmlparser.tags.CompositeTag;

/**
 * @author pingfangx
 * @date 2019/4/12
 */
public class CodeTag extends CompositeTag {
    private static final long serialVersionUID = -7472516327664183816L;
    private static final String[] mIds = new String[]{"CODE"};

    public CodeTag() {
    }

    public String[] getIds() {
        return (mIds);
    }
}
