package com.example.order.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.Staff;
import com.example.order.XuLy.XuLyDangNhap;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWord;
    Button btnLogin, btnSignup;
    List<Staff> staffList;
    public static int ma_nhan_vien = 0;
    public static String ten_nv="";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        setTitle(R.string.login);
        anhxa();
    }


    //lay thong tin dang nhap cua nhan vien
    public List<Staff> getListStaff(String username, String pass) {

        staffList = new ArrayList<>();
        DBManager db = new DBManager(getApplicationContext());
        staffList = db.getAccount(username, pass);

        return staffList;
    }


    public void login(View view) {

        String name = edtUserName.getText().toString();
        String pass = edtPassWord.getText().toString();
        XuLyDangNhap xuLyDangNhap = new XuLyDangNhap(getApplicationContext());

        if (name.matches("") || pass.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        } else {

            boolean checkUser=checkUserName(name);
            if (checkUser) {

                if (xuLyDangNhap.checkPass(name,pass)){
                    Intent i = new Intent(this, MainActivity.class);

                    //lay thong tin dang nhap cua nhan vien
                    Cursor cs = xuLyDangNhap.GetId(name, pass);
                    cs.moveToFirst();
                    ten_nv = cs.getString(1);
                    ma_nhan_vien = cs.getInt(0);

                    Toast.makeText(getApplicationContext(), "Chao mung ban : " + ten_nv, Toast.LENGTH_SHORT).show();
                    startActivity(i);

                }else {
                    Toast.makeText(getApplicationContext(), "Sai mat khau !!!!", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(getApplicationContext(), "tai khoan khong ton tai", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void anhxa() {
        edtUserName = (EditText) findViewById(R.id.edt_username_login);
        edtPassWord = (EditText) findViewById(R.id.edt_password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = (Button) findViewById(R.id.btn_signup_login);
    }

    List<String>listUser;
    //kiem tra username da ton tai chua
    public boolean checkUserName(String userName){

        listUser=new ArrayList<>();


        XuLyDangNhap xuLyDangNhap=new XuLyDangNhap(getApplicationContext());

        listUser= xuLyDangNhap.selectListNameUser(userName);
        if (listUser.size()==0){
            return false;
        }
        return true;
    }

    // mo trang dang ky
    public void create_New_Account(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
