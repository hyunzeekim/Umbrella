//WELCOME PAGE

package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    //Switch to Choose Contacts Page
    public void beginOnClick(View v){
        startActivity(new Intent(Welcome.this, ChooseContacts.class));
    }

    //Settings allow for changing Shape
    public void settingOnClick(View v){
        startActivity(new Intent(Welcome.this, CreatePasswordActivity.class));
    }

}