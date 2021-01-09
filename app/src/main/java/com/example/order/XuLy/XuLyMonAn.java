package com.example.order.XuLy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;
import com.example.order.Menu;

public class XuLyMonAn {

    SQLiteDatabase db;
    DBManager dbManager;


    public XuLyMonAn(Context context){
        dbManager=new DBManager(context);
        db=dbManager.openCon();
    }

    //them mon an moi
    public long addFood(Menu table) {
        //mo ket noi database


        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.NAME_FOOD, table.getNameFood());
        values.put(dbManager.PRICE_FOOD, table.getPrice());
        long result = db.insert(dbManager.TB_MENU, null, values);

        //dong ket noi
        db.close();

        return result;
    }

    //Lay ma mon an theo ten mon an
    public Cursor getIDFood(String namefood) {


        String sql = "Select * from " + dbManager.TB_MENU + " where " + dbManager.NAME_FOOD + " = '" + namefood + "'";
        Cursor cs = db.rawQuery(sql, null);

        return cs;

    }


    //lay danh sach mon an




    //cap nhat so luong mon an
    public Cursor updateQuantityFood(int madatmon, int soluong, int mamonan) {


        String sql = "update " + dbManager.TB_ORDER_DETAIL + " set soluong = " + soluong + " where " + dbManager.ID_ORDER_ORDER + " = " +
                madatmon + " and " + dbManager.ID_FOOD_ORDER + " = " + mamonan;
        Cursor cs = db.rawQuery(sql, null);
        db.execSQL(sql);
        return cs;
    }
}
