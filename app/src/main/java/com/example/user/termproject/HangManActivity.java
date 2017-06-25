package com.example.user.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;

/**
 * Created by USER on 2017-06-05.
 */

public class HangManActivity extends AppCompatActivity {
    private static final int LAUNCHED_ACTIVITY1 = 1;
/*
    View.OnClickListener listen = new View.onClickListener(){
        public void onClick(View v){

        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_main);

        Intent intent = getIntent();
        final HangmanGame h = new HangmanGame();
        h.newman = (Hangman) intent.getSerializableExtra("newman");
        h.newhint = (HangManHint) intent.getSerializableExtra("newhint");

        Button letterbutton = (Button) findViewById(R.id.letter);
        Button wordbutton = (Button) findViewById(R.id.word);
        Button hintbutton = (Button) findViewById(R.id.hintbutton);
        final TextView secretword = (TextView) findViewById(R.id.secretWord);
        final TextView bananacount = (TextView) findViewById(R.id.bananaview);
        final EditText letterinput = (EditText) findViewById(R.id.letterinput);
        final EditText wordinput = (EditText) findViewById(R.id.wordinput);
        final TextView wronglist = (TextView) findViewById(R.id.wronglist);


        //bananacount.setText("banana count : " + h.newman.banana);
        h.setStarlist();

        letterbutton.setOnClickListener(new View.OnClickListener() {//button->view
            @Override
            public void onClick(View view) {
                h.getletter = letterinput.getText().toString();
                if(h.guessWord()){
                    bananacount.setText("  " + h.newman.banana);
                    secretword.setText(h.star_list, 0, h.newman.listsize);
                    wronglist.setText(h.wrongletterlist, 0, h.wrongcount);
                }else {
                    Intent intent = new Intent(getApplicationContext(), GameLose.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                }
            }
        });
        wordbutton.setOnClickListener(new View.OnClickListener() {//button->view
            @Override
            public void onClick(View view) {
                h.getletter = wordinput.getText().toString();
                if(h.guessAllWord()){
                    Intent intent = new Intent(getApplicationContext(), GameWin.class); // 다음 넘어갈 클래스 지정
                    startActivity(intent);
                }else {
                    if(h.newman.banana!=0) {
                        bananacount.setText("  " + h.newman.banana);
                        secretword.setText(h.star_list, 0, h.newman.listsize);
                        wronglist.setText(h.wrongletterlist, 0, h.wrongcount);
                    }else{
                        Intent intent = new Intent(getApplicationContext(), GameLose.class); // 다음 넘어갈 클래스 지정
                        startActivity(intent);
                    }
                }
            }
        });
        hintbutton.setOnClickListener(new View.OnClickListener() {//button->view
            @Override
            public void onClick(View view) {
                if(h.newhint.hint_used){
                    Toast.makeText(HangManActivity.this, "hint is already used", Toast.LENGTH_SHORT).show();
                }else{
                    h.userandomHint();
                    bananacount.setText("  " + h.newman.banana);
                    secretword.setText(h.star_list, 0, h.newman.listsize);
                    wronglist.setText(h.wrongletterlist, 0, h.wrongcount);
                    if(h.newhint.hint_num == 0){
                        Toast.makeText(HangManActivity.this, "Give you one more banana!", Toast.LENGTH_SHORT).show();
                    }else if(h.newhint.hint_num == 1){
                        Toast.makeText(HangManActivity.this, "Give you one letter!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
class HangmanGame implements Serializable{
    Hangman newman;
    HangManHint newhint;
    char[] star_list;
    String getletter;
    char letter;
    char[] wrongletterlist;
    int wrongcount = 0;

    public void setStarlist(){
        star_list = new char[newman.listsize];
        wrongletterlist = new char[24];
        for(int i=0; i<newman.listsize; i++){
            star_list[i] = '?';
        }
    }

    public boolean guessWord() {
        letter = getletter.charAt(0);
        boolean isthere = false;
        for (int i = 0; i < newman.listsize; i++) {
            if (newman.secret_word.charAt(i) == letter) {
                star_list[i] = letter;
                isthere = true;
            }
        }
        if (isthere == false) {
            wrongletterlist[wrongcount] = letter;
            wrongcount ++;
            newman.banana = newman.banana - 1;
        }
        if(newman.banana == 0){
            return false;
        }
        return true;
    }

    public boolean guessAllWord() {
        //printlist = String.valueOf(star_list);
        if (newman.secret_word.equals(getletter)) {
            return true;
        } else {
            newman.banana = newman.banana - 1;
            return false;
        }
    }

    public boolean didHintused(){
        if(newhint.hint_used){
            return true;
        }else{
            return false;
        }
    }

    public void userandomHint(){
        if(newhint.hint_num == 0){
            hintaddBanana();
        }else if(newhint.hint_num == 1){
            hintshowletter();
        }
    }

    public void hintaddBanana(){
        newman.banana = newman.banana + 1;
        newhint.hint_used =  true;
    }

    public void hintshowletter(){
        for(int i = 0; i < newman.listsize; i++){
            if(star_list[i] == '?'){
                star_list[i] = getletter.charAt(i);
                newhint.hint_used = true;
                return;
            }else{
                continue;
            }
        }
    }
}
