package com.example.admin.registerationapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.mobileapptracker.MobileAppTracker;

public class Main extends ActionBarActivity {



    EditText userName, userPassword;
    Button login;
    CheckBox keepMe;
    SharedPreferences sharedPref;
    MobileAppTracker mobileAppTracker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        accessAppTracking();
        login = (Button) findViewById(R.id.login);
        userName = (EditText) findViewById(R.id.userName);
        userPassword = (EditText) findViewById(R.id.userPassword);
        keepMe = (CheckBox) findViewById(R.id.keepMeSigned);

        sharedPref = getSharedPreferences("logindetails", MODE_PRIVATE);

        if(sharedPref.contains("username") && sharedPref.contains("password")){
            Intent page = new Intent(Main.this, Homepage.class);
            startActivity(page);
            finish();
        }


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent activity2 = new Intent(Main.this, Homepage.class);
                startActivity(activity2);

                String userNameText = userName.getText().toString();
                String passwordText = userPassword.getText().toString();


                if(!userNameText.equals("") && !passwordText.equals("")){

                    if(keepMe.isChecked()) {
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString("username", userNameText);
                        editor.putString("password", passwordText);
                        editor.commit();
                    }

                    Intent homepage = new Intent(Main.this, Homepage.class);
                    startActivity(homepage);

                } else {
                    Toast.makeText(Main.this, "Please Enter user Name..", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void accessAppTracking()
    {
        mobileAppTracker = MobileAppTracker.init(getApplicationContext(),
                "your_advertiser_ID",
                "your_conversion_key");
        mobileAppTracker.setAndroidId(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID));
        String deviceId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
        mobileAppTracker.setDeviceId(deviceId);
        try {
            WifiManager wm = (WifiManager) getSystemService(Context.WIFI_SERVICE);
            mobileAppTracker.setMacAddress(wm.getConnectionInfo().getMacAddress());
        } catch (NullPointerException e) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mobileAppTracker.setReferralSources(this);
        // MAT will not function unless the measureSession call is included
        mobileAppTracker.measureSession();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
