package com.example.gestionregistrant.sqlite;

import android.provider.BaseColumns;

public final class UserDatabase {

    private UserDatabase() {}

    public final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COLUMN_FIRSTNAME = "firstName";
        public static final String COLUMN_LASTNAME = "lastName";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_NUMTEL = "numTel";
        public static final String CREATE_USER_TABLE =
                "CREATE TABLE " + UserEntry.TABLE_NAME  + "(" + _ID + " INTEGER PRIMARY KEY, "
                + UserEntry.COLUMN_FIRSTNAME + " TEXT NOT NULL, " + UserEntry.COLUMN_LASTNAME + " TEXT NOT NULL, " + COLUMN_EMAIL +
                        " TEXT NOT NULL, " + UserEntry.COLUMN_NUMTEL + ")";
    }

}
