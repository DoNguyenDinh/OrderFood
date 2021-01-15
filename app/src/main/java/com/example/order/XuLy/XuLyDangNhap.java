package com.example.order.XuLy;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.order.Data.DBManager;

import java.util.ArrayList;
import java.util.List;

public class XuLyDangNhap {

    SQLiteDatabase db;
    DBManager dbManager;


    public XuLyDangNhap(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }


    //kiem tra mat khau co dung khong
    public boolean checkPass(String username,String pass) {

        String getPass = "select "+dbManager.PASS_STAFF+" from " + dbManager.TB_STAFF+" where "+dbManager.USER_NAME+" = '"+username+"'";
        Cursor cs = db.rawQuery(getPass, null);
        cs.moveToFirst();

        if(pass.equals(cs.getString(0))){
            return true;
        }
        return false;
    }


    //lay ten nhan vien dang nhap
    public Cursor getNameLogin(int id) {

        String sql = "Select * from " + dbManager.TB_STAFF + " where manv = " + id;
        Cursor getCursor = db.rawQuery(sql, null);
        return getCursor;
    }


    //lay id dang nhap
    public Cursor GetId(String name, String pass) {


        String sql = "Select * from " + dbManager.TB_STAFF + " where " + dbManager.USER_NAME + "= '" +
                name + "' and " + dbManager.PASS_STAFF + " = '" + pass + "'";
        Cursor getIdAccount = db.rawQuery(sql, null);

        return getIdAccount;
    }

    //lay danh sach ten dang nhap
    public List<String> selectListNameUser(String userName) {

        String query_selectall = "Select * from " + dbManager.TB_STAFF + " where "+dbManager.USER_NAME+"='" + userName + "'";
        List<String> listUserName = new ArrayList<>();

        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                listUserName.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        db.close();
        return listUserName;
    }



}
