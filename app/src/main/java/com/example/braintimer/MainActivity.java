package com.example.braintimer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrect;
    TextView sumTextView;
    TextView result;
    Button button0 ; Button button1 ; Button button2 ; Button button3;
    int score = 0;
    int noOfQuestion = 0;
    TextView scoreTextView;
    TextView timerTextView;
    Button playAgain;
    ConstraintLayout gameLayout;

    public void playAgain(View view)
    {
        score =0;
        noOfQuestion = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestion));
        newQuestion();;
        playAgain.setVisibility(View.INVISIBLE);
        result.setText(" ");


        new CountDownTimer(30100 , 1000) {

            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l/1000) + "s");
            }

            @Override
            public void onFinish() {
                result.setText("DONE!");
                playAgain.setVisibility(View.VISIBLE);

                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext() , R.raw.horn);
                mediaPlayer.start();

            }
        }.start();

    }

    public void chooseAnswer(View view)
    {
        if(Integer.toString(locationOfCorrect).equals(view.getTag().toString()))
        {
            result.setText("CORRECT!");
            score++;
        }
        else
        {
            result.setText("WRONG :(");
        }
        noOfQuestion++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noOfQuestion));
        newQuestion();

    }

    public void start(View view)
    {
        goButton.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.timerTextView));
        gameLayout.setVisibility(View.VISIBLE);
    }

    public void newQuestion()
    {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrect = rand.nextInt(4);

        answers.clear();

        for (int i = 0 ; i< 4 ; i++)
        {
            if(i == locationOfCorrect)
            {
                answers.add(a+b);
            }
            else
            {
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);

            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = (Button) findViewById(R.id.goButton);
        sumTextView = (TextView) findViewById(R.id.sum);
        button0 = (Button) findViewById(R.id.num1);
        button1 = (Button) findViewById(R.id.num2);
        button2 = (Button) findViewById(R.id.num3);
        button3 = (Button) findViewById(R.id.num4);
        result = (TextView) findViewById(R.id.resultTextView);
        scoreTextView = (TextView) findViewById(R.id.score);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.button4);
        goButton.setVisibility(View.VISIBLE);
        gameLayout = findViewById(R.id.gameLayout);
        gameLayout.setVisibility(View.INVISIBLE);







    }
}