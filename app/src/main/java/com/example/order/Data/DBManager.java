package com.example.order.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.order.Menu;
import com.example.order.Order;
import com.example.order.OrderDetail;
import com.example.order.ShowOrder;
import com.example.order.Staff;
import com.example.order.Table;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "Restaurant";
    static final int VERSION = 7;

    //loai monan
    public String TB_FOOD_TYPE = "loaimonan";
    public String ID_FOOD_TYPE = "maloai";
    public String NAME_FOOD_TYPE = "tenmaloai";

    private String createTypeFood = "CREATE TABLE " + TB_FOOD_TYPE + " ( " +
            ID_FOOD_TYPE + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            NAME_FOOD_TYPE + " Text )";


    //table chitietdatmon

    public static String TB_ORDER_DETAIL = "chitietdatmon";
    public static String ID_ORDER_ORDER = "madatmonan";
    public static String ID_FOOD_ORDER = "mamonan";
    public static String QUANTITY = "soluong";

    private String createOrderDetail = "CREATE TABLE " + TB_ORDER_DETAIL + " (" +
            ID_ORDER_ORDER + " INTEGER," +
            ID_FOOD_ORDER + " INTEGER," +
            QUANTITY + " INTEGER, " +
            "FOREIGN KEY (" + ID_ORDER_ORDER + ") REFERENCES datmon" + "(" + ID_ORDER + ")," +
            "FOREIGN KEY (" + ID_FOOD_ORDER + ") REFERENCES thucdon" + "(" + ID_FOOD + "))";


    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    //table banan
    static String TB_TABLEFOOD = "banan";
    static String ID_TABLE = "mabanan";
    public String NAME_TABLE = "tenbanan";
    public String STATUS_TABLE = "trangthai";
    private String createTableFood = "CREATE TABLE " + TB_TABLEFOOD + " (" +
            ID_TABLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_TABLE + " TEXT," +
            STATUS_TABLE + " INTEGER default 0)";


    String dropTable = "";

    //table thucdon
    public String TB_MENU = "thucdon";
    public String PRICE_FOOD = "gia";
    static String ID_FOOD = "mamonan";
    public String NAME_FOOD = "tenmonan";
    private String createMenuFood = "CREATE TABLE " + TB_MENU + " (" +
            ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_FOOD + " TEXT," +
            PRICE_FOOD + " Text)";

    //table nhanvien
    public String TB_STAFF = "nhanvien";
    public String ID_STAFF = "manv";
    public String NAME_STAFF = "tennv";
    public String PASS_STAFF = "matkhau";
    public String RULE_STAFF = "quyen";
    private String createStaff = "CREATE TABLE " + TB_STAFF + " ( " +
            ID_STAFF + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_STAFF + " TEXT, " +
            PASS_STAFF + " TEXT, " +
            RULE_STAFF + " TEXT default 1)";


    //table datmon
    public static String TB_ORDER = "datmon";
    public static String ID_ORDER = "madatmon";
    public static String ID_TABLE_ORDER = "mabandat";
    public static String STATUS_ORDER = "trangthai";

    private String createOrder = "CREATE TABLE " + TB_ORDER + " ( " +
            ID_ORDER + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            ID_TABLE_ORDER + " INTEGER, " +
            STATUS_ORDER + " INTEGER default 1)";


    @Override
    public void onCreate(SQLiteDatabase db) {

        //khởi tạo bảng
        db.execSQL(createTableFood);
        db.execSQL(createMenuFood);
        db.execSQL(createStaff);
        db.execSQL(createOrder);
        db.execSQL(createOrderDetail);
        db.execSQL(createTypeFood);

    }


    String DROP_ORDER_DETAIL = "DROP TABLE IF EXISTS " + TB_ORDER_DETAIL;
    String DROP_ORDER = "DROP TABLE IF EXISTS " + TB_ORDER;
    String DROP_STAFF = "DROP TABLE IF EXISTS " + TB_STAFF;
    String DROP_MENU = "DROP TABLE IF EXISTS " + TB_MENU;
    String DROP_TABLE = "DROP TABLE IF EXISTS " + TB_TABLEFOOD;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exist
        db.execSQL(DROP_ORDER_DETAIL);
        db.execSQL(DROP_ORDER);
        db.execSQL(DROP_STAFF);
        db.execSQL(DROP_MENU);
        db.execSQL(DROP_TABLE);


        //create new table
        onCreate(db);

    }

    public SQLiteDatabase openCon() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db;
    }

    public long addOrder(Order table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();


        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(ID_TABLE_ORDER, table.getIdTable());
        long result = db.insert(TB_ORDER, null, values);

        //dong ket noi
        db.close();
        return result;
    }


    //add table food
    public long addTableFood(Table table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();


        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME_TABLE, table.getNameTable());
        long result = db.insert(TB_TABLEFOOD, null, values);

        //dong ket noi
        db.close();
        return result;
    }


    //get list ordered
    public List<Order> selectListOrdered() {
        String query_selectall = "Select * from " + TB_ORDER + " where " + STATUS_ORDER + " = 1";
        List<Order> listTable = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {
                Order table = new Order();

                table.setId(cursor.getInt(0));
                table.setIdTable(Integer.parseInt(cursor.getString(1)));

                listTable.add(table);
            } while (cursor.moveToNext());
        }
        db.close();
        return listTable;
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
    public long addFood(Menu table) {
        //mo ket noi database
        SQLiteDatabase db = this.getWritableDatabase();

        //luu gia tri xuong database
        ContentValues values = new ContentValues();
        values.put(NAME_FOOD, table.getNameFood());
        values.put(PRICE_FOOD, table.getPrice());
        long result = db.insert(TB_MENU, null, values);

        //dong ket noi
        db.close();

        return result;
    }


    //cap nhat trang thai dat mon

    public void updateStatusorder(int iddatmon) {
        SQLiteDatabase db = this.getWritableDatabase();


        String update_db = "update " + TB_ORDER + " set " + STATUS_ORDER + "= 0 where " + ID_ORDER + " = " + iddatmon;
        db.execSQL(update_db);

    }

    //lay trang thai ban an
    public Cursor getTableStatus(int idbanan) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        String sql = "Select * from " + TB_TABLEFOOD + " where mabanan = " + idbanan;
        Cursor cs = myDB.rawQuery(sql, null);


        return cs;
    }


    //cap nhat trang thai ban an
    public void updateTableStatus(int banan, boolean statusTable) {
        SQLiteDatabase db = this.getWritableDatabase();
        String update_db = "";
        boolean changeStatus = statusTable;
        if (changeStatus) {
            update_db = "update " + TB_TABLEFOOD + " set " + STATUS_TABLE + "= 0 where " + ID_TABLE + " = " + banan;
        } else {
            update_db = "update " + TB_TABLEFOOD + " set " + STATUS_TABLE + "= 1 where " + ID_TABLE + " = " + banan;
        }


        db.execSQL(update_db);

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
        values.put(PASS_STAFF, staff.getPassword());
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
    public Cursor GetId(String name, String pass) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        String sql = "Select * from " + TB_STAFF + " where tennv = '" + name + "' and matkhau= '" + pass + "'";
        Cursor getIdAccount = myDB.rawQuery(sql, null);


        return getIdAccount;
    }

    //lay ma dat mon an
    public Cursor getIDOrder() {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "select * from datmon order by madatmon desc limit 1";
        Cursor cs = db.rawQuery(sql, null);
        return cs;
    }

    //insert data table chitietdatmon
    public long insertDataDetail(OrderDetail detail) {
        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ID_ORDER_ORDER, detail.getIdOrder());
        values.put(ID_FOOD_ORDER, detail.getIdFood());
        values.put(QUANTITY, detail.getQuantityFood());
        long result = db.insert(TB_ORDER_DETAIL, null, values);

        db.close();

        return result;
    }


    //get name login
    public Cursor getNameLogin(int id) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        String sql = "Select * from " + TB_STAFF + " where manv = " + id;
        Cursor getCursor = myDB.rawQuery(sql, null);
        return getCursor;
    }


    //lay thong tin dat mon de thanh toan
    public List<ShowOrder> getInfo(int iddatmon) {


        String query_selectall = "select *" +
                "FROM thucdon " +
                "INNER JOIN chitietdatmon on chitietdatmon.madatmonan=" + iddatmon + " and chitietdatmon.mamonan=thucdon.mamonan";

        List<ShowOrder> listInfo = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {

                // listInfo.add(cursor.getString(1));
                ShowOrder staff = new ShowOrder();
//
                staff.setTenmonan(cursor.getString(1));
                staff.setSoluong(cursor.getInt(5));


                int dongia = cursor.getShort(2);
                int soluong = cursor.getInt(5);
                int thanhtien = dongia * soluong;
//
                staff.setThanhtien(thanhtien);
                listInfo.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return listInfo;
    }


    //lay danh sach mon an theo ma dat mon
    public List<ShowOrder> getDetailOrder(int iddatmon) {


        String query_selectall = "select *" +
                "FROM thucdon " +
                "INNER JOIN chitietdatmon on chitietdatmon.madatmonan=" + iddatmon + " and chitietdatmon.mamonan=thucdon.mamonan";

        List<ShowOrder> listInfo = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query_selectall, null);

        if (cursor.moveToFirst()) {
            do {


                ShowOrder staff = new ShowOrder();

                staff.setTenmonan(cursor.getString(1));
                staff.setSoluong(cursor.getInt(5));
                listInfo.add(staff);
            } while (cursor.moveToNext());
        }
        db.close();
        return listInfo;
    }

    //Lay ma mon an theo ten mon an

    public Cursor getIDFood(String namefood) {


        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "Select * from " + TB_MENU + " where " + NAME_FOOD + " = '" + namefood + "'";
        Cursor cs = db.rawQuery(sql, null);

        return cs;

    }


    //cap nhat so luong mon an
    public Cursor updateQuantityFood(int madatmon, int soluong, int mamonan) {
        SQLiteDatabase db = this.getWritableDatabase();

        String sql = "update " + TB_ORDER_DETAIL + " set soluong = " + soluong + " where " + ID_ORDER_ORDER + " = " +
                madatmon + " and " + ID_FOOD_ORDER + " = " + mamonan ;
                Cursor cs=db.rawQuery(sql,null);
        db.execSQL(sql);
        return cs;
    }


    //xoa mon an theo ma mon an va ma dat mon
    public void deleteFoodWithValues(int madatmon,int mamonan) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TB_ORDER_DETAIL, " madatmonan =? and mamonan=?",new String[]{madatmon+"",mamonan+""});


    }


}
