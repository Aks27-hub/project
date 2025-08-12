package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.Set;

public class MainActivity3 extends AppCompatActivity {

    Button start;
    private static final String[] WORDS = {
            "Gardening", "Go on walk", "Spend time with nature", " ", "Learn something new",
            "Be Happy", "Talk to friend", "Do favourite activity", "Listen to music", "Relax",
            "Read book", "Meditate", "Eat favourite food", " ", "Remember Good memories",
            "Spend time with family", " ", "Help others when need", "Reduce screen time",
            "Do deep breathing", " "
    };
    Button[] buttons = new Button[16];
    private boolean[][] marked = new boolean[4][4];
    private Set<String> check_lines = new HashSet<>();

    private static ArrayList<String> usedWords = new ArrayList<>();
    private static ArrayList<String> passWords = new ArrayList<>();
    private TextView txtWord;
    private Handler handler = new Handler(Looper.getMainLooper());
    private final int DELAY_MS = 4000;
    int start_flag = 0;

    private final Runnable wordUpdater = new Runnable() {
        @Override
        public void run() {
            String randomWord = getUniqueWord();
            if (randomWord.isEmpty()) {
                Toast.makeText(MainActivity3.this, "All Done!!", Toast.LENGTH_SHORT).show();
                return;
            }
            txtWord.setText(randomWord);
            handler.postDelayed(this, DELAY_MS); // Schedule the next update
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        txtWord = findViewById(R.id.rnd_word);
        start = findViewById(R.id.button19);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_flag = 1;
                handler.postDelayed(wordUpdater, DELAY_MS);
            }
        });

        List<String> shuffledWords = new ArrayList<>(Arrays.asList(WORDS));
        Collections.shuffle(shuffledWords);

        for (int i=3; i<=18; i++) {
            String buttonID = "button" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            Button button = findViewById(resID);
            int index = i - 3;
            int row = index / 4;
            int col = index % 4;

            buttons[index] = button;
            button.setText(shuffledWords.get(i));
            marked[row][col] = false;

            int finalRow = row;
            int finalCol = col;

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (start_flag==1) {
                        v.setBackgroundColor(ContextCompat.getColor(MainActivity3.this, android.R.color.holo_green_light));
                        v.setEnabled(false);
                        marked[finalRow][finalCol] = true;
                        checkForLine();
                    } else {
                        Toast.makeText(MainActivity3.this, "Please click \"START\" first", Toast.LENGTH_SHORT).show();
                    }
                }
            });

//            final int buttonIndex = i - 3; // To ensure each button gets its own reference
//            buttons[buttonIndex].setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Change the background color to green
//                    v.setBackgroundColor(ContextCompat.getColor(MainActivity3.this, android.R.color.holo_green_light));
//                }
//            });
        }

    }


    private String getUniqueWord() {
        if (usedWords.size() == WORDS.length) {
            usedWords.clear(); // Reset when all words are used
            return "";
        }

        Random random = new Random();
        String word;
        do {
            word = WORDS[random.nextInt(WORDS.length)];
        } while (usedWords.contains(word));

        usedWords.add(word);
        return word;
    }

    private void checkForLine() {
        for (int i = 0; i < 4; i++) {
            String row = "Row"+i;
            if (!check_lines.contains(row) && marked[i][0] && marked[i][1] && marked[i][2] && marked[i][3]) {
                check_lines.add(row);
                showWin("Row " + (i + 1));
                return;
            }
        }

        for (int j = 0; j < 4; j++) {
            String column = "Column"+j;
            if (!check_lines.contains(column) && marked[0][j] && marked[1][j] && marked[2][j] && marked[3][j]) {
                check_lines.add(column);
                showWin("Column " + (j + 1));
                return;
            }
        }

        if (!check_lines.contains("MainDiagonal") && marked[0][0] && marked[1][1] && marked[2][2] && marked[3][3]) {
            check_lines.add("MainDiagonal");
            showWin("Main diagonal");
            return;
        }

        if (!check_lines.contains("AntiDiagonal") && marked[0][3] && marked[1][2] && marked[2][1] && marked[3][0]) {
            check_lines.add("AntiDiagonal");
            showWin("Other-diagonal");
        }
    }

    private void showWin(String type) {
        Toast.makeText(this, "Line formed: " + type + "!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(wordUpdater); // Clean up the handler to prevent memory leaks
    }
}