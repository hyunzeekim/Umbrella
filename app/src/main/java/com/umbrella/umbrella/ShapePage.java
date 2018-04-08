package com.umbrella.umbrella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.os.CountDownTimer;

public class ShapePage extends AppCompatActivity {

    TextView countdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_page);
        countdown = (TextView) findViewById(R.id.text_view_countdown);
        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countdown.setText("done!");
            }
        }.start();
    }

        }


