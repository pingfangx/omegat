/**************************************************************************
 OmegaT - Computer Assisted Translation (CAT) tool
          with fuzzy matching, translation memory, keyword search,
          glossaries, and translation leveraging into updated projects.

 Copyright (C) 2000-2006 Keith Godfrey and Maxym Mykhalchuk
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

package org.omegat.core.matching;

import org.omegat.core.data.StringData;
import org.omegat.tokenizer.DefaultTokenizer;
import org.omegat.util.Token;

/**
 * The class, responsible for building the list of fuzzy matches between the
 * source text strings.
 *
 * @author Maxym Mykhalchuk
 */
public final class FuzzyMatcher {

    private FuzzyMatcher() {
    }

    /**
     * Builds the similarity data for color highlight in match window.
     */
    public static byte[] buildSimilarityData(Token[] sourceTokens, Token[] matchTokens) {
        int len = matchTokens.length;
        byte[] result = new byte[len];

        boolean leftfound = true;
        for (int i = 0; i < len; i++) {
            result[i] = 0;

            Token righttoken = null;
            if (i + 1 < len) {
                righttoken = matchTokens[i + 1];
            }
            boolean rightfound = (i + 1 == len) || DefaultTokenizer.isContains(sourceTokens, righttoken);

            Token token = matchTokens[i];
            //不要合并
            //为了模糊更精确的显示差异，比较位置是否相同，否则如果有 a the 这样的,可能在整个句子中 contain ,会被错误地判为相同
            //改子之后会导致不能正确表现出缺少某个词但后面相同的情况,但是该方法只在显示模糊匹配结果时才调用，不影响其他逻辑
            //boolean found = DefaultTokenizer.isContains(sourceTokens, token);
            boolean found = false;
            if (i < sourceTokens.length) {
                found = sourceTokens[i].equals(token);
            }

            if (found && (!leftfound || !rightfound)) {
                result[i] = StringData.PAIR;
            } else if (!found) {
                result[i] = StringData.UNIQ;
            }

            leftfound = found;
        }
        return result;
    }

    /**
     * Calculate similarity for tokens arrays(percent).
     *
     * @param str
     *            original string tokens
     * @param cand
     *            candidate string tokens
     * @return similarity in percents
     */
    public static int calcSimilarity(final ISimilarityCalculator distanceCalculator, final Token[] str,
            final Token[] cand) {
        if (str.length == 0 && cand.length == 0) {
            // empty token lists - can't calculate similarity
            return 0;
        }
        int ld = distanceCalculator.compute(str, cand);
        int similarity = (100 * (Math.max(str.length, cand.length) - ld)) / Math.max(str.length, cand.length);
        return similarity;
    }
}
