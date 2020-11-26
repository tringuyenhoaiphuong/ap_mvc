package com.uni.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_ANSWER = "EXTRA_ANSWER";
    public static final int SHOW_ANSWER_REQUEST = 1001;
    public static final int SHOWN_ANSWER_CODE = 1000;
    TextView mTvAnswer;
    Button mBtShowAnswer;
    private boolean mAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mTvAnswer = findViewById(R.id.tvAnswer);
        mBtShowAnswer = findViewById(R.id.btShowAnswer);

        mBtShowAnswer.setOnClickListener(v -> {
            mTvAnswer.setText(String.format("The current answer is %S", mAnswer));
            setResult(SHOWN_ANSWER_CODE);
        });

        Intent startedIntent = getIntent();
        if(startedIntent != null) {
            mAnswer = startedIntent.getBooleanExtra(EXTRA_ANSWER, false);
        }
    }
}
