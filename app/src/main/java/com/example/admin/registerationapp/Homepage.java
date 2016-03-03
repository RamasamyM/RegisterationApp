package com.example.admin.registerationapp;

        import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Homepage extends Main{

    SharedPreferences sharedPref;
    String userName;
    TextView value1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        value1=(TextView) findViewById(R.id.user);

        sharedPref = getSharedPreferences("logindetails", MODE_PRIVATE);

        if(sharedPref.contains("username") && sharedPref.contains("password")){
            userName = sharedPref.getString("username", "");
            value1.setText(userName);

        } else {
            Toast.makeText(Homepage.this, "No UserName and Password Avail", Toast.LENGTH_LONG).show();
        }

        Button add= (Button) findViewById(R.id.addStudent);
        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent addstudent = new Intent(Homepage.this, AddStudent.class);

                startActivity(addstudent);
            }
        });
        Button get= (Button) findViewById(R.id.getDetails);
        get.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent getstudent = new Intent(Homepage.this,StudentDetails.class);

                startActivity(getstudent);
            }
        });
        Button del= (Button) findViewById(R.id.del_details);
        del.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent delstudent = new Intent(Homepage.this,StudentDetails.class);

                startActivity(delstudent);
            }
        });
    }

}
