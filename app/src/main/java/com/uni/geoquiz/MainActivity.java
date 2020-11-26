package com.uni.geoquiz;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.uni.geoquiz.models.Question;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Question> questionList;
    TextView mTvQuestion;
    int currentQuestionIdx = 0;
    Button mBtTrue, mBtFalse, mBtCheat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createQuestions();
        /*setContentView(R.layout.activity_main);*/
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.activity_main, null);

        mTvQuestion = view.findViewById(R.id.tvQuestionContent);
        mBtTrue = view.findViewById(R.id.btTrue);
        mBtFalse = view.findViewById(R.id.btFalse);
        mBtCheat = view.findViewById(R.id.btCheat);

        mBtTrue.setOnClickListener(v -> ShowAnswer(true));
        mBtFalse.setOnClickListener(v -> ShowAnswer(false));
        mBtCheat.setOnClickListener(v -> {
            Intent openCheatActivityIntent = new Intent(this, CheatActivity.class);
            Question curQuestion = questionList.get(currentQuestionIdx);
            boolean answer = curQuestion.getAnswer();
            openCheatActivityIntent.putExtra(CheatActivity.EXTRA_ANSWER, answer);
            startActivityForResult(openCheatActivityIntent, CheatActivity.SHOW_ANSWER_REQUEST);
        });

        UpdateViews();

        setContentView(view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CheatActivity.SHOW_ANSWER_REQUEST) {
            if(resultCode == CheatActivity.SHOWN_ANSWER_CODE) {
                Toast t = Toast.makeText(this, getString(R.string.you_saw_the_answer), Toast.LENGTH_SHORT);
                t.show();
                StartTimerToCloseToast(t);
            }
        }
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

    private void ShowAnswer(boolean userAnswer) {
        Question curQuestion = questionList.get(currentQuestionIdx);
        boolean answer = curQuestion.getAnswer();
        if(answer == userAnswer) {
            Toast t = Toast.makeText(this, "Bạn đã trả lời ĐÚNG", Toast.LENGTH_SHORT);
            t.show();
            StartTimerToCloseToast(t);
        } else {
            Toast t = Toast.makeText(this, "Bạn đã trả lời SAI", Toast.LENGTH_SHORT);
            t.show();
            StartTimerToCloseToast(t);
        }

//        ShowNextQuestion();
    }

    private void StartTimerToCloseToast(final Toast t) {
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            t.cancel();
            ShowNextQuestion();
        }, 2000);
    }

    private void ShowNextQuestion() {
        currentQuestionIdx++;
        UpdateViews();
    }
}
