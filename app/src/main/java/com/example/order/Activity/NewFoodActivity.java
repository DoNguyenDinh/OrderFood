package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Data.DBManager;
import com.example.order.Fragment.MenuFragment;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.Table;
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;

public class NewFoodActivity extends AppCompatActivity {

    EditText edtName, edtPrice;
    ImageView imgFood;
    Button btnSave, btnAddImage;
    Spinner spnFoodStyle;
    List<String> listFoodStyle;
    XuLyMonAn xlMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        anhxa();
        setTitle(R.string.new_food);
        setupSpinner();

    }

    private void setupSpinner() {


        xlMonAn = new XuLyMonAn(getApplicationContext());
        listFoodStyle = new ArrayList<>();
        DBManager db = new DBManager(getApplicationContext());
        listFoodStyle = xlMonAn.selectListFoodStyle();


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listFoodStyle);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFoodStyle.setAdapter(arrayAdapter);
        selectFoodStyle();
    }

    private void selectFoodStyle() {
        spnFoodStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "ten loai mon an "+ listFoodStyle.get(position), Toast.LENGTH_SHORT).show();

                nameStyle = listFoodStyle.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "chua chon loai mon an", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void anhxa() {
        edtName = (EditText) findViewById(R.id.edt_nameFood);
        edtPrice = (EditText) findViewById(R.id.edt_priceFood);
        btnSave = (Button) findViewById(R.id.btn_saveFood);
        spnFoodStyle = (Spinner) findViewById(R.id.spn_FoodStyle);
    }


    String nameStyle;
    int ID;

    //them mon an moi vao thucdon
    public void save_Food(View view) {

        XuLyMonAn xlMonAn = new XuLyMonAn(getApplicationContext());

        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
       // Toast.makeText(getApplicationContext(), "loai mon an la: " + nameStyle, Toast.LENGTH_SHORT).show();

        //lay id loai mon an
        ID=xlMonAn.getIDFoodStyle(nameStyle);

       // Toast.makeText(getApplicationContext(), "ID loai mon an la: " + ID, Toast.LENGTH_SHORT).show();


        if (name.matches("") || price.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        } else {

            Menu menu = createMenu();
            long check = xlMonAn.addFood(menu);
            if (check > 0) {
                Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Them that bai?!!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //create new food
    private Menu createMenu() {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        Menu table = new Menu(name, price,ID);
        return table;
    }

    //back to main activity
    public void cancel_food(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btn_addNewFoodStyle(View view) {
        startActivity(new Intent(this, NewFoodStyleActivity.class));
    }
}