package com.example.admin.registerationapp;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 2/10/2016.
 */

public class StudentDetails extends ListActivity {

    ListView listView;

    StudentDatabase sdb;

    ArrayList<HashMap<String, String>> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_data);

        sdb = new StudentDatabase(this);

        list =new ArrayList<HashMap<String, String>>();


        ListAdapter adapter = new SimpleAdapter(StudentDetails.this, list,
                R.layout.get_single, new String[] {"Name", "regId", "mobile"},
                new int[]{R.id.getName, R.id.regId, R.id.mobileNo});
		/*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	    		R.layout.get_single_data, R.id.getName, strignArray);*/

        setListAdapter(adapter);

    }

}
