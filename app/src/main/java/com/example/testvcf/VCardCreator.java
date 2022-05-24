package com.example.testvcf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class VCardCreator {
    ArrayList<TempContacts> contactList;
    File file;
    int maxContacts = 50;
    int currentContact = 0;

    public VCardCreator(File file, ArrayList<TempContacts> contacts) {
        this.file = file;
        this.contactList = contacts;
    }

    public void createVCFFile() {
        Throwable th;
        IOException e;
        this.currentContact = 0;
        BufferedWriter writer = null;
        try {
            BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.file)));
            while (this.currentContact < this.contactList.size()) {
                try {
                    writer2.write(getMaxContactsString());
                    System.out.println("loop");
                } catch (FileNotFoundException e2) {
                    e = e2;
                    writer = writer2;
                    e.printStackTrace();
                    try {
                        writer.close();
                        return;
                    } catch (IOException e3) {
                        e3.printStackTrace();
                        return;
                    }
                } catch (IOException e4) {
                    e = e4;
                    writer = writer2;
                    e.printStackTrace();
                    try {
                        writer.close();
                        return;
                    } catch (IOException e5) {
                        e5.printStackTrace();
                        return;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    writer = writer2;
                    try {
                        writer.close();
                    } catch (IOException e6) {
                        e6.printStackTrace();
                    }
                    throw th;
                }
            }
            try {
                writer2.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public String getMaxContactsString() {
        StringBuilder builder = new StringBuilder();
        for (int num = 0; this.currentContact < this.contactList.size() && num < this.maxContacts; num++) {
            TempContacts tempContact = this.contactList.get(this.currentContact);
            builder.append("BEGIN:VCARD\n");
            builder.append("VERSION:3.0\n");
            builder.append("N:;" + tempContact.name + ";;;\n");
            builder.append("FN:" + tempContact.name + "\n");
            Iterator<String> it = tempContact.phno.iterator();
            while (it.hasNext()) {
                String phno = it.next();
                builder.append("TEL;TYPE=CELL:" + phno + "\n");
            }
            builder.append("END:VCARD\n");
            this.currentContact++;
        }
        return builder.toString();
    }
}