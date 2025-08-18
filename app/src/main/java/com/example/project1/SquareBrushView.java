package com.example.project1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SquareBrushView extends View {

    private Bitmap canvasbmp;
    private Canvas drawCanvas;
    private Paint brushPaint;
    private float brushSize = 40f;

    public SquareBrushView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupPaint();
    }

    private void setupPaint() {
        brushPaint = new Paint();
        brushPaint.setColor(Color.BLUE);
        brushPaint.setStyle(Paint.Style.FILL);
        brushPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w>0 && h>0) {
            canvasbmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            drawCanvas = new Canvas(canvasbmp);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawCanvas!=null) {
            canvas.drawBitmap(canvasbmp, 0, 0, null);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (drawCanvas==null) {
            return false;
        }
        float touchX = event.getX();
        float touchY = event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE) {
            drawSquare(touchX, touchY);
        }

        invalidate();
        return true;
    }

    private void drawSquare(float x, float y) {
        if (drawCanvas==null) {
            return;
        }
        float halfSize = brushSize / 2;
        drawCanvas.drawRect(x - halfSize, y - halfSize, x + halfSize, y + halfSize, brushPaint);
    }

    public void setBrushColor(int color) {
        brushPaint.setColor(color);
    }

    public void setBrushSize(float size) {
        this.brushSize = size;
    }

    public void clearCanvas() {
        if (drawCanvas!=null) {
            drawCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            invalidate();
        }

    }
}
