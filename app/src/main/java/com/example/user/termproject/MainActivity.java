package com.example.user.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Startbutton = (Button)findViewById(R.id.startbutton);

        Startbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HangManActivity.class); // 다음 넘어갈 클래스 지정
                Hangman h = new Hangman();
                HangManHint hint = new HangManHint();
                intent.putExtra("newman", h);
                intent.putExtra("newhint",hint);
                startActivity(intent); // 다음 화면으로 넘어간다
            }
        });
    }
} // end onCreate()

class Hangman implements Serializable{
    String[] secret_list = {"apple", "banana", "car"};
    String secret_word = secret_list[(int)(Math.random()*3)];
    int banana = 5;
    int listsize = secret_word.length();
}

class HangManHint implements Serializable {
    boolean hint_used = false;
    int hint_num = (int) (Math.random() * 3);
}


