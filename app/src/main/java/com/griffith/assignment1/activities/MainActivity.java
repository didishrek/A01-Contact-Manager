package com.griffith.assignment1.activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.griffith.assignment1.R;
import com.griffith.assignment1.contactManager.Contact;
import com.griffith.assignment1.contactManager.ContactDBOpenHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private FloatingActionButton button_add_contact;

    private ContactDBOpenHelper contactDBOpenHelper;
    private SQLiteDatabase sqLiteDatabase;
    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactDBOpenHelper = new ContactDBOpenHelper(this, "contact.db", null, 1);
        sqLiteDatabase = contactDBOpenHelper.getWritableDatabase();

        list = (ListView) findViewById(R.id.listView_contact);
        updateList();

        button_add_contact = (FloatingActionButton) findViewById(R.id.button_add_contact);

        registerForContextMenu(list);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ViewContact.class);
                intent.putExtra("ID_CONTACT", contacts.get(i).getId());
                startActivity(intent);
            }
        });

        button_add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateList();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Select The Action");
        menu.add(0, v.getId(), 0, "Edit");
        menu.add(0, v.getId(), 0, "Delete");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int selected_item = (int) info.id;
        if(item.getTitle() == "Edit"){
            Intent intent = new Intent(MainActivity.this, EditContact.class);
            intent.putExtra("ID_CONTACT", contacts.get(selected_item).getId());
            startActivity(intent);
        }
        else if(item.getTitle() == "Delete"){
            contactDBOpenHelper.rmContact(sqLiteDatabase, contacts.get(selected_item).getId());
            updateList();
        }else{
            return false;
        }
        return true;
    }

    public void updateList(){
        contacts = contactDBOpenHelper.getContacts(sqLiteDatabase);
        CustomListAdapter adapter = new CustomListAdapter(this, contacts);
        list.setAdapter(adapter);
    }
}
