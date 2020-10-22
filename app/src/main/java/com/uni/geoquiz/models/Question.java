package com.uni.geoquiz.models;

public class Question {
    private String content;
    private boolean answer;

    public String getContent() {
        return content;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public Question() {
    }

    public Question(String content, boolean answer) {
        this.content = content;
        this.answer = answer;
    }
}
