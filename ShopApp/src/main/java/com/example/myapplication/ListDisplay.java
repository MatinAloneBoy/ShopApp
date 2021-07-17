package com.example.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListDisplay extends Activity {
    // Array of strings...
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_profile);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.fragment_profile, mobileArray);

        ListView listView = (ListView) findViewById(R.id.profile_fragment_list);
        listView.setAdapter(adapter);
    }
}