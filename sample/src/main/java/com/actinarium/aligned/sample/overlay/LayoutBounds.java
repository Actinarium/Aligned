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
