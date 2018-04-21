package com.umbrella.umbrella;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class InputPasswordActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;

    String password;
    int passwordAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_password);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        password = preferences.getString("password", "0");

        mPatternLockView = (PatternLockView) findViewById(R.id.pattern_lock_view);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                //String phoneNum = c.contact1OnClick(null);

                if (password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                    returnToShapePage();

                } else {
                    Toast.makeText(InputPasswordActivity.this, "Wrong Shape!", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                    passwordAttempts++;

                    if (passwordAttempts >= 3) {
                        sendSMS("5556", "Your user may be in danger! Check up on her!");
                        returnToShapePage();
                    }
                }
            }

            public void missedCheck(){
                new Timer().schedule(new TimerTask(){
                    public void run() {
                        InputPasswordActivity.this.runOnUiThread(new Runnable() {
                            public void run() {
                                sendSMS("5556", "Your contact missed a checkin. Please contact them to ensure of their safety.");
                                returnToShapePage();
                            }
                        });
                    }
                }, 2000);
            }

            @Override
            public void onCleared() {

            }
        });
    }

    public void returnToShapePage() {
        Intent intent = new Intent(this, ShapePage.class);
        intent.putExtra("timeInterval", "Every 5 Minutes");
        startActivity(intent);
    }


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


}
