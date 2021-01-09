package com.example.order.XuLy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;
import com.example.order.Table;

import java.util.ArrayList;
import java.util.List;

public class XuLyBanAn {

    SQLiteDatabase db;
    DBManager dbManager;

    public XuLyBanAn(Context context){
        dbManager=new DBManager(context);
        db=dbManager.openCon();
    }


    //them moi ban an
    public long addTableFood(Table table) {

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.NAME_TABLE, table.getNameTable());
        long result = db.insert(dbManager.TB_TABLEFOOD, null, values);

        //dong ket noi
        db.close();
        return result;
    }



    //lay trang thai ban an
    public Cursor getTableStatus(int idbanan) {


        String sql = "Select * from " + dbManager.TB_TABLEFOOD + " where mabanan = " + idbanan;
        Cursor cs = db.rawQuery(sql, null);

        return cs;
    }


    //lay danh sach ban an
    public List<Table> selectListTable() {
        String query_selectall = "Select * from " + dbManager.TB_TABLEFOOD;
        List<Table> listTable = new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Table table = new Table();

                table.setId(cursor.getInt(0));
                table.setNameTable(cursor.getString(1));

                listTable.add(table);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTable;
    }


    //lay danh sach ten ban an
    public List<String> selectlistNameTable(String tableName) {

        String query_selectall = "Select * from " + dbManager.TB_TABLEFOOD+" where tenbanan='"+tableName+"'";
        List<String> listUserName=new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                listUserName.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        db.close();
        return listUserName;
    }


    //cap nhat trang thai ban an
    public void updateTableStatus(int banan, boolean statusTable) {

        String update_db = "";
        boolean changeStatus = statusTable;
        if (changeStatus) {
            update_db = "update " + dbManager.TB_TABLEFOOD + " set " + dbManager.STATUS_TABLE + "= 0 where " + dbManager.ID_TABLE + " = " + banan;
        } else {
            update_db = "update " + dbManager.TB_TABLEFOOD + " set " + dbManager.STATUS_TABLE + "= 1 where " + dbManager.ID_TABLE + " = " + banan;
        }

        db.execSQL(update_db);

    }


}
