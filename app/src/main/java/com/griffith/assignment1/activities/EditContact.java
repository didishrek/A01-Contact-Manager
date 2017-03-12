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

public class EditContact extends AppCompatActivity {

    private EditText editName;
    private EditText editHomePhone;
    private EditText editMobilePhone;
    private EditText editEmail;
    private Button validate_button;

    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        editName = (EditText) findViewById(R.id.edit_contact_name);
        editHomePhone = (EditText) findViewById(R.id.edit_contact_home_phone);
        editMobilePhone = (EditText) findViewById(R.id.edit_contact_mobile_phone);
        editEmail = (EditText) findViewById(R.id.edit_contact_email);

        validate_button = (Button) findViewById(R.id.button_edit_validate_contact);

        contactDBOpenHelper = new ContactDBOpenHelper(this, "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();

        int id_contact = getIntent().getIntExtra("ID_CONTACT", -1);
        contact = contactDBOpenHelper.getContact(sqLiteDatabase, id_contact);

        editName.setText(contact.getName());
        editHomePhone.setText(contact.getHome_phone());
        editMobilePhone.setText(contact.getMobile_phone());
        editEmail.setText(contact.getEmail());

        validate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    contact.setName(editName.getText().toString());
                    contact.setHome_phone(editHomePhone.getText().toString());
                    contact.setMobile_phone(editMobilePhone.getText().toString());
                    contact.setEmail(editEmail.getText().toString());
                    contactDBOpenHelper.editContact(sqLiteDatabase, contact);
                    Toast toast = Toast.makeText(EditContact.this, "Contact edited", Toast.LENGTH_SHORT);
                    toast.show();
                    finish();
                    setResult(RESULT_OK);
                } catch (Exception e){
                    Toast toast = Toast.makeText(EditContact.this, e.getMessage(), Toast.LENGTH_SHORT);
                    toast.show();
                    Log.e("ERROR", e.getMessage());
                }
            }
        });
    }
}
