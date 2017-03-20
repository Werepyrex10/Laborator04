package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.nfc.Tag;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);

        Intent intent = getIntent();

        if (intent != null) {
            String phone = intent.getStringExtra("ro.pub.cs.systems.eim.lab04.contactsmanager.PHONE_NUMBER_KEY");
            if (phone != null) {
                EditText number = (EditText) findViewById(R.id.Number);
                number.setText(phone);
            }
            else {
                Toast.makeText(this, "Can't retrieve phone number", Toast.LENGTH_SHORT).show();
            }
        }
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
        startActivityForResult(intent, 1);
    }

    public void onClickCancel(View view) {
        setResult(Activity.RESULT_CANCELED, new Intent());
        finish();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            setResult(resultCode, new Intent());
            finish();
        }
    }
}
