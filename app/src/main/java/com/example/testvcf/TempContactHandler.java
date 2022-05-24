package com.example.testvcf;

import java.util.ArrayList;

public class TempContactHandler {
    private static int max=0;
    private static int current =0;

    static ArrayList<TempContacts> tempContacts = new ArrayList<>();

    public static void addContact(String name, String phno) {
        if (name.length() > max) {
            max = name.length();
        }
        if (current > 0) {
            if (tempContacts.get(current - 1).phno.equals(phno)) {
            }
            if (name.equals(tempContacts.get(current - 1).name)) {
                if (!tempContacts.get(current - 1).hasPhoneNumber(phno)) {
                    tempContacts.get(current - 1).addPhoneNumber(phno);
                    return;
                }
                return;
            }
        }
        current++;
        tempContacts.add(new TempContacts(name, phno));
    }

    public void clear() {
        tempContacts.clear();
        max = 0;
        current = 0;
    }

    public int getTotalContacts() {
        return tempContacts.size();
    }

}
