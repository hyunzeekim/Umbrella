package com.umbrella.umbrella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.content.Intent;
import java.util.concurrent.TimeUnit;

public class ShapePage extends AppCompatActivity {

    TextView countdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_page);

        countdown = (TextView) findViewById(R.id.text_view_countdown);

        Intent timeSpinner = getIntent();
        String result = timeSpinner.getStringExtra("timeInterval");
        int value = 0;
        if (result.equalsIgnoreCase("5 Minutes")){
            value = 5*60*1000;
        }
        if (result.equalsIgnoreCase("10 Minutes")){
            value = 10*60*1000;
        }
        else if (result.equalsIgnoreCase("15 Minutes")){
            value = 15*60*1000;
        }
            new CountDownTimer(value, 1000) {
            public void onTick(long millisUntilFinished) {
            String timeValue = String.format("Time to check-in: %02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            countdown.setText(timeValue);
            }

            public void onFinish() {
                countdown.setText("done!");
            }
        }.start();
    }

        }


