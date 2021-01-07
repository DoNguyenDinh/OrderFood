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


public class SignupActivity extends AppCompatActivity {

    EditText edt_UserName, edt_PassWord, edt_RePassword;
    Button btnSign;

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
    }


    //save user and back login
    public void signup(View view) {

        String s = edt_UserName.getText().toString();
        String s1 = edt_PassWord.getText().toString();
        String s2 = edt_RePassword.getText().toString();

        //kiem tra gia tri nhap co rong khong
        if (s2.matches("")|s1.matches("")||s.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap du lieu", Toast.LENGTH_SHORT).show();
        } else {

            if (checkPassword(s2, s1)) {
                DBManager dbManager = new DBManager(getApplicationContext());
                if(dbManager==null){
                    Toast.makeText(getApplicationContext(), "DB NUll", Toast.LENGTH_SHORT).show();
                }else {
                    Staff staff = createAccount();
                    dbManager.addNewUser(staff);

                    Intent i = new Intent(this, LoginActivity.class);
                    startActivity(i);
                }
            } else {
                Toast.makeText(getApplicationContext(), "ReEnter Confirm Password ", Toast.LENGTH_SHORT).show();

            }
        }
    }


    //compare password and confirm password
    boolean checkPassword(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        } else {
            return false;
        }
    }

    //tao nhan vien moi
    private Staff createAccount() {
        String nameStaff = edt_UserName.getText().toString();
        String password = edt_PassWord.getText().toString();
        String rePassword = edt_RePassword.getText().toString();
        Staff staff = new Staff(nameStaff, password);
        return staff;
    }
}