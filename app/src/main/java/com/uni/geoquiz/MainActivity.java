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
    TextView mTvQuestion;
    int currentQuestionIdx = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createQuestions();
        /*setContentView(R.layout.activity_main);*/
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_main, null);

        mTvQuestion = view.findViewById(R.id.tvQuestionContent);

        UpdateViews();

        setContentView(view);
    }

    private void UpdateViews() {
        Question curQuestion = questionList.get(currentQuestionIdx);
        mTvQuestion.setText(curQuestion.getContent());
    }

    private void createQuestions() {
        questionList = new ArrayList<>();
        questionList.add(new Question(getString(R.string.questionA), true));
        questionList.add(new Question(getString(R.string.questionB), true));
        questionList.add(new Question(getString(R.string.questionC), false));
        questionList.add(new Question(getString(R.string.questionD), false));
        questionList.add(new Question(getString(R.string.questionE), true));
    }

    public void btTrue_OnClick(View view) {
        ShowAnswer(true);
    }

    private void ShowAnswer(boolean userAnswer) {
        Question curQuestion = questionList.get(currentQuestionIdx);
        boolean answer = curQuestion.getAnswer();
        if(answer == userAnswer) {
            Toast.makeText(this, "Bạn đã trả lời ĐÚNG", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Bạn đã trả lời SAI", Toast.LENGTH_SHORT).show();
        }

        ShowNextQuestion();
    }

    private void ShowNextQuestion() {
        currentQuestionIdx++;
        UpdateViews();
    }

    public void btFalse_OnClick(View view) {
        ShowAnswer(false);
    }
}
