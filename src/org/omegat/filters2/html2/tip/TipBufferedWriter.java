package org.omegat.filters2.html2.tip;

import org.jetbrains.annotations.NotNull;
import org.omegat.filters2.AbstractFilter;

import java.io.BufferedWriter;
import java.io.Writer;

/**
 * 用于持用 TipHTMLWriter
 */
public class TipBufferedWriter extends BufferedWriter {
    private TipHTMLWriter tipHTMLWriter;

    public TipBufferedWriter(@NotNull Writer out) {
        super(out);
        if (out instanceof TipHTMLWriter) {
            tipHTMLWriter = (TipHTMLWriter) out;
        }
    }

    public void onProcessFile(AbstractFilter filter) {
        if (tipHTMLWriter != null) {
            tipHTMLWriter.onProcessFile(filter);
        }
    }
}
