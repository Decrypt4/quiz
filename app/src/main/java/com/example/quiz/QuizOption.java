package com.example.quiz;

import android.content.res.ColorStateList;
import android.widget.Button;

import androidx.core.content.ContextCompat;

public class QuizOption {
    boolean isCorrect;
    Button widget;
    String text;

    QuizOption(Button widget, String text, boolean isCorrect) {
        this.widget = widget;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    void markAsCorrect() {
        this.widget.setTextColor(ContextCompat.getColor(this.widget.getContext(), R.color.option_green));
        this.widget.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.widget.getContext(), R.color.option_bg_green)));
        this.widget.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.correct, 0);
        setButtonStrokeColor(this.widget, R.color.option_green);
    }

    void markAsWrong() {
        this.widget.setTextColor(ContextCompat.getColor(this.widget.getContext(), R.color.option_red));
        this.widget.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.widget.getContext(), R.color.option_bg_red)));
        this.widget.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.wrong, 0);
        setButtonStrokeColor(this.widget, R.color.option_red);
    }

    void markAsUnchecked() {
        this.widget.setTextColor(ContextCompat.getColor(this.widget.getContext(), R.color.text_gray));
        this.widget.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this.widget.getContext(), R.color.white)));
        this.widget.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.unchecked, 0);
        setButtonStrokeColor(this.widget, R.color.gray);
    }

    void disable() {
        this.widget.setEnabled(false);
    }
    void enable() {
        this.widget.setEnabled(true);
    }
    boolean isEnabled() {
        return this.widget.isEnabled();
    }

    private void setButtonStrokeColor(Button button, int colorResId) {
        //
    }

    void setText(String text) {
        this.markAsUnchecked();
        this.widget.setText(text);
    }
}
