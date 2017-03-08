package com.griffith.assignment1.activities;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.griffith.assignment1.R;
import com.griffith.assignment1.contactManager.Contact;
import com.griffith.assignment1.contactManager.ContactDBOpenHelper;

import static android.widget.Toast.makeText;

public class AddContact extends AppCompatActivity {

    private EditText addName;
    private EditText addHomePhone;
    private EditText addMobilePhone;
    private EditText addEmail;
    
    private Button button_add_contact;

    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        addName = (EditText) findViewById(R.id.add_contact_name);
        addHomePhone = (EditText) findViewById(R.id.add_contact_home_phone);
        addMobilePhone = (EditText) findViewById(R.id.add_contact_mobile_phone);
        addEmail = (EditText) findViewById(R.id.add_contact_email);
        
        contactDBOpenHelper = new ContactDBOpenHelper(this, "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();

        button_add_contact = (Button) findViewById(R.id.button_add_validate_contact);
        
        button_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact(-1, addName.getText().toString(), addHomePhone.getText().toString(),addMobilePhone.getText().toString(), addEmail.getText().toString());
                try {
                    contactDBOpenHelper.addContact(sqLiteDatabase, contact);
                    Toast toast = Toast.makeText(AddContact.this, "Contact added", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                    setResult(RESULT_OK);
                } catch (Exception e){
                    Log.e("ERROR", e.getMessage());
                }
            }
        });
    }
}
