package com.example.order.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.order.Menu;
import com.example.order.Staff;
import com.example.order.Table;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "Restaurant";


    public String TB_MENU = "thucdon";
    public String PRICE = "gia";
    static String ID_FOOD = "mamonan";
    public String NAME_FOOD = "tenmonan";


    static String TB_TABLEFOOD = "banan";
    static String ID_TABLE = "mabanan";
    public String NAME_TABLE = "tenbanan";


    public String TB_ORDER = "datmon";
    static String ID_ORDER = "madatmon";


    public String TB_STAFF = "nhanvien";
    public String ID_STAFF = "manv";
    public String NAME_STAFF = "tennv";
    public String PASS = "matkhau";
    public String RULE = "quyen";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 3);
    }


    private String createTableFood = "CREATE TABLE " + TB_TABLEFOOD + " (" +
            ID_TABLE + " INTEGER PRIMARY KEY, " +
            NAME_TABLE + " TEXT)";

    private String createMenuFood = "CREATE TABLE " + TB_MENU + " (" +
            ID_FOOD + " INTEGER PRIMARY KEY, " +
            NAME_FOOD + " TEXT," +
            PRICE + " Text)";

    private String createStaff = "CREATE TABLE " + TB_STAFF + " ( " +
            ID_STAFF + " INTEGER PRIMARY KEY, " +
            NAME_STAFF + " TEXT, " +
            PASS + " TEXT, " +
            RULE + " TEXT default 1)";


    @Override
    public void onCreate(SQLiteDatabase db) {

        //khởi tạo bảng
        db.execSQL(createTableFood);
        db.execSQL(createMenuFood);
        db.execSQL(createStaff);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SQLiteDatabase openCon() {
        return this.getWritableDatabase();
    }


    //add table food
    public void addTableFood(Table table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();


        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME_TABLE, table.getNameTable());
        db.insert(TB_TABLEFOOD, null, values);

        //dong ket noi
        db.close();
    }


    //get list table food
    public List<Table> selectListTable() {
        String query_selectall = "Select * from " + TB_TABLEFOOD;
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


    //add food
    public void addFood(Menu table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME_FOOD, table.getNameFood());
        values.put(PRICE, table.getPrice());
        db.insert(TB_MENU, null, values);


        //dong ket noi
        db.close();
    }

    //get list table food
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


    //delete all list table food
    public List<Table> deleteTable() {
        String query_selectall = "DELETE FROM " + TB_TABLEFOOD + " where id=2";
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


    //create new user
    public void addNewUser(Staff staff) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME_STAFF, staff.getUserName());
        values.put(PASS, staff.getPassword());
        db.insert(TB_STAFF, null, values);

        //dong ket noi
        db.close();
    }


    //get list staff
    public List<Staff> selectListStaff() {

        String query_selectall = "Select * from " + TB_STAFF;
        List<Staff> listStaff = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff();

                staff.setId(cursor.getInt(0));
                staff.setUserName(cursor.getString(1));
                staff.setPassword(cursor.getString(2));

                listStaff.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return listStaff;
    }

    //get account
    public List<Staff> getAccount(String name, String pass) {

        String query_selectall = "Select * from " + TB_STAFF + " where tennv = '" + name + "' and matkhau= '" + pass + "'";
        List<Staff> listStaff = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff();

                staff.setId(cursor.getInt(0));
                staff.setUserName(cursor.getString(1));
                staff.setPassword(cursor.getString(2));

                listStaff.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return listStaff;
    }


    //get id login
    public int GetId(String name, String pass) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        String sql = "Select manv from " + TB_STAFF + " where tennv = '" + name + "' and matkhau= '" + pass + "'";
        Cursor getIdAccount = myDB.rawQuery(sql, null);
        return getIdAccount.getColumnIndex("manv");
    }

    //get name login
    public String getNameLogin(int id){
        SQLiteDatabase myDB = this.getWritableDatabase();

        String sql = "Select tennv from " + TB_STAFF + " where manv = " + id  ;
        Cursor getCursor = myDB.rawQuery(sql, null);
        return String.valueOf(getCursor.getColumnIndex("tennv"));
    }
}
