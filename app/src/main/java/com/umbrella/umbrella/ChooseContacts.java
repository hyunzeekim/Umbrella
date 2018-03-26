package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseContacts extends AppCompatActivity {

    String contactChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contacts);
    }

    public void shapeOnClick(View v){
        startActivity(new Intent(ChooseContacts.this, ShapePage.class));
    }

    public void clickNumber(View v){
        Button number1;
        number1 =(Button)findViewById(R.id.contact1);
        number1.setOnClickListener(v.OnClickListener());

        String number1Text= ((Button)v).getText().toString();
        System.out.println(number1Text);

    }

}
