package com.example.eatall.model;

import java.util.Locale;

public class PhoneItem {

    public final long _id;
    public final String title;
    public final String cal;
    public final String time;
    public final String image;

    public PhoneItem(long _id, String title, String number, String image ,String time) {
        this._id = _id;
        this.title = title;
        this.cal = number;
        this.time = time;
        this.image = image;
    }

    @Override
    public String toString() {
        String msg = String.format(
                Locale.getDefault(),
                "%s (%s)",
                this.title,
                this.cal
        );
        return msg;
    }
}
