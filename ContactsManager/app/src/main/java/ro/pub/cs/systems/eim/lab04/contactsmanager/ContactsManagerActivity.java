package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
    }
    public void onClickFields(View view) {
        RelativeLayout add = (RelativeLayout) findViewById(R.id.Additional);
        Button fields = (Button) view;
        if (add.getVisibility() == View.GONE) {
            fields.setText("Hide Additional Fields");
            add.setVisibility(View.VISIBLE);
        }
        else {
            fields.setText("Show Additional Fields");
            add.setVisibility(View.GONE);
        }
    }

    public void onClickSave(View view) {
        Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
        intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);

        EditText text;
        text = (EditText) findViewById(R.id.Name);
        if (text.getText().toString().length() != 0)
            intent.putExtra(ContactsContract.Intents.Insert.NAME, text.getText());

        text = (EditText) findViewById(R.id.Number);
        if (text.getText().toString().length() != 0)
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, text.getText());

        text = (EditText) findViewById(R.id.Email);
        if (text.getText().toString().length() != 0)
            intent.putExtra(ContactsContract.Intents.Insert.EMAIL, text.getText());

        text = (EditText) findViewById(R.id.Address);
        if (text.getText().toString().length() != 0)
            intent.putExtra(ContactsContract.Intents.Insert.POSTAL, text.getText());

        text = (EditText) findViewById(R.id.Job_Title);
        if (text.getText().toString().length() != 0)
            intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, text.getText());

        text = (EditText) findViewById(R.id.Company);
        if (text.getText().toString().length() != 0)
            intent.putExtra(ContactsContract.Intents.Insert.COMPANY, text.getText());

        ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();

        text = (EditText) findViewById(R.id.Website);
        if (text.getText().toString().length() != 0) {
            ContentValues site = new ContentValues();
            site.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
            site.put(ContactsContract.CommonDataKinds.Website.URL, text.getText().toString());
            contactData.add(site);
        }

        text = (EditText) findViewById(R.id.IM);
        if (text.getText().toString().length() != 0) {
            ContentValues im = new ContentValues();
            im.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
            im.put(ContactsContract.CommonDataKinds.Im.DATA, text.getText().toString());
            contactData.add(im);
        }

        intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
        startActivity(intent);
    }

    public void onClickCancel(View view) {
        finish();
    }
}
