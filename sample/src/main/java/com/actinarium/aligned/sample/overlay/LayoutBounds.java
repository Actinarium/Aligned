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

package com.actinarium.aligned.sample.overlay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import com.actinarium.rhythm.RhythmSpecLayer;
import com.actinarium.rhythm.config.LayerConfig;
import com.actinarium.rhythm.config.RhythmInflationException;
import com.actinarium.rhythm.config.RhythmSpecLayerFactory;

/**
 * A simple custom layer that draws layout bounds, similarly to "Draw layout bounds" in Developer options
 *
 * @author Paul Danyliuk
 */
public class LayoutBounds implements RhythmSpecLayer {

    private Paint mPaint;
    private RectF mTemp;
    private int mCrosshairSize;

    public LayoutBounds(int crosshairSize) {
        this();
        mCrosshairSize = crosshairSize;
    }

    private LayoutBounds() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mTemp = new RectF();
    }

    @Override
    public void draw(Canvas canvas, Rect drawableBounds) {
        mTemp.set(drawableBounds);
        mTemp.inset(0.5f, 0.5f);

        // Draw big box
        mPaint.setStrokeWidth(0);
        mPaint.setColor(Color.MAGENTA);
        canvas.drawRect(mTemp, mPaint);

        // Draw crosshair
        mPaint.setStrokeWidth(3);
        mPaint.setColor(Color.BLUE);

        canvas.save();
        canvas.clipRect(drawableBounds.left, drawableBounds.top, drawableBounds.left + mCrosshairSize, drawableBounds.top + mCrosshairSize);
        canvas.clipRect(drawableBounds.right - mCrosshairSize, drawableBounds.top, drawableBounds.right, drawableBounds.top + mCrosshairSize, Region.Op.UNION);
        canvas.clipRect(drawableBounds.left, drawableBounds.bottom - mCrosshairSize, drawableBounds.left + mCrosshairSize, drawableBounds.bottom, Region.Op.UNION);
        canvas.clipRect(drawableBounds.right - mCrosshairSize, drawableBounds.bottom - mCrosshairSize, drawableBounds.right, drawableBounds.bottom, Region.Op.UNION);
        canvas.drawRect(mTemp, mPaint);
        canvas.restore();
    }

    public static class Factory implements RhythmSpecLayerFactory<LayoutBounds> {

        public static final String LAYER_TYPE = "layout-bounds";

        @Override
        public LayoutBounds getForConfig(LayerConfig config) {
            LayoutBounds layoutBounds = new LayoutBounds();

            layoutBounds.mCrosshairSize = config.getDimensionPixelOffset("crosshair-size", 0);
            if (layoutBounds.mCrosshairSize <= 0) {
                throw new RhythmInflationException("Error when inflating layout-bounds: crosshair-size is <= 0 or invalid");
            }

            return layoutBounds;
        }
    }

}
