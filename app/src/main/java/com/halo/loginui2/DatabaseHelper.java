package com.halo.loginui2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL_ = "email";
    private static final String COLUMN_PASS = "pass";
    private static final String COLUMN_ID = "id";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, " +
            "name text not null, email text not null, pass text not null);";
    public DatabaseHelper(Context context)
    {
        super (context , DATABASE_NAME , null , DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db  ) {
        db.execSQL(TABLE_CREATE);
        this.db = db;

    }

    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // this if for when we want to change the id of the user EG, USER 1, 2 ,3
        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME , c.getName());
        values.put(COLUMN_PASS , c.getPass());
        values.put(COLUMN_EMAIL_, c.getEmail());
        db.insert(TABLE_NAME, null , values);
        db.close();
    }
    public String searchPass (String name)
    {
        db = this.getReadableDatabase();
        String query = "select name, pass from"+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if(cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(name)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;


       // db.execSQL(query);

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
