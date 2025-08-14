package com.example.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity6 extends AppCompatActivity {

    EditText editText;
    TextView textView, msg, point;
    Button button, passbtn;
    HashMap<String, String[]> dict = new HashMap<>();
    List<String> wordlst = new ArrayList<>();
    int curr_i = 0, pts = 0;
    String curr_word, temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        editText = findViewById(R.id.scramble_word);
        textView = findViewById(R.id.textView4);
        point = findViewById(R.id.pts);
        msg = findViewById(R.id.textView5);
        button = findViewById(R.id.button21);
        passbtn = findViewById(R.id.button22);

        dict.put("MIND", new String[]{"MEDITATE", "IGNITE", "NATURE", "DELIGHT"});

        for (String[] arr: dict.values()) {
            Collections.addAll(wordlst, arr);
        }

        msg.setText("UNSCRAMBLE THE ABOVE WORD AND ENTER HERE");
        showNext();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String entered = editText.getText().toString().trim();
                if (editText.getText().toString().trim().equalsIgnoreCase(curr_word)) {
                    msg.setText("Correct! The word was: " + curr_word);
                    pts += 10;
                    point.setText("POINTS: "+pts);
                    editText.setText("");
                    hideKeyboard(getCurrentFocus());

                }
//                else if (entered.equalsIgnoreCase(passlist.get(entered))) {
//                    msg.setText("Correct! The word was: " + passlist.get(entered));
//                }
                else {
                    msg.setText("Incorrect. Try again!");
                    return;
                }

                curr_i += 1;
                if (curr_i < wordlst.size()) {
                    showNext();
                }
                else {
//                    showNextpass();
                    msg.setText("All words completed! Try to apply them to real-life");
                }
            }
        });

        passbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msg.setText("UNSCRAMBLE THE ABOVE WORD AND ENTER HERE");
                wordlst.add(curr_word);
                curr_i += 1;
                showNext();
            }
        });
    }

    private void showNext() {
        curr_word = wordlst.get(curr_i);
        temp = scrambleWord(curr_word);
        textView.setText(temp);
    }

//    private void showNextpass() {
//        curr_word = wordpasslst.get(curr_i);
//        temp = scrambleWord(curr_word);
//        textView.setText(temp);
//    }

    private String scrambleWord(String word) {
        List<Character> chars = new ArrayList<>();
        for (char c : word.toCharArray()) {
            chars.add(c);
        }

        Collections.shuffle(chars);
        StringBuilder scrambled = new StringBuilder();
        for (char c : chars) {
            scrambled.append(c);
        }
        return scrambled.toString();
    }

    public void hideKeyboard(View view) {
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (im != null) {
            im.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}