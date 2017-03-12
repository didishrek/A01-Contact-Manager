package com.griffith.assignment1.contactManager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by 42900 on 28/02/2017 for Assignment1.
 */

public class ContactDBOpenHelper extends SQLiteOpenHelper {
    private static String TAG = "CONTACTDPOPENHELPER";
    private static String TABLE = "contact";

    private static final String create_table = "create table if not exists contact(" +
            "ID integer primary key autoincrement, " +
            "NAME string, " +
            "HOME_PHONE string, " +
            "MOBILE_PHONE string, " +
            "EMAIL string" +
            ")";
    private static final String drop_table = "drop table contact";

    public ContactDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int version_old, int version_new) {
        sqLiteDatabase.execSQL(drop_table);
        sqLiteDatabase.execSQL(create_table);
    }

    //get all contacts
    public ArrayList<Contact> getContacts(SQLiteDatabase sqLiteDatabase){
        ArrayList<Contact> contacts = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query(TABLE, null, null, null, null, null, "NAME");
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); ++i){
            contacts.add(new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            cursor.moveToNext();
        }
        cursor.close();
        return contacts;
    }

    //get contact by id
    public Contact getContact(SQLiteDatabase sqLiteDatabase, int id){
        String[] where = { String.valueOf(id) };
        Cursor cursor = sqLiteDatabase.query(TABLE, null, "id = ?",  where, null, null, null);
        cursor.moveToFirst();
        Contact c = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        cursor.close();
        return c;
    }

    //add a contact
    public void addContact(SQLiteDatabase sqLiteDatabase, Contact contact) throws Exception{
        isContactValid(sqLiteDatabase, contact, false);
        ContentValues cv = new ContentValues();
        cv.put("NAME", contact.getName());
        cv.put("HOME_PHONE", contact.getHome_phone());
        cv.put("MOBILE_PHONE", contact.getMobile_phone());
        cv.put("EMAIL", contact.getEmail());
        sqLiteDatabase.insert(TABLE, null, cv);
        Log.v(TAG, "Contact added");
    }

    //update a contact
    public void editContact(SQLiteDatabase sqLiteDatabase, Contact contact) throws Exception{
        isContactValid(sqLiteDatabase, contact, true);
        String strFilter = "ID =" + contact.getId();
        ContentValues cv = new ContentValues();
        cv.put("NAME", contact.getName());
        cv.put("HOME_PHONE", contact.getHome_phone());
        cv.put("MOBILE_PHONE", contact.getMobile_phone());
        cv.put("EMAIL", contact.getEmail());
        sqLiteDatabase.update(TABLE, cv, strFilter, null);
    }


    //check if contact already exist
    public void isContactValid(SQLiteDatabase sqLiteDatabase, Contact contact, Boolean forupdate) throws Exception{
        if (contact.getName().isEmpty())
            throw new Exception("Contact name cannot be empty");
        if (!contact.getEmail().isEmpty())
            if (!contact.getEmail().matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*)@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]+)\\])"))
                throw new Exception("That email does not match");

        String[] where = { contact.getName().toLowerCase() };
        Cursor cursor = sqLiteDatabase.query(TABLE, null, "LOWER(NAME) = ?" ,where, null, null, null);
        cursor.moveToFirst();
        Boolean found = false;
        if (forupdate){
            for (int i = 0; i < cursor.getCount(); ++i){
                if (cursor.getInt(0) != contact.getId()) {
                    found = true;
                    break;
                }
                cursor.moveToNext();
            }
        } else {
            found = (cursor.getCount() > 0);
        }
        cursor.close();
        if (found)
            throw new Exception("This contact is already registered");

    }


    //remove contact
    public boolean rmContact(SQLiteDatabase sqLiteDatabase, int id){
        return sqLiteDatabase.delete(TABLE, "id = " + id, null) > 0;
    }
}
