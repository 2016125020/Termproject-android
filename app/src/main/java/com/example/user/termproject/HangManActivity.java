package com.example.user.termproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by USER on 2017-06-05.
 */

public class HangManActivity extends AppCompatActivity{
    private String[] secret_list = {"apple", "banana", "car"};
    private String secret_word;
    protected int[] star_list;
    protected int banana = 5;
    protected int listsize;

    public void startgame(){            //생성자 있던 곳
        secret_word = secret_list[(int) (Math.random()*3)];
        //System.out.printf("\nsecret word : %s", secret_word);
        listsize = secret_word.length();
        star_list = new int[secret_word.length()];
        for(int i=0; i < secret_word.length(); i++){
            star_list[i] = 0;
        }

    }

    public void guessWord(char a){
        boolean isthere = false;
        for(int i=0; i < secret_word.length(); i++){
            if(secret_word.charAt(i) == a){
                star_list[i] = 1;
                isthere = true;
            }
        }
        if(isthere == false){
            banana = banana - 1;
        }
    }

    public boolean guessWord(String a){
        if(secret_word.equals(a)){
            return true;
        }else{
            banana = banana - 1;
            return false;
        }
    }
    public void print_starlist(){
        for(int i=0; i < secret_word.length(); i++){
            if(star_list[i]==0){
                System.out.print("*");
            }else{
                System.out.print(secret_word.charAt(i));
            }
        }
        System.out.print("\n");
    }

    public int getbanana(){
        return banana;
    }

    public boolean isfinish(){
        for(int i=0; i < secret_word.length(); i++){
            if(star_list[i] == 0){
                return false;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hangman_main);

        Button letterbutton = (Button)findViewById(R.id.letter);
        Button wordbutton = (Button)findViewById(R.id.word);
        EditText letterinput = (EditText)findViewById(R.id.letterinput);
        EditText wordinput = (EditText)findViewById(R.id.wordinput);
        TextView secretword = (TextView)findViewById(R.id.secretWord);

        letterbutton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onFocusChange() {

            }
        }
    }

}
