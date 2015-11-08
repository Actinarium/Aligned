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
import com.actinarium.rhythm.spec.RhythmSpecLayer;

/**
 * <p></p>
 *
 * @author Paul Danyliuk
 */
public class LayoutBounds implements RhythmSpecLayer {

    private Paint mPaint;
    private RectF mTemp;
    private int mCrosshairSize;

    public LayoutBounds(int crosshairSize) {
        mCrosshairSize = crosshairSize;

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
        // top left
        canvas.save();
        canvas.clipRect(drawableBounds.left, drawableBounds.top, drawableBounds.left + mCrosshairSize, drawableBounds.top + mCrosshairSize);
        canvas.drawRect(mTemp, mPaint);
        canvas.restore();
        // top right
        canvas.save();
        canvas.clipRect(drawableBounds.right - mCrosshairSize, drawableBounds.top, drawableBounds.right, drawableBounds.top + mCrosshairSize);
        canvas.drawRect(mTemp, mPaint);
        canvas.restore();
        // bottom left
        canvas.save();
        canvas.clipRect(drawableBounds.left, drawableBounds.bottom - mCrosshairSize, drawableBounds.left + mCrosshairSize, drawableBounds.bottom);
        canvas.drawRect(mTemp, mPaint);
        canvas.restore();
        // bottom right
        canvas.save();
        canvas.clipRect(drawableBounds.right - mCrosshairSize, drawableBounds.bottom - mCrosshairSize, drawableBounds.right, drawableBounds.bottom);
        canvas.drawRect(mTemp, mPaint);
        canvas.restore();
    }

}
