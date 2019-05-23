package org.omegat.filters2.html2.tag;

import org.htmlparser.NodeFactory;
import org.htmlparser.Parser;
import org.htmlparser.PrototypicalNodeFactory;

/**
 * @author pingfangx
 * @date 2019/4/12
 */
public class TagUtils {

    /**
     * 在 parser 中注册缺少的标签
     */
    public static void registerTag(Parser parser) {
        NodeFactory nodeFactory = parser.getNodeFactory();
        if (nodeFactory instanceof PrototypicalNodeFactory) {
            PrototypicalNodeFactory prototypicalNodeFactory = (PrototypicalNodeFactory) nodeFactory;
            prototypicalNodeFactory.registerTag(new CodeTag());
            prototypicalNodeFactory.registerTag(new PreTag());
        }
    }
}
