package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.Table;

public class NewFoodActivity extends AppCompatActivity {

    EditText edtName, edtPrice;
    ImageView imgFood;
    Button btnSave, btnAddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        anhxa();
    }

    private void anhxa() {
        edtName = (EditText) findViewById(R.id.edt_nameFood);
        edtPrice = (EditText) findViewById(R.id.edt_priceFood);
        btnSave = (Button) findViewById(R.id.btn_saveFood);
    }


    public void save_Food(View view) {

        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        if (name.matches("")||price.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        }
        else {
            DBManager dbManager = new DBManager(this);

            Menu menu = createMenu();
            dbManager.addFood(menu);

            Intent i = new Intent(this, MainActivity.class);

            Log.d("click", "info was save");

            Toast.makeText(this,"Them thanh cong",Toast.LENGTH_SHORT).show();
            startActivity(i);

        }
    }
    private Menu createMenu() {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        Menu table = new Menu(name,price);
        return table;
    }
}