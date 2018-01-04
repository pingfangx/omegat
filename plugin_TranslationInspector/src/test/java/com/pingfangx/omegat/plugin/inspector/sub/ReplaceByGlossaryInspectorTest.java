package com.pingfangx.omegat.plugin.inspector.sub;

import org.junit.Test;
import org.omegat.gui.glossary.GlossaryEntry;

/**
 * GlossaryInspector Tester.
 *
 * @author pingfangx
 * @version 1.0
 * @since <pre>05/21/2018</pre>
 */
public class ReplaceByGlossaryInspectorTest {

    @Test
    public void testInspect() {
        GlossaryEntry glossaryEntry = new GlossaryEntry("change", "更改,变更", "改变,如果名词性不强,就使用“变更”", true, "");
        ReplaceByGlossaryInspector.getInstance().inspect("test change", "测试改变", glossaryEntry);
        ReplaceByGlossaryInspector.getInstance().inspect("test change", "测试修改", glossaryEntry);
    }


} 
