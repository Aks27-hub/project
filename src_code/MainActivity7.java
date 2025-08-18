package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.chrisbanes.photoview.PhotoView;

public class MainActivity7 extends AppCompatActivity {

    private PhotoView asana1, asana2, asana3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        asana1 = findViewById(R.id.anu_vil);
        asana1.setImageResource(R.drawable.anulom_vilom);
        asana2 = findViewById(R.id.bhst);
        asana2.setImageResource(R.drawable.bhastrika);
        asana3 = findViewById(R.id.kpl);
        asana3.setImageResource(R.drawable._kapalbhati);

    }
}