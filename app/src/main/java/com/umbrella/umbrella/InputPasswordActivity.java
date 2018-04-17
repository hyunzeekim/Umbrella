package com.umbrella.umbrella;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;

import java.util.List;

public class InputPasswordActivity extends AppCompatActivity {

    PatternLockView mPatternLockView;

    String password;

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
                int numWrong = 0;
                String phoneNum = c.contact1OnClick(null);

                if (password.equals(PatternLockUtils.patternToString(mPatternLockView, pattern))) {
                    Intent intent = new Intent(getApplicationContext(), ShapePage.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(InputPasswordActivity.this, "Wrong Shape!", Toast.LENGTH_SHORT).show();
                    mPatternLockView.clearPattern();
                    numWrong++;

                    if (numWrong >= 3) {
                        c.sendSMS(phoneNum, "Your user may be in danger! Check up on her!");
                        Intent intent2 = new Intent(getApplicationContext(), ShapePage.class);
                        startActivity(intent2);
                    }

                }
            }



            @Override
            public void onCleared() {

            }
        });
    }

    ChooseContacts c = new ChooseContacts();

}
