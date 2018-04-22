//USER INPUTS SHAPE

package com.umbrella.umbrella;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.Toast;


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

            //Entering in shape
            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {

                //If correct shape, return to Timer Page
                if (password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                    returnToTimerPage();

                } else {
                    Toast.makeText(InputPasswordActivity.this, "Wrong Shape!", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                    passwordAttempts++;

                    //If incorrect more than three times, notify emergency contact and return to timer page
                    if (passwordAttempts >= 3) {
                        sendSMS(c.phoneNum, "Your contact may be in danger! Check up on her!");
                        returnToTimerPage();
                    }
                }
            }

            @Override
            public void onCleared() {

            }
        });
    }

    //Return to timer page
    public void returnToTimerPage() {
        Intent intent = new Intent(this, TimerPage.class);
        intent.putExtra("timeInterval", s.result);
        startActivity(intent);
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

    ChooseContacts c = new ChooseContacts();
    TimerPage s = new TimerPage();

}
