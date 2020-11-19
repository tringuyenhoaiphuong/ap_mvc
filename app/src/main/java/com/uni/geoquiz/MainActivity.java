package com.uni.geoquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.uni.geoquiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "GEOQUIZ";
    private static final String SAVED_CUR_QUESTION_IDX = "SAVED_CUR_QUESTION_IDX";
    List<Question> questionList;
    int curQuesitonIdx = 0;
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*setContentView(R.layout.activity_main);*/
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_main, null);

        tvContent = view.findViewById(R.id.tvQuestionContent);

        generateQuestions();

        if(savedInstanceState != null) {
            curQuesitonIdx = savedInstanceState.getInt(SAVED_CUR_QUESTION_IDX, 0);
        }

        ShowCurQuestion();

        setContentView(view);

        Log.d(TAG, "onCreate");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_CUR_QUESTION_IDX, curQuesitonIdx);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    private void ShowCurQuestion() {
        Question curQuestion = questionList.get(curQuesitonIdx);
        tvContent.setText(curQuestion.getContent());
    }

    private void generateQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question(getString(R.string.questionA), false));
        questionList.add(new Question(getString(R.string.questionB), true));
        questionList.add(new Question(getString(R.string.questionC), false));
        questionList.add(new Question(getString(R.string.questionD), true));
        questionList.add(new Question(getString(R.string.questionE), false));
    }


    public void btTrue_OnClick(View view) {
        CheckAndShowAnswer(true);
    }

    public void btFalse_OnClick(View view) {
        CheckAndShowAnswer(false);
    }

    private void CheckAndShowAnswer(boolean userAnswer) {
        Question curQuestion = questionList.get(curQuesitonIdx);
        if(curQuestion.getAnswer() == userAnswer) {
            Toast.makeText(this, "Bạn đã trả lời ĐÚNG", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bạn đã trả lời SAI", Toast.LENGTH_SHORT).show();
        }

        ShowNextQuestion();
    }

    private void ShowNextQuestion() {
        curQuesitonIdx++;
        if(curQuesitonIdx >= questionList.size()) curQuesitonIdx = 0;
        ShowCurQuestion();
    }
}
