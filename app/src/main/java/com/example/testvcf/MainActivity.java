package com.example.testvcf;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String fileName = "testContact";
        String s = fileName.trim();
        File mainFolder = Environment.getExternalStorageDirectory();
        File myFolder = new File(mainFolder.getAbsolutePath(), "folderContact");
        if (!myFolder.exists()) {
            myFolder.mkdirs();
        }

        String fullFileName3 = s + ".vcf";
        File file3 = new File(myFolder.getAbsolutePath(), fullFileName3);

        Cursor c = MainActivity.this.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{"sort_key", "data1"}, null, null, "sort_key ASC");
        while (c.moveToNext()) {
            String num = c.getString(1).replaceAll("([- )(]|\\+98)", "");
            TempContactHandler.addContact(c.getString(0), num);
        }
        c.close();



        VCardCreator creator = new VCardCreator(file3, new TempContactHandler().tempContacts);
        creator.createVCFFile();



    }




}