package com.example.contentprovides;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView list;
    ArrayList<String>listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=findViewById(R.id.list);

        listdata=new ArrayList<String>();

        fetchcontect();
    }

    private void fetchcontect() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},0);
        }
        ContentResolver resolver=getContentResolver();
        Uri uri= ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        String[] projection=null;    //use to access column-wise Data
        String selection=null;     //use to access Row-Wise Data
        String[] selectonArgs=null;
        String order=null;

        Cursor cursor= resolver.query(uri, projection, selection, selectonArgs, order);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                int name_column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
                int number_column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

                String name=cursor.getString(name_column);
                String number=cursor.getString(number_column) ;

                String mycontact=name+ "\n" + number;
                listdata.add(mycontact);
            }
        }

        ArrayAdapter<String>adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listdata);
        list.setAdapter(adapter);
    }
}