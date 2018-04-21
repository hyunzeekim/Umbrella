package com.umbrella.umbrella;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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
    Spinner timeInterval;

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

    public String contact1OnClick(View v){
        final EditText bt1 =  (EditText) findViewById(R.id.etcontact);
        String phoneNum = (bt1.getText().toString());
        return phoneNum;
    }

    public String contact2OnClick(View v){
        final EditText bt2 =  (EditText) findViewById(R.id.etcontact2);
        String phoneNum = (bt2.getText().toString());
        return phoneNum;
    }


    public void shapeOnClick(View v) {
        Intent timeInterval = new Intent(ChooseContacts.this, ShapePage.class);
        sendSMS("5556", "Hi there! Contact 5554 has started their Umbrella journey. Stay tuned!");
        timeInterval.putExtra("timeInterval", this.timeInterval.getSelectedItem().toString());
        startActivity(timeInterval);

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
