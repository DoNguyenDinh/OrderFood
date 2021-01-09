package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class NewFoodActivity extends AppCompatActivity {

    EditText edtName, edtPrice;
    ImageView imgFood;
    Button btnSave, btnAddImage;
    Spinner spnFoodStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        setTitle(R.string.new_food);
        anhxa();
    }

    private void anhxa() {
        edtName = (EditText) findViewById(R.id.edt_nameFood);
        edtPrice = (EditText) findViewById(R.id.edt_priceFood);
        btnSave = (Button) findViewById(R.id.btn_saveFood);
        spnFoodStyle=(Spinner)findViewById(R.id.spn_FoodStyle);
    }

    //them mon an moi vao thucdon
    public void save_Food(View view) {

        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        if (name.matches("") || price.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        } else {
            XuLyMonAn xlMonAn=new XuLyMonAn(getApplicationContext());

            Menu menu = createMenu();
          long check= xlMonAn.addFood(menu);
          if(check>0){
              Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();

          }
          else {
              Toast.makeText(this, "Them that bai?!!", Toast.LENGTH_SHORT).show();

          }
        }
    }

//create new food
    private Menu createMenu() {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();
        Menu table = new Menu(name, price);
        return table;
    }

    //back to main activity
    public void cancel_food(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }
}