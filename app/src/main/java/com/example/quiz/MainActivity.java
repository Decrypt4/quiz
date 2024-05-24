package com.example.quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView questionText, timerText;
    private MediaPlayer mediaPlayer;

    private ProgressBar progressBar;
    private ImageView questionImage;
    private QuizOption option1, option2, option3, option4;
    private QuizOption[] options;
    @Nullable
    private QuizOption selectedOption;
    private List<QuizQuestion> questionList;
    private int currentQuestionIndex = 0;
    private QuizQuestion currentQuestion;
    private CountDownTimer countDownTimer;
    private long startTime;
    private int correctCounter, wrongCounter;
    private static final long TIMER_DURATION = 20000; // 20s

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mediaPlayer = MediaPlayer.create(this, R.raw.question);

        this.questionText = findViewById(R.id.question_text);
        this.timerText = findViewById(R.id.timer);
        this.progressBar = findViewById(R.id.progressBar);
        this.questionImage = findViewById(R.id.imageView);

        this.option1 = new QuizOption(findViewById(R.id.option1), "Option", false);
        this.option2 = new QuizOption(findViewById(R.id.option2), "Option", false);
        this.option3 = new QuizOption(findViewById(R.id.option3), "Option", false);
        this.option4 = new QuizOption(findViewById(R.id.option4), "Option", false);

        this.options = new QuizOption[]{option1, option2, option3, option4};
        for (QuizOption option : this.options) {
            option.widget.setOnClickListener(v -> {
                if (!option.isEnabled()) return;
                selectedOption = option;
                showAnswer();
            });
        }

        this.questionList = QuestionProvider.getRandomQuestions(8);
        this.startTime = System.currentTimeMillis();
        this.showNextQuestion();
    }

    private void showNextQuestion() {
        this.mediaPlayer.stop();
        this.mediaPlayer.prepareAsync();
        this.mediaPlayer.setOnPreparedListener(MediaPlayer::start);
        this.selectedOption = null;

        if (currentQuestionIndex < questionList.size()) {
            currentQuestion = questionList.get(currentQuestionIndex);
            questionText.setText(currentQuestion.question);

            if (currentQuestion.image != QuizQuestion.NO_IMAGE) {
                questionImage.setVisibility(View.VISIBLE);
                questionImage.setImageResource(currentQuestion.image);
            } else {
                questionImage.setVisibility(View.GONE);
            }

            option1.setText(currentQuestion.option1);
            option2.setText(currentQuestion.option2);
            option3.setText(currentQuestion.option3);
            option4.setText(currentQuestion.option4);

            for (QuizOption option : this.options) {
                option.enable();
            }

            startTimer();
        } else {
            long endTime = System.currentTimeMillis();
            long timeTaken = endTime - startTime;

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("timeTaken", timeTaken);
            intent.putExtra("correctAnswers", this.correctCounter);
            intent.putExtra("wrongAnswers", this.wrongCounter);
            startActivity(intent);
            finish();
        }
    }

    private void startTimer() {
        if (this.countDownTimer != null) {
            this.countDownTimer.cancel();
        }

        this.countDownTimer = new CountDownTimer(TIMER_DURATION, 1000) {
            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int) millisUntilFinished / 1000;

                String wordCase;
                if (progress == 1) {
                    wordCase = "секунда";
                } else if (progress >= 2 && progress <= 4) {
                    wordCase = "секунды";
                } else {
                    wordCase = "секунд";
                }
                timerText.setText(progress + " " + wordCase);

                int percentage = progress * 5;
                progressBar.setProgress(percentage);
                if (percentage > 65) {
                    progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#6AC259"), PorterDuff.Mode.SRC_IN);
                } else if (percentage > 25) {
                    progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#F3CB65"), PorterDuff.Mode.SRC_IN);
                } else {
                    progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#E45151"), PorterDuff.Mode.SRC_IN);
                }
            }

            @Override
            public void onFinish() {
                showAnswer();
            }
        }.start();
    }

    private void showAnswer() {
        this.mediaPlayer.stop();

        for (int i = 0; i < this.options.length; i++) {
            QuizOption option = this.options[i];
            option.disable();

            if (this.currentQuestion.correctIndex == i) {
                option.markAsCorrect();
            } else {
                option.markAsUnchecked();
            }

            if(selectedOption == option) {
                if(this.currentQuestion.correctIndex != i) {
                    this.wrongCounter++;
                    option.markAsWrong();
                    MediaPlayer.create(this, R.raw.wrong).start();
                } else {
                    this.correctCounter++;
                    MediaPlayer.create(this, R.raw.correct).start();
                }
            }
        }

        this.countDownTimer.cancel();
        new Handler().postDelayed(this::showNextQuestion, 3000);
        this.currentQuestionIndex++;
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
        super.onDestroy();
    }
}
