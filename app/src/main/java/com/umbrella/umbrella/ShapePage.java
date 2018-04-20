package com.umbrella.umbrella;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.content.Intent;
import java.util.concurrent.TimeUnit;

public class ShapePage extends AppCompatActivity {

    TextView countdown;
    CountDownTimer countdownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_page);

        countdown = (TextView) findViewById(R.id.text_view_countdown);

        Intent timeSpinner = getIntent();
        String result = timeSpinner.getStringExtra("timeInterval");
        int value = 0;
        if (result.equalsIgnoreCase("Every 5 Minutes")){
            value = 5*1*1000;
        }
        if (result.equalsIgnoreCase("Every 15 Minutes")){
            value = 15*60*1000;
        }
<<<<<<< HEAD
            countdownTimer = new CountDownTimer(value, 1000) {
=======
        else if (result.equalsIgnoreCase("Every 30 Minutes")){
            value = 30*60*1000;
        }
            new CountDownTimer(value, 1000) {
>>>>>>> 2a40c0285901f910c5ad4bc8cd80a7f53a45d6cb
            public void onTick(long millisUntilFinished) {
            String timeValue = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            countdown.setText(timeValue);
            }

            public void onFinish(){
                startActivity(new Intent(ShapePage.this, InputPasswordActivity.class));
            }
        }.start();
    }

    public void endJourney(View v){
        countdownTimer.cancel();
        startActivity(new Intent(ShapePage.this, Finish.class));
    }

}


