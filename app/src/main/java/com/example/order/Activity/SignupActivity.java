package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.Staff;
import com.example.order.XuLy.XuLyDangNhap;

import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends AppCompatActivity {

    EditText edt_UserName, edt_PassWord, edt_RePassword, edt_StaffName;
    Button btnSign;
    List<String> listUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);
        DBManager db = new DBManager(getApplicationContext());

        anhxa();
        setTitle(R.string.register);
    }

    private void anhxa() {
        edt_UserName = (EditText) findViewById(R.id.edt_username_signup);
        edt_PassWord = (EditText) findViewById(R.id.edt_password_signup);
        edt_RePassword = (EditText) findViewById(R.id.edt_repassword_sigup);
        btnSign = (Button) findViewById(R.id.btn_signup_signup);
        edt_StaffName = (EditText) findViewById(R.id.edt_staff_name_signup);
    }

    //save user and back login
    public void signup(View view) {

        String userName = edt_UserName.getText().toString().trim();
        String password = edt_PassWord.getText().toString().trim();
        String RePassword = edt_RePassword.getText().toString().trim();
        String staffName = edt_StaffName.getText().toString().trim();

        //kiem tra gia tri nhap co rong khong
        if (userName.matches("") || password.matches("") || RePassword.matches("") || staffName.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap du lieu", Toast.LENGTH_SHORT).show();
        } else {
            boolean checkUser = checkUserName(userName);
            if (checkUser) {
                Toast.makeText(getApplicationContext(), "userName da ton tai", Toast.LENGTH_SHORT).show();

            } else {
                if (checkPassword(password, RePassword)) {
                    DBManager dbManager = new DBManager(getApplicationContext());
                    if (dbManager == null) {
                        Toast.makeText(getApplicationContext(), "DB NUll", Toast.LENGTH_SHORT).show();
                    } else {
                        Staff staff = createAccount();
                        long check = dbManager.addNewUser(staff);
                        if (check > 0) {

                            Intent i = new Intent(this, LoginActivity.class);
                            Toast.makeText(getApplicationContext(), "Dang ky thanh cong", Toast.LENGTH_SHORT).show();

                            startActivity(i);
                        } else {
                            Toast.makeText(getApplicationContext(), "Dang ky that bai", Toast.LENGTH_SHORT).show();

                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "ReEnter Confirm Password ", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    //so sanh mat khau va xac nhan mat khau
    boolean checkPassword(String password, String repassword) {
        if (repassword.equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    //kiem tra username da ton tai chua
    public boolean checkUserName(String userName) {

        listUser = new ArrayList<>();
        XuLyDangNhap xuLyDangNhap = new XuLyDangNhap(getApplicationContext());
        listUser = xuLyDangNhap.selectListNameUser(userName);

        if (listUser.size() == 0) {
            return false;
        }
        return true;
    }

    //tao nhan vien moi
    private Staff createAccount() {
        String userName = edt_UserName.getText().toString();
        String password = edt_PassWord.getText().toString();
        String nameStaff = edt_StaffName.getText().toString();

        Staff staff = new Staff(userName, password, nameStaff);
        return staff;
    }

}