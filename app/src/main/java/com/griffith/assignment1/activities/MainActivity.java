package com.griffith.assignment1.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.griffith.assignment1.R;
import com.griffith.assignment1.contactManager.ContactDBOpenHelper;

public class MainActivity extends AppCompatActivity {

    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactDBOpenHelper = new ContactDBOpenHelper(this, "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();
    }
}
