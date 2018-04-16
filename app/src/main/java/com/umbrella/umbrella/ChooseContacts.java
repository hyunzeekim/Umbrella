package com.umbrella.umbrella;

import android.content.Intent;
import android.content.SharedPreferences;
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
    EditText etcontact;
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
    }

    public void contact1OnClick(View v){
        final EditText bt1 =  (EditText) findViewById(R.id.etcontact);
        int phoneNum = Integer.parseInt(bt1.getText().toString());
    }

    public void contact2OnClick(View v){
        final EditText bt2 =  (EditText) findViewById(R.id.etcontact2);
        int phoneNum = Integer.parseInt(bt2.getText().toString());
    }

    public void shapeOnClick(View v){
        Intent timeInterval = new Intent(ChooseContacts.this, ShapePage.class);
        timeInterval.putExtra("timeInterval", this.timeInterval.getSelectedItem().toString());
        startActivity(timeInterval);

    }


}
