package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    private String getResultPronunciation(int amount) {
        switch (amount) {
            case 1:
                return "правильный ответ";
            case 2:
            case 3:
            case 4:
                return "правильных ответа";
            default:
                return "правильных ответов";
        }
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        TextView time = findViewById(R.id.time);
        Button resultCorrect = findViewById(R.id.result_correct);
        Button resultWrong = findViewById(R.id.result_wrong);
        Button restartButton = findViewById(R.id.restart);

        Intent intent = getIntent();
        if (intent != null) {
            long timeTaken = intent.getLongExtra("timeTaken", 0);
            long minutes = (timeTaken / 1000) / 60;
            long seconds = (timeTaken / 1000) % 60;

            time.setText(String.format("%d:%02d", minutes, seconds));

            int correctAnswers = intent.getIntExtra("correctAnswers", 0);
            String correctPronunciation = this.getResultPronunciation(correctAnswers);
            resultCorrect.setText(correctAnswers + " " + correctPronunciation);

            int wrongAnswers = intent.getIntExtra("wrongAnswers", 0);
            String wrongPronunciation = this.getResultPronunciation(wrongAnswers);
            resultWrong.setText(wrongAnswers + " не" + wrongPronunciation);
        }

        restartButton.setOnClickListener(v -> {
            Intent newIntent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(newIntent);
            finish();
        });
    }
}
