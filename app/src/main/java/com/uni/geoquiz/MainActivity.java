package com.uni.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.uni.geoquiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
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

        ShowCurQuestion();

        setContentView(view);
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
