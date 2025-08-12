package com.example.project1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    private SquareBrushView paint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        paint = findViewById(R.id.paintView);
        Button clearbtn = findViewById(R.id.clear);
        LinearLayout colorContainer = findViewById(R.id.colorContainer);

        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.YELLOW,
                        Color.CYAN, Color.MAGENTA};

        for (int color : colors) {
            Button colorBtn = new Button(MainActivity5.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100); // size in px
            params.setMargins(8, 0, 8, 0);
            colorBtn.setLayoutParams(params);
            colorBtn.setBackgroundColor(color);

            // Handle click
            colorBtn.setOnClickListener(v -> paint.setBrushColor(color));

            // Add to container
            colorContainer.addView(colorBtn);
        }

        clearbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paint.clearCanvas();
            }
        });
    }
}
