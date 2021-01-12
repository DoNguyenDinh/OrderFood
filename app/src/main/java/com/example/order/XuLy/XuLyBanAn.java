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

    public XuLyBanAn(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }


    //them moi ban an
    public long addTableFood(Table table) {

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.NAME_TABLE, table.getNameTable());
        long result = db.insert(dbManager.TB_TABLEFOOD, null, values);

        //dong ket noi
        // db.close();
        return result;
    }


    //lay trang thai ban an
    public Cursor getTableStatus(int idbanan) {


        String sql = "Select * from " + dbManager.TB_TABLEFOOD + " where mabanan = " + idbanan;
        Cursor cs = db.rawQuery(sql, null);

        return cs;
    }


    //lay ten ban an theo ma ban an

    public String nameTable(int id) {

        String sql = "select "+dbManager.NAME_TABLE+" from "+dbManager.TB_TABLEFOOD +" where "+dbManager.ID_TABLE+" = "+id;

        Cursor cs=db.rawQuery(sql,null);
        cs.moveToFirst();
        String name = cs.getString(0);
        return name;
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
        // db.close();
        return listTable;
    }


    //lay danh sach ten ban an
    public List<String> selectlistNameTable(String tableName) {

        String query_selectall = "Select * from " + dbManager.TB_TABLEFOOD + " where tenbanan='" + tableName + "'";
        List<String> listUserName = new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                listUserName.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // db.close();
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

    //xoa ban an
    public void deleteTabele(String nameTable) {
        db.delete(dbManager.TB_TABLEFOOD, " tenbanan =?", new String[]{nameTable});

    }

    //lay id ban an
    //lay trang thai ban an
    public Cursor getIDTable(String nameTable) {

        String sql = "Select " + dbManager.ID_TABLE + " from " + dbManager.TB_TABLEFOOD + " where tenbanan = '" + nameTable + "'";
        Cursor cs = db.rawQuery(sql, null);

        return cs;
    }

    public void updateTableName(String nameTable, int id) {


        String update_db = "update " + dbManager.TB_TABLEFOOD + " set " + dbManager.NAME_TABLE + "= '" + nameTable + "' where " + dbManager.ID_TABLE + " = " + id;
        db.execSQL(update_db);
//        db.close();

    }

}
