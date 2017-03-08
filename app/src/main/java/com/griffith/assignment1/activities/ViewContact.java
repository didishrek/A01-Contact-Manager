package com.griffith.assignment1.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.griffith.assignment1.R;
import com.griffith.assignment1.contactManager.Contact;
import com.griffith.assignment1.contactManager.ContactDBOpenHelper;

public class ViewContact extends AppCompatActivity {

    private TextView viewName;
    private TextView viewHomePhone;
    private TextView viewMobilePhone;
    private TextView viewEmail;

    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);

        viewName = (TextView) findViewById(R.id.view_contact_name);
        viewHomePhone = (TextView) findViewById(R.id.view_contact_home_phone);
        viewMobilePhone = (TextView) findViewById(R.id.view_contact_mobile_phone);
        viewEmail = (TextView) findViewById(R.id.view_contact_email);



        contactDBOpenHelper = new ContactDBOpenHelper(this, "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();

        int id_contact = getIntent().getIntExtra("ID_CONTACT", -1);
        Contact contact = contactDBOpenHelper.getContact(sqLiteDatabase, id_contact);
        Log.d("TAG", contact.toString());

        viewName.setText(contact.getName());
        viewHomePhone.setText(contact.getHome_phone());
        viewMobilePhone.setText(contact.getMobile_phone());
        viewEmail.setText(contact.getEmail());

    }
}
