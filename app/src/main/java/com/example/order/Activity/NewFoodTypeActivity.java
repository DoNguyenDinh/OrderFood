package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order.TypeFood;
import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;

public class NewFoodTypeActivity extends AppCompatActivity {

    Button btnCanel, btnAddNewStyle;
    EditText edt_nameStyleFood;
    XuLyMonAn xlMonAn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_foodstyle);
        anhxa();
    }

    private void anhxa() {
        edt_nameStyleFood = (EditText) findViewById(R.id.edt_nameStyleFood);
        btnAddNewStyle = (Button) findViewById(R.id.btn_newFoodStyle);
        btnCanel = (Button) findViewById(R.id.btn_cancel_stylefood);
    }

    //create new food
    private TypeFood createNew(String name) {
        TypeFood table = new TypeFood(name);
        return table;
    }

    public void cancel_stylefood(View view) {
        startActivity(new Intent(this, NewFoodActivity.class));
    }

    boolean checkNameStyle(String name) {

        List<String> list = new ArrayList<>();
        xlMonAn = new XuLyMonAn(this);
        //lay danh sach ten loai mon an
        list = xlMonAn.selectListNameStyle();

        for(int i=0;i<list.size();i++){
            if (list.get(i).matches(name)) {
                return true;
            }
        }
        return false;
    }

    public void newfoodstyle(View view) {

        String name = edt_nameStyleFood.getText() + "";
        xlMonAn = new XuLyMonAn(this);
        if (name.matches("") || name == null) {
            Toast.makeText(this, "chua nhap ten loai mon an", Toast.LENGTH_SHORT).show();

        } else {

            if (checkNameStyle(name)) {
                Toast.makeText(this, "loai mon an nay da ton tai", Toast.LENGTH_SHORT).show();
            } else {
                TypeFood food = createNew(name);
                long result = xlMonAn.addFoodStyle(food);

                if (result > 0) {
                    Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
                }
            }

        }


    }
}
