package com.example.order.XuLy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.order.Data.DBManager;

public class XuLyDangNhap {

    SQLiteDatabase db;
    DBManager dbManager;


    public XuLyDangNhap(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }


    //lay passoword
    public Cursor checkPass(String username) {

        String getPass = "select * from " + dbManager.TB_STAFF+" where "+dbManager.NAME_STAFF+" = "+username;
        Cursor cs = db.rawQuery(getPass, null);

        cs.moveToFirst();

        return cs;
    }

}
