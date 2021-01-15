package com.example.order.XuLy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;
import com.example.order.Order;
import com.example.order.OrderDetail;
import com.example.order.ShowOrder;

import java.util.ArrayList;
import java.util.List;

public class XuLyDatMon {
    SQLiteDatabase db;
    DBManager dbManager;


    public XuLyDatMon(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }


    //insert data table chitietdatmon
    public long insertDataDetail(OrderDetail detail) {

        ContentValues values = new ContentValues();
        values.put(dbManager.ID_ORDER_ORDER, detail.getIdOrder());
        values.put(dbManager.ID_FOOD_ORDER, detail.getIdFood());
        values.put(dbManager.QUANTITY, detail.getQuantityFood());
        long result = db.insert(dbManager.TB_ORDER_DETAIL, null, values);

        return result;
    }


    //kiem tra mamon an da ton tai trong orderdetail chua

    public List<String> checkIDFoodExists(int idorder, int idfood) {

        List<String> list = new ArrayList<>();

        String sql = "select * from " + dbManager.TB_ORDER_DETAIL + " where madatmonan=" + idorder + " and mamonan =" + idfood;

        Cursor ds = db.rawQuery(sql, null);
        ds.moveToFirst();
        list.add(ds.getString(0));
        return list;
    }

    //lay danh sach mon an da dat
    public List<String> selectListFoodOrdered(int idorder, int idfood) {

        String select = "Select * from " + dbManager.TB_ORDER_DETAIL + " where " + dbManager.ID_ORDER_ORDER + " = " + idorder +
                " and " + dbManager.ID_FOOD_ORDER + " = " + idfood;
        List<String> list = new ArrayList<>();

        Cursor cs = db.rawQuery(select, null);
        if (cs.moveToFirst()) {
            do {

                list.add(cs.getString(1));
            } while (cs.moveToNext());
        }

        return list;
    }


    //lay thong tin dat mon de thanh toan
    public List<ShowOrder> getInfo(int iddatmon) {

        String query_selectall = "select *" +
                "FROM thucdon " +
                "INNER JOIN chitietdatmon on chitietdatmon.madatmonan=" + iddatmon + " and chitietdatmon.mamonan=thucdon.mamonan";

        List<ShowOrder> listInfo = new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                ShowOrder staff = new ShowOrder();

                staff.setTenmonan(cursor.getString(1));
                staff.setSoluong(cursor.getInt(7));

                int dongia = cursor.getShort(2);
                int soluong = cursor.getInt(7);
                int thanhtien = dongia * soluong;

                staff.setThanhtien(thanhtien);
                listInfo.add(staff);
            } while (cursor.moveToNext());
        }

        return listInfo;
    }

    //xoa mon an da dat
    public void deleteFoodOrder(int madatmon, int mamonan) {

        db.delete(dbManager.TB_ORDER_DETAIL, " madatmonan =? and mamonan=?", new String[]{madatmon + "", mamonan + ""});
    }


    //lay danh sach mon an theo ma dat mon
    public List<ShowOrder> getDetailOrder(int iddatmon) {

        String query_selectall = "select *" +
                "FROM thucdon " +
                "INNER JOIN chitietdatmon on chitietdatmon.madatmonan=" + iddatmon + " and chitietdatmon.mamonan=thucdon.mamonan";

        List<ShowOrder> listInfo = new ArrayList<>();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {

                int sl = cursor.getInt(7);
                String name = cursor.getString(1);
                ShowOrder staff = new ShowOrder(sl, name, 0);

                listInfo.add(staff);
            } while (cursor.moveToNext());
        }

        return listInfo;
    }


    //lay ma dat mon an
    public Cursor getIDOrder() {

        String sql = "select "+dbManager.ID_ORDER+" from " + dbManager.TB_ORDER + " where " + dbManager.STATUS_ORDER +
                " = 1 " + " order by madatmon desc limit 1";
        Cursor cs = db.rawQuery(sql, null);

        return cs;
    }



    //them dat mon moi
    public long addOrder(Order table) {

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.ID_TABLE_ORDER, table.getIdTable());
        long result = db.insert(dbManager.TB_ORDER, null, values);

        return result;
    }


    //lay danh sach da dat mon
    public List<Order> selectListOrdered() {
        String query_selectall = "Select * from " + dbManager.TB_ORDER + " where " + dbManager.STATUS_ORDER + " = 1";
        List<Order> listTable = new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Order table = new Order();

                table.setId(cursor.getInt(0));
                table.setIdTable(Integer.parseInt(cursor.getString(1)));

                listTable.add(table);
            } while (cursor.moveToNext());
        }

        return listTable;
    }

    //cap nhat trang thai dat mon
    public void updateStatusorder(int iddatmon) {

        String update_db = "update " + dbManager.TB_ORDER + " set " + dbManager.STATUS_ORDER + "= 0 where " + dbManager.ID_ORDER + " = " + iddatmon;
        db.execSQL(update_db);

    }

}
