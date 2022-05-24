package com.example.testvcf;

import java.util.ArrayList;

public class TempContacts {
    public String name;
    public ArrayList<String> phno = new ArrayList<>();
    int spaceRequired;

    public TempContacts(String name, String number) {
        this.name = name;
        addPhoneNumber(number);
    }

    public TempContacts addPhoneNumber(String phoneNumber) {
        this.phno.add(phoneNumber);
        return this;
    }

    public boolean hasPhoneNumber(String pno) {
        return this.phno.contains(pno);
    }
}
