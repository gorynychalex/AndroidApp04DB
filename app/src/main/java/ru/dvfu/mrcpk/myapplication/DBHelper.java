package ru.dvfu.mrcpk.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personDB";
    public static final String TABLE_MAIN = "personTable";

    public static final String TABLE_MAIN_ID = "_id";
    public static final String TABLE_MAIN_FIRSTNAME = "firstname";
    public static final String TABLE_MAIN_LASTNAME = "lastname";
    public static final String TABLE_MAIN_EMAIL = "eMail";
    public static final String TABLE_MAIN_PHONENUM = "phoneNum";

    public static final String CREATE_TABLE = "create table " + TABLE_MAIN + "(" + TABLE_MAIN_ID + " integer primary key, "
            + TABLE_MAIN_FIRSTNAME + " text, " + TABLE_MAIN_LASTNAME + " text, "
            + TABLE_MAIN_PHONENUM + " text, " + TABLE_MAIN_EMAIL + " text" + ")";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_MAIN_FIRSTNAME, "Ivan");
        contentValues.put(TABLE_MAIN_LASTNAME, "Ivanov");
        db.insert(TABLE_MAIN, null, contentValues);

        contentValues = new ContentValues();
        contentValues.put(TABLE_MAIN_FIRSTNAME, "Petr");
        contentValues.put(TABLE_MAIN_LASTNAME, "Petrov");
        db.insert(TABLE_MAIN, null, contentValues);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_MAIN);

        onCreate(db);
    }
}
