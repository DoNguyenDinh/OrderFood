package com.example.order.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.order.Menu;
import com.example.order.Table;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "Restaurant";
    static String TABLE_NAME = "tablefood";
    static String ID = "id";
    public static String NAME = "name";
    public String PRICE = "price";
    public String TB_MENU = "menu";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    private String createTableFood = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NAME +" TEXT)";

    private String createMenuFood = "CREATE TABLE " + TB_MENU + " (" +
            ID + " INTEGER PRIMARY KEY, " +
            NAME +" TEXT,"+
            PRICE + " Text)";


    @Override
    public void onCreate(SQLiteDatabase db) {

        //khởi tạo bảng
        db.execSQL(createTableFood);
        db.execSQL(createMenuFood);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openCon() {
        return this.getWritableDatabase();
    }


    //them ban an
    public void addTableFood(Table table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();


        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME, table.getNameTable());
        db.insert(TABLE_NAME, null, values);

        //dong ket noi
        db.close();
    }


    //lay danh sach ban an
    public List<Table> selectListTable() {
        String query_selectall = "Select * from " + TABLE_NAME;
        List<Table> listTable = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
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




    //them mon an
    public void addFood(Menu table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME, table.getNameFood());
        values.put(PRICE, table.getPrice());
        db.insert(TB_MENU, null, values);


        //dong ket noi
        db.close();
    }

    //lay danh sach mon an
    public List<Menu> selectListMenu() {

        String query_selectall = "Select * from " + TB_MENU;
        List<Menu> listMenu = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
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




    //xoa het danh sach ban an
    public List<Table> deleteTable() {
        String query_selectall = "DELETE FROM " + TABLE_NAME +" where id=2";
        List<Table> listTable = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
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

}
