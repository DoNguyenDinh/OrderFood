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
        values.put(dbManager.IMAGE_FOOD, table.getImg());
        long result = db.insert(dbManager.TB_MENU, null, values);

        //dong ket noi
        //db.close();

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
                menu.setImg(cursor.getBlob(3));
                listMenu.add(menu);
            } while (cursor.moveToNext());
        }
        //db.close();
        return listMenu;
    }


    //cap nhat so luong mon an
    public Cursor updateQuantityFood(int madatmon, int soluong, int mamonan) {


        String sql = "update " + dbManager.TB_ORDER_DETAIL + " set soluong = " + soluong + " where " + dbManager.ID_ORDER_ORDER + " = '" +
                madatmon + "' and " + dbManager.ID_FOOD_ORDER + " = '" + mamonan + "'";
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
        //db.close();

        return result;
    }


    //lay danh sach ten loai mon an
    public List<String> selectListNameStyle() {

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

    //lay danh sach loai mon an
    public List<FoodStyle> selectListFoodStyle() {

        String select = "Select * from " + dbManager.TB_FOOD_TYPE;
        List<FoodStyle> list = new ArrayList<>();

        Cursor cs = db.rawQuery(select, null);
        if (cs.moveToFirst()) {
            do {

                FoodStyle food = new FoodStyle();
                food.setNameFoodStyle(cs.getString(1));
                food.setId(cs.getInt(0));

                list.add(food);
            } while (cs.moveToNext());
        }

        return list;
    }


    //lay danh sach mon an theo loai mon an

    public List<Menu> selectMenu(String name) {


        String query_selectall = "select *" +
                "FROM " + dbManager.TB_MENU +
                " INNER JOIN " + dbManager.TB_FOOD_TYPE + " on " + dbManager.NAME_FOOD_TYPE + " = '" + name + "' and " + dbManager.ID_FOOD_TYPE + " = " + dbManager.STYLE_FOOD;

        List<Menu> listMenu = new ArrayList<>();


        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Menu menu = new Menu();

                menu.setId(cursor.getInt(0));
                menu.setNameFood(cursor.getString(1));
                menu.setPrice(cursor.getString(2));
                menu.setImg(cursor.getBlob(3));
                listMenu.add(menu);
            } while (cursor.moveToNext());
        }
        //db.close();
        return listMenu;
    }


    //xoa loai mon an theo loai

    public void deleteTypeFood(String nameType) {

        db.delete(dbManager.TB_FOOD_TYPE, dbManager.NAME_FOOD_TYPE + " =? ", new String[]{nameType});
    }

    //kiem tra xoa thanh cong
    public List<String> checkDel(String nameType) {


        String query_selectall = "select * from " + dbManager.TB_FOOD_TYPE + " where " + dbManager.NAME_FOOD_TYPE + " = '" + nameType + "'";
        Cursor cursor = db.rawQuery(query_selectall, null);

        List<String> listMenu = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listMenu.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return listMenu;
    }


    //cap nhat ten loai
    public void updateNameStyle(String name, int id) {
        String sql = "update " + dbManager.TB_FOOD_TYPE + " set " + dbManager.NAME_FOOD_TYPE + " = '" + name + "' where " + dbManager.ID_FOOD_TYPE + " = " + id;
        Cursor cs = db.rawQuery(sql, null);
        db.execSQL(sql);
    }

    //lay hinh anh mon an

    public Cursor selectImage(int id) {

        String query_selectall = "Select * from " + dbManager.TB_MENU + " where " + dbManager.ID_FOOD + " = " + id;
        Cursor cursor = db.rawQuery(query_selectall, null);


        return cursor;
    }


    public void update(byte[] bytes,String name,String price, int id) {
        ContentValues cv = new ContentValues();
        cv.put(dbManager.IMAGE_FOOD, bytes);
        cv.put(dbManager.NAME_FOOD, name);
        cv.put(dbManager.PRICE_FOOD, price);
        db.update("thucdon", cv, "mamonan=" + id, null);
    }

    //xoa mon an
    public void deleteFood(String idfood) {
        db.delete(dbManager.TB_MENU, dbManager.ID_FOOD + " =? ", new String[]{idfood});
    }

    public List<String> checkDelFood(String id) {


        String query_selectall = "select * from " + dbManager.TB_MENU + " where " + dbManager.ID_FOOD + " = " + id ;
        Cursor cursor = db.rawQuery(query_selectall, null);

        List<String> listMenu = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                listMenu.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        return listMenu;
    }
}
