package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity4 extends AppCompatActivity {

    ImageButton start, stop, back, fwd;
    Button load;
    TextView music_name, seek_bg;
    SeekBar seekbar;
    MediaPlayer music;
    Map<String, Integer> musicMap;
    Handler handler = new Handler();
    Runnable runnable;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        back = findViewById(R.id.imageButton);
        stop = findViewById(R.id.imageButton2);
        fwd = findViewById(R.id.imageButton3);
        start = findViewById(R.id.imageButton4);
        load = findViewById(R.id.button20);
        music_name = findViewById(R.id.textView3);
        seek_bg = findViewById(R.id.seek);
        seekbar = findViewById(R.id.seekBar);
        AutoCompleteTextView dropdown = findViewById(R.id.autoCompleteTextView);

        musicMap = new HashMap<>();
        musicMap.put("music_track1", R.raw.music_track1);
        musicMap.put("music_track2", R.raw.music_track2);
        musicMap.put("music_track3", R.raw.music_track3);

        start.setVisibility(View.INVISIBLE);

        runnable = new Runnable() {
            @Override
            public void run() {
                if (music != null) {
                    seekbar.setProgress(music.getCurrentPosition());
                }
                handler.postDelayed(this, 1000); // Update every second
            }
        };

        handler.postDelayed(runnable, 0);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer resID = musicMap.get(selected);
                if (resID != null) {
                    playMusic(resID);
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (music != null) {
                    music.start();
                    stop.setVisibility(View.VISIBLE);
                    start.setVisibility(View.INVISIBLE);
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (music != null) {
                    music.pause();
                    stop.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.VISIBLE);
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int curr_pos = music.getCurrentPosition();
                int new_pos = Math.max(curr_pos - 5000, 0);
                music.seekTo(new_pos);
                Toast.makeText(getApplicationContext(), "5 seconds backward", Toast.LENGTH_SHORT).show();

            }
        });

        fwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int len = music.getDuration();
                int curr_pos = music.getCurrentPosition();
                int new_pos = Math.min(curr_pos + 5000, len);
                music.seekTo(new_pos);
                Toast.makeText(getApplicationContext(), "5 seconds forward", Toast.LENGTH_SHORT).show();

            }
        });

        String[] items = getResources().getStringArray(R.array.music_name);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        dropdown.setAdapter(adapter);

        dropdown.setText("Select any music item", false);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected = parent.getItemAtPosition(position).toString();
                music_name.setText(selected);
                Toast.makeText(getApplicationContext(), "Selected music: " + selected, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void playMusic(Integer resID) {
        if (music != null) {
            music.release();
            music = null;
        }
        if (resID != null) {
            music = MediaPlayer.create(this, resID);
            seekbar.setMax(music.getDuration());
            music.start();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (music != null) {
            music.release();
            music = null;
        }
        handler.removeCallbacks(runnable);
    }
}
