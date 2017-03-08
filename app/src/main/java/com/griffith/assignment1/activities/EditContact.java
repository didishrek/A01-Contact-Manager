package com.griffith.assignment1.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.griffith.assignment1.R;
import com.griffith.assignment1.contactManager.Contact;
import com.griffith.assignment1.contactManager.ContactDBOpenHelper;

public class EditContact extends AppCompatActivity {

    private EditText editName;
    private EditText editHomePhone;
    private EditText editMobilePhone;
    private EditText editEmail;

    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        editName = (EditText) findViewById(R.id.edit_contact_name);
        editHomePhone = (EditText) findViewById(R.id.edit_contact_home_phone);
        editMobilePhone = (EditText) findViewById(R.id.edit_contact_mobile_phone);
        editEmail = (EditText) findViewById(R.id.edit_contact_email);

        contactDBOpenHelper = new ContactDBOpenHelper(this, "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();

        int id_contact = getIntent().getIntExtra("ID_CONTACT", -1);
        Contact contact = contactDBOpenHelper.getContact(sqLiteDatabase, id_contact);
        Log.d("TAG", contact.toString());

        editName.setText(contact.getName());
        editHomePhone.setText(contact.getHome_phone());
        editMobilePhone.setText(contact.getMobile_phone());
        editEmail.setText(contact.getEmail());


    }
}
