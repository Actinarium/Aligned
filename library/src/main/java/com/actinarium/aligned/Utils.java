/*
 * Copyright (C) 2015 Actinarium
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.actinarium.aligned;

import android.graphics.Paint;

/**
 * Utility methods for modifying existing views
 *
 * @author Paul Danyliuk
 */
public final class Utils {

    private Utils() {}

    /**
     * Sets exact metrics to a TextView (and apparently its subclasses like EditText, however sanity is not guaranteed
     * there), making it much easier to lay down on Material spec grid. Will alter top and bottom padding of the view
     * and its lineSpacingExtra. Be aware of possible clipping if first line leading or last line descent are too small
     * to accommodate the characters.
     *
     * @param view             TextView to adjust
     * @param firstLineLeading required leading for the first line. In general case try to keep it &gt;= interline
     *                         leading to avoid negative margins.
     * @param interlineLeading leading for the 2nd and following lines
     * @param lastLineDescent  vertical space between the baseline of the last line and the bottom of the view.
     */
    public static void setExactMetrics(android.widget.TextView view,
                                       int firstLineLeading,
                                       int interlineLeading,
                                       int lastLineDescent) {
        // Remove implicit extra top/bottom padding to make our calculations easier
        view.setIncludeFontPadding(false);

        Paint.FontMetricsInt metrics = view.getPaint().getFontMetricsInt();

        // Required extra for interline leading.
        // Own height is measured from ascent to descent (ascent < descent)
        final int extra = interlineLeading - (metrics.descent - metrics.ascent);
        view.setLineSpacing(extra, 1);

        // Required top padding to set first line leading
        // With setIncludeFontPadding=false, the first baseline is placed -{ascent} pixels from the top
        final int paddingTop = firstLineLeading + metrics.ascent;

        // Required bottom padding
        // With setIncludeFontPadding=false, the view's bottom os placed {descent} pixels from the bottom
        final int paddingBottom = lastLineDescent - metrics.descent;

        view.setPadding(view.getPaddingLeft(), paddingTop, view.getPaddingRight(), paddingBottom);
    }

}
