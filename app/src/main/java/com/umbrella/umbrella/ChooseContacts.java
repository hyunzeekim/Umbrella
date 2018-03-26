package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD
import android.widget.Button;
=======
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
>>>>>>> d45fb0d08e47779a167c2742330f73a3e38e5542

public class ChooseContacts extends AppCompatActivity {

    String contactChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contacts);

        Spinner timeInterval = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ChooseContacts.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.intervals));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeInterval.setAdapter(myAdapter);
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
