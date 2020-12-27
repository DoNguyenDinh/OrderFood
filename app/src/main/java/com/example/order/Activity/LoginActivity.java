package com.example.order.Activity;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText edtUserName, edtPassWord;
    Button btnLogin, btnSignup;
    List<Staff> staffList;
    int ma_nhan_vien=0;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);


        setTitle("LOGIN");
        anhxa();
    }

    public List getListStaff(String name,String pass) {

        staffList = new ArrayList<>();
        DBManager db = new DBManager(getApplicationContext());
        staffList = db.getAccount(name,pass);
        ma_nhan_vien=db.GetId(name,pass);


        return staffList;
    }



    public void login(View view) {

        String name = edtUserName.getText().toString();
        String pass = edtPassWord.getText().toString();



        if (name.matches("") || pass.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        } else {
            if (getListStaff(name,pass).size() == 0) {
                Toast.makeText(getApplicationContext(), "tai khoan khong ton tai", Toast.LENGTH_SHORT).show();
            } else {
                Intent i = new Intent(this, MainActivity.class);

                Bundle bundle=new Bundle();
                bundle.putInt("idLogin",ma_nhan_vien);
                startActivity(i);
            }
        }
    }

    public void anhxa() {
        edtUserName = (EditText) findViewById(R.id.edt_username_login);
        edtPassWord = (EditText) findViewById(R.id.edt_password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnSignup = (Button) findViewById(R.id.btn_signup_login);
    }

    public void create_New_Account(View view) {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
    }
}
