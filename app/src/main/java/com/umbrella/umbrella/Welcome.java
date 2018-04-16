package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Toast;
import android.content.pm.PackageManager;
import android.os.Build;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void beginOnClick(View v){
        startActivity(new Intent(Welcome.this, ChooseContacts.class));
    }

    public void settingOnClick(View v){
        startActivity(new Intent(Welcome.this, CreatePasswordActivity.class));
    }

}