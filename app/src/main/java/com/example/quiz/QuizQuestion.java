package com.example.quiz;

import androidx.annotation.DrawableRes;

public class QuizQuestion {
    public static final int NO_IMAGE = -1;

    @DrawableRes
    final int image;
    final String question;
    final String option1;
    final String option2;
    final String option3;
    final String option4;
    final int correctIndex;

    public QuizQuestion(String question, String option1, String option2, String option3, String option4, int correctAnswer) {
        this(NO_IMAGE, question, option1, option2, option3, option4, correctAnswer);
    }

    public QuizQuestion(int image, String question, String option1, String option2, String option3, String option4, int correctAnswer) {
        this.image = image;
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctIndex = correctAnswer - 1;
    }
}
