package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class MainActivity7 extends AppCompatActivity {

    private ImageView asana1;
    private Matrix matrix = new Matrix();
    private ScaleGestureDetector scaleGestureDetect;
    private float scaleFact = 1.f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        asana1 = findViewById(R.id.anu_vil);
        asana1.setImageMatrix(matrix);
        scaleGestureDetect = new ScaleGestureDetector(MainActivity7.this, new ScaleListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Pass touch events to the ScaleGestureDetector
        scaleGestureDetect.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // Scale the image based on pinch gestures
            scaleFact *= detector.getScaleFactor();
            scaleFact = Math.max(0.1f, Math.min(scaleFact, 10.0f)); // Limit the zoom scale
            matrix.setScale(scaleFact, scaleFact);
            asana1.setImageMatrix(matrix);
            return true;
        }
    }
}