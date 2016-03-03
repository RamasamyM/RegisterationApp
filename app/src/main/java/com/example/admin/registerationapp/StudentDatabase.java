package com.example.admin.registerationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 2/10/2016.
 */



public class StudentDatabase extends SQLiteOpenHelper {

    public StudentDatabase(Context context)
    {
        super(context, "studentdb", null, 1);
    }
    String STUDENT_DATA = "student_details";
    String col1 = "studentname";
    String col2 = "regid";
    String col3 = "address";
    String col4 = "mobileNo";
    String col5 = "e_mail";



    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE "+STUDENT_DATA+"(_id INTEGER PRIMARY KEY AUTOINCREMENT, "+col1 +" VARCHAR, "+ col2+" LONG, "
                +col3 +" VARCHAR, "+col4 +" LONG, "+col5+" VARCHAR );";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("studentdb", "Upgrade getting called");
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }
    public void insert(ContentValues values) {

        SQLiteDatabase db= getWritableDatabase();
        db.insert(STUDENT_DATA, null , values);
    }

    public Cursor getDataBase()
    {
        ArrayList<HashMap<String, String>> wordList;
        wordList = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = getReadableDatabase();
        String tableData = ("select * from " + STUDENT_DATA);

        Cursor c = db.rawQuery(tableData, null);
        if(c.moveToFirst())
        {
            do
            {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("Name", c.getString(1));
                map.put("regId", c.getString(2));
                map.put("mobile", c.getString(4));
                wordList.add(map);
            }while(c.moveToNext());
        }
        return c;

    }

}
