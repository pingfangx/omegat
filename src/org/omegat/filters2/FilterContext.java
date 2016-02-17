/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2010 Alex Buloichik
               Home page: http://www.omegat.org/
               Support center: http://groups.yahoo.com/group/OmegaT/

 This file is part of OmegaT.

 OmegaT is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 OmegaT is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **************************************************************************/

package org.omegat.filters2;

import org.omegat.core.data.ProjectProperties;
import org.omegat.util.Language;

/**
 * Context for filter calls.
 */
public class FilterContext {

    private final Language sourceLang;

    private final Language targetLang;

    private String inEncoding;

    private String outEncoding;

    private boolean sentenceSegmentingEnabled;

    private boolean isRemoveAllTags;

    public FilterContext(ProjectProperties props) {
        this.sourceLang = props.getSourceLanguage();
        this.targetLang = props.getTargetLanguage();
        this.sentenceSegmentingEnabled = props.isSentenceSegmentingEnabled();
        this.isRemoveAllTags = props.isRemoveTags();
    }

    public FilterContext(Language sourceLang, Language targetLang, boolean sentenceSegmentingEnabled) {
        this.sourceLang = sourceLang;
        this.targetLang = targetLang;
        this.sentenceSegmentingEnabled = sentenceSegmentingEnabled;
        this.isRemoveAllTags = false;
    }

    /** Source language of project. */
    public Language getSourceLang() {
        return sourceLang;
    }

    /** Target language of project. */
    public Language getTargetLang() {
        return targetLang;
    }

    /** Source file encoding, but can be 'null'. */
    public String getInEncoding() {
        return inEncoding;
    }

    public FilterContext setInEncoding(String inEncoding) {
        this.inEncoding = inEncoding;
        return this;
    }

    /** Target file encoding, but can be 'null'. */
    public String getOutEncoding() {
        return outEncoding;
    }

    public FilterContext setOutEncoding(String outEncoding) {
        this.outEncoding = outEncoding;
        return this;
    }

    /** Is sentence segmenting enabled. */
    public boolean isSentenceSegmentingEnabled() {
        return sentenceSegmentingEnabled;
    }

    /** Should all tags be removed from segments */
    public boolean isRemoveAllTags() {
        return isRemoveAllTags;
    }
}
