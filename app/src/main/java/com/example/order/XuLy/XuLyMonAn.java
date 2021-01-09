package com.example.order.XuLy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.order.Data.DBManager;
import com.example.order.FoodStyle;
import com.example.order.Menu;

import java.util.ArrayList;
import java.util.List;

public class XuLyMonAn {

    SQLiteDatabase db;
    DBManager dbManager;


    public XuLyMonAn(Context context) {
        dbManager = new DBManager(context);
        db = dbManager.openCon();
    }

    //them mon an moi
    public long addFood(Menu table) {
        //mo ket noi database


        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.NAME_FOOD, table.getNameFood());
        values.put(dbManager.PRICE_FOOD, table.getPrice());
        values.put(dbManager.STYLE_FOOD, table.getStyleFood());
        long result = db.insert(dbManager.TB_MENU, null, values);

        //dong ket noi
        db.close();

        return result;
    }


    //lay ma loai mon an theo ten loai
    public int getIDFoodStyle(String namefood) {


        String sql = "Select " + dbManager.ID_FOOD_TYPE + " from " + dbManager.TB_FOOD_TYPE + " where " + dbManager.NAME_FOOD_TYPE + " = '" + namefood + "'";
        Cursor cs = db.rawQuery(sql, null);
        cs.moveToFirst();

        int maloai = Integer.parseInt(cs.getString(0));
        return maloai;

    }


    //Lay ma mon an theo ten mon an
    public Cursor getIDFood(String namefood) {

        String sql = "Select * from " + dbManager.TB_MENU + " where " + dbManager.NAME_FOOD + " = '" + namefood + "'";
        Cursor cs = db.rawQuery(sql, null);

        return cs;

    }


    //lay danh sach mon an

    public List<Menu> selectListMenu() {

        String query_selectall = "Select * from " + dbManager.TB_MENU;
        List<Menu> listMenu = new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Menu menu = new Menu();

                menu.setId(cursor.getInt(0));
                menu.setNameFood(cursor.getString(1));
                menu.setPrice(cursor.getString(2));

                listMenu.add(menu);
            } while (cursor.moveToNext());
        }
        db.close();
        return listMenu;
    }


    //cap nhat so luong mon an
    public Cursor updateQuantityFood(int madatmon, int soluong, int mamonan) {


        String sql = "update " + dbManager.TB_ORDER_DETAIL + " set soluong = " + soluong + " where " + dbManager.ID_ORDER_ORDER + " = " +
                madatmon + " and " + dbManager.ID_FOOD_ORDER + " = " + mamonan;
        Cursor cs = db.rawQuery(sql, null);
        db.execSQL(sql);
        return cs;
    }



    //them loai mon an moi
    public long addFoodStyle(FoodStyle food) {

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(dbManager.NAME_FOOD_TYPE, food.getNameFoodStyle());
        long result = db.insert(dbManager.TB_FOOD_TYPE, null, values);

        //dong ket noi
        db.close();

        return result;
    }



    //lay danh sach loai mon an

    public List<String> selectListFoodStyle() {

        String select = "Select * from " + dbManager.TB_FOOD_TYPE;
        List<String> list = new ArrayList<>();

        Cursor cs = db.rawQuery(select, null);
        if (cs.moveToFirst()) {
            do {

                list.add(cs.getString(1));
            } while (cs.moveToNext());
        }

        return list;
    }

}
