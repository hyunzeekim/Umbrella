package com.umbrella.umbrella;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ChooseContacts extends AppCompatActivity {

    String number;
    Spinner timeInterval;
    ImageButton b1;
    ImageButton b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_contacts);

        timeInterval = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(ChooseContacts.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.intervals));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeInterval.setAdapter(myAdapter);

        b1 = (ImageButton) findViewById(R.id.b1);
        b2 =(ImageButton)findViewById(R.id.b2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Contact 1 was chosen", Toast.LENGTH_SHORT)
                        .show();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Contact 2 was chosen", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void shapeOnClick(View v){
        Intent timeInterval = new Intent(ChooseContacts.this, ShapePage.class);
        timeInterval.putExtra("timeInterval", this.timeInterval.getSelectedItem().toString());
        startActivity(timeInterval);

    }

    public void b1OnClick(View v){

    }

    /*public void clickNumber(View v){
        Button number1;
        number1 =(Button)findViewById(R.id.contact1);
        number1.setOnClickListener(v.OnClickListener());

        String number1Text= ((Button)v).getText().toString();
        System.out.println(number1Text);

    }*/

}
