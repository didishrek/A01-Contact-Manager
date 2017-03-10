package com.griffith.assignment1.activities;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.griffith.assignment1.R;
import com.griffith.assignment1.contactManager.Contact;

import java.util.ArrayList;

/**
 * Created by 42900 on 07/03/2017 for Assignment1.
 */

public class CustomListAdapter extends ArrayAdapter<Contact> {

    private final Activity context;
    private final ArrayList<Contact> contacts;

    public CustomListAdapter(Activity context, ArrayList<Contact> contacts) {
        super(context, R.layout.list_contact, contacts);
        this.context = context;
        this.contacts = contacts;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_contact, null, false);

        TextView contactName = (TextView) rowView.findViewById(R.id.ContactName);

        contactName.setText(contacts.get(position).getName());
        return rowView;
    }
}