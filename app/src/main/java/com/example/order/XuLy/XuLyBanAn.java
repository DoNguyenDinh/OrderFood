package com.example.order.XuLy;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;

public class XuLyBanAn {

    SQLiteDatabase db;


    public XuLyBanAn(Context context){
        DBManager dbManager=new DBManager(context);
        db=dbManager.openCon();
    }
}
