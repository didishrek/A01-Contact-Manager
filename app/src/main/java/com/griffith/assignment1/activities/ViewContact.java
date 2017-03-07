package com.griffith.assignment1.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.griffith.assignment1.R;

public class ViewContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        Log.d("TAG", String.valueOf(getIntent().getIntExtra("ID_CONTACT", -1)));
    }
}
