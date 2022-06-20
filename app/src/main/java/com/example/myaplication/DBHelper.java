package com.example.myaplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String database_name = "db_vswbookstore";
    public static final String tabel_name = "table_buku";

    public static final String row_id = "_id";
    public static final String row_judul = "Judul";
    public static final String row_harga = "TglHarga";
    public static final String row_deskripsi = "Deskripsi";
    public static final String row_status = "Status";

    private SQLiteDatabase db;

    public DBHelper(@Nullable Context context) {
        super(context, database_name, null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE "+ tabel_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_judul + " TEXT, " + row_harga + " TEXT, " + row_deskripsi + " TEXT, " + row_status + " TEXT) ";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tabel_name);

    }

    //Get All SQL data
    public Cursor allData(){
        Cursor cur = db.rawQuery(" SELECT * FROM " + tabel_name + " ORDER BY " + row_id + " DESC", null);
        return cur;
    }

    //GET 1 data  By Id
    public Cursor oneData(long id){
        Cursor cur = db.rawQuery(" SELECT * FROM " + tabel_name + " WHERE " + row_id + "=" + id, null);
        return cur;
    }

    //Insert Data

    public void insertData (ContentValues values){
        db.insert(tabel_name, null, values);
    }

    //update Data
    public void updateData (ContentValues values, long id){
        db.update(tabel_name, values, row_id + "=" + id, null);
    }

    //Delete data
    public void deleteData (long id){
        db.delete(tabel_name, row_id + "=" + id, null);
    }

    public Cursor al() {
        return null;
    }
}
