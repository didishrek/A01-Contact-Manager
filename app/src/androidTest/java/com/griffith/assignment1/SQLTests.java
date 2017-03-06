package com.griffith.assignment1;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.griffith.assignment1.contactManager.Contact;
import com.griffith.assignment1.contactManager.ContactDBOpenHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by 42900 on 06/03/2017 for Assignment1.
 */

@RunWith(AndroidJUnit4.class)
public class SQLTests {
    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    @Before
    public void initTests(){
        contactDBOpenHelper = new ContactDBOpenHelper(InstrumentationRegistry.getTargetContext(), "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();
    }

    @Test
    public void testContacts(){
        ArrayList<Contact> contacts = contactDBOpenHelper.getContacts(sqLiteDatabase);
        assertTrue(contacts.isEmpty());
    }
}
