//TIMER RUNNING PAGE

package com.umbrella.umbrella;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.content.Intent;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class TimerPage extends AppCompatActivity {
    TextView countdown;
    CountDownTimer countdownTimer;
    static String result;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_page);

        countdown = (TextView) findViewById(R.id.text_view_countdown);

        Intent timeSpinner = getIntent();
        result = timeSpinner.getStringExtra("timeInterval");
        int value = 0;

        //Run timer according to the chosen time interval
        if (result.equalsIgnoreCase("Every 5 Minutes")){
            value = 5*1*1000;
        }
        if (result.equalsIgnoreCase("Every 15 Minutes")){
            value = 15*60*1000;
        }
        else if (result.equalsIgnoreCase("Every 30 Minutes")){
            value = 30*60*1000;
        }

        countdownTimer = new CountDownTimer(value, 1000) {

            //Run timer
            public void onTick(long millisUntilFinished) {
            String timeValue = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)));
            countdown.setText(timeValue);
            }

            //When timer runs out, send notification that it is time for user's check-in
            public void onFinish(){
                sendSMS("5554", "IT IS TIME FOR YOUR SAFETY UMBRELLA CHECK-IN!");
                startActivity(new Intent(TimerPage.this, InputPasswordActivity.class));

            }
        }.start();
    }

    //Send SMS
    public void sendSMS(String phoneNum, String text){
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phoneNum, null, text, null, null);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }
        else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                requestPermissions(new String[]{android.Manifest.permission.SEND_SMS}, 10);
            }
        }
    }

    //When End Journey is pressed, cancel timer
    public void endJourney(View v){
        countdownTimer.cancel();
        startActivity(new Intent(TimerPage.this, Finish.class));
    }

 }


