package com.example.order.XuLy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;

public class XyLyDangNhap {

    SQLiteDatabase db;
    DBManager dbManager;


    public XyLyDangNhap(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }

    //public boolean checkPass(String )

}
