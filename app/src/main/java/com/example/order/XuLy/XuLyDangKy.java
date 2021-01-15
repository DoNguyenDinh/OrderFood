package com.example.order.XuLy;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;
import com.example.order.Staff;

public class XuLyDangKy {

    SQLiteDatabase db;
    DBManager dbManager;

    public XuLyDangKy(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }


    //tao nguoi dung moi
    public long addNewUser(Staff staff) {

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.USER_NAME, staff.getUserName());
        values.put(dbManager.PASS_STAFF, staff.getPassword());
        values.put(dbManager.STAFF_NAME, staff.getNameStaff());
        long ck = db.insert(dbManager.TB_STAFF, null, values);

        return ck;
    }
}
