package com.example.order.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import androidx.annotation.Nullable;

import com.example.order.Order;
import com.example.order.Staff;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {


    static final String DATABASE_NAME = "Restaurant";
    static final int VERSION = 12;

    //loai mon an
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
    public String TB_TABLEFOOD = "banan";
    public String ID_TABLE = "mabanan";
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
    public static String ID_FOOD = "mamonan";
    public String NAME_FOOD = "tenmonan";
    public String STYLE_FOOD = "loaimonan";
    public String IMAGE_FOOD = "hinhanh";
    private String createMenuFood = "CREATE TABLE " + TB_MENU + " (" +
            ID_FOOD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME_FOOD + " TEXT," +
            PRICE_FOOD + " Text," +
            IMAGE_FOOD + " BLOB," +
            STYLE_FOOD + " INTEGER," +
            " FOREIGN KEY (" + STYLE_FOOD + ") REFERENCES " + STYLE_FOOD + "(" + ID_FOOD_TYPE + "))";

    //table nhanvien
    public String TB_STAFF = "nhanvien";
    public String ID_STAFF = "manv";
    public String USER_NAME = "tendangnhap";
    public String STAFF_NAME = "tennv";
    public String PASS_STAFF = "matkhau";
    public String RULE_STAFF = "quyen";
    private String createStaff = "CREATE TABLE " + TB_STAFF + " ( " +
            ID_STAFF + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            STAFF_NAME + " TEXT, " +
            USER_NAME + " TEXT, " +
            PASS_STAFF + " TEXT, " +
            RULE_STAFF + " TEXT default 1)";


    //table datmon
    public String TB_ORDER = "datmon";
    public static String ID_ORDER = "madatmon";
    public String ID_TABLE_ORDER = "mabandat";
    public String STATUS_ORDER = "trangthai";

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
    String DROP_STYLE_FOOD = "DROP TABLE IF EXISTS " + TB_FOOD_TYPE;

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if exist
        db.execSQL(DROP_ORDER_DETAIL);
        db.execSQL(DROP_ORDER);
        db.execSQL(DROP_STAFF);
        db.execSQL(DROP_MENU);
        db.execSQL(DROP_TABLE);
        db.execSQL(DROP_STYLE_FOOD);


        //create new table
        onCreate(db);

    }

    public SQLiteDatabase openCon() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db;
    }


}
