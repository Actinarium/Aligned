package com.actinarium.aligned.sample.overlay;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import com.actinarium.rhythm.spec.RhythmSpecLayer;

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
}
