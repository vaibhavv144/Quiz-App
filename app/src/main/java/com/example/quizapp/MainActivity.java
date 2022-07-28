package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.example.quizapp.databinding.ActivityMainBinding;
import com.example.quizapp.model.questions;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int currentindex=0;
    private questions[] questionBank=new questions[]{
            new questions(R.string.question_agri,true),
            new questions(R.string.question_border,true),
            new questions(R.string.question_gk,false),
            new questions(R.string.question_pm,false),
            new questions(R.string.question_state,true),
            new questions(R.string.question_crop,true),
            new questions(R.string.question_prize,false),
            new questions(R.string.question_sports,false),
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);
        binding.ques.setText(questionBank[currentindex].getAnswerResId());
        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        binding.button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentindex=(currentindex+1)%questionBank.length;
                updateQuestion();
            }
        });
        binding.prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentindex>0){
                    currentindex=(currentindex-1)%questionBank.length;
                    updateQuestion();
                }
            }
        });
    }
            private void checkAnswer(boolean checkuser){
        boolean answeriscorrect=questionBank[currentindex].isAnswerTrue();
                int mssgid;
                if(answeriscorrect==checkuser){
                    mssgid=R.string.correct_answer;
                }else{
                    mssgid=R.string.wrong_answer;
                }
                Snackbar.make(binding.imageView,mssgid,Snackbar.LENGTH_SHORT)
                        .show();
            }

    private void updateQuestion() {
        binding.ques.setText(questionBank[currentindex].getAnswerResId());
    }
}