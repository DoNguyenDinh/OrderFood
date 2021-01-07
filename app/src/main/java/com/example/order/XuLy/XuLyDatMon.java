package com.example.order.XuLy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;
import com.example.order.Order;
import com.example.order.OrderDetail;

public class XuLyDatMon {
    SQLiteDatabase db;
    DBManager dbManager;


    public Cursor getIDOrder() {
        dbManager.openCon();

        //String sql="select * from "+dbManager.TB_ORDER+"order by "+"";
        String sql = "select * from datmon order by madatmon desc limit 1";
        Cursor cs = db.rawQuery(sql, null);
        return cs;
    }


//    public void addOrder(Order order) {
//        //mo ket noi database
//        //dbManager.openCon();
//
//        //luu gia tri xuong database
//        ContentValues values = new ContentValues();
//        values.put(dbManager.ID_TABLE_ORDER, order.getIdTable());
//
//        db.insert(dbManager.TB_ORDER, null, values);
//
//        //dong ket noi
//        db.close();
//    }

    //insert data table chitietdatmon
    public long insertDataDetail(OrderDetail detail) {
//        String sql_insertData = "insert into " + dbManager.TB_ORDER_DETAIL + "(" + dbManager.ID_ORDER_ORDER + "," + dbManager.ID_FOOD_ORDER + "," + dbManager.QUANTITY + ")"+
//                "values ("+idOrder+","+idFood+","+quantityFood+")";

        dbManager.openCon();

        ContentValues values = new ContentValues();
        values.put(dbManager.ID_ORDER_ORDER, detail.getIdOrder());
        values.put(dbManager.ID_FOOD_ORDER, detail.getIdFood());
        values.put(dbManager.QUANTITY, detail.getQuantityFood());
        long result = db.insert(dbManager.TB_ORDER_DETAIL, null, values);

        db.close();

        return result;
    }
}
