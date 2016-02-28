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
import com.actinarium.rhythm.RhythmSpecLayer;
import com.actinarium.rhythm.config.LayerConfig;
import com.actinarium.rhythm.config.RhythmInflationException;
import com.actinarium.rhythm.config.RhythmSpecLayerFactory;

/**
 * <p></p>
 *
 * @author Paul Danyliuk
 */
public class CheckersLayer implements RhythmSpecLayer {

    private int mSquareSize;
    private Paint mPaint;

    public CheckersLayer(int squareSize) {
        mSquareSize = squareSize;

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
    }

    @Override
    public void draw(Canvas canvas, Rect drawableBounds) {
        int y = drawableBounds.top;
        while (y < drawableBounds.bottom) {
            canvas.drawRect(drawableBounds.left, y, drawableBounds.left + mSquareSize, y + mSquareSize, mPaint);
            canvas.drawRect(drawableBounds.right - mSquareSize, y, drawableBounds.right, y + mSquareSize, mPaint);
            y += mSquareSize << 1;
        }
    }

    public static class Factory implements RhythmSpecLayerFactory<CheckersLayer> {

        public static final String LAYER_TYPE = "checkers";

        @Override
        public CheckersLayer getForConfig(LayerConfig config) {
            int squareSize = config.getDimensionPixelSize("size", 0);
            if (squareSize <= 0) {
                throw new RhythmInflationException("Size cannot be <= 0 for checkers layer");
            }
            return new CheckersLayer(squareSize);
        }
    }
}
