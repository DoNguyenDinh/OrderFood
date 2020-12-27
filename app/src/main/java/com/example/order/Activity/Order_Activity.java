package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Adapter.TableAdapter;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.R;

import java.util.ArrayList;
import java.util.List;

public class Order_Activity extends AppCompatActivity {

    private TextView tableChoose;
    String txt;
    List<Menu> menuList;
    MenuAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        RecyclerView rv = findViewById(R.id.rv_table);
        RecyclerView rv_menu = findViewById(R.id.rv_dsmonan);

        tableChoose = (TextView) findViewById(R.id.txt);

        menuAdapter = new MenuAdapter(this, menuList);
        rv_menu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_menu.setAdapter(menuAdapter);

        //thay doi tieu de
        setTitle(R.string.Order);

        getData();
        initData();
    }


    //luu
    public void back_activitymain(View view) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //load danh sach monan
    private void initData() {
        menuList = new ArrayList<>();
        DBManager dbManager = new DBManager(this);

        //lay danh sach ban tu database
        menuList = dbManager.selectListMenu();

    }

    //lay id ban an
    void getData() {
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        txt = bundle.getString("keyname");

        tableChoose.setText("Please choose food and drink " + "( Table " + txt + " )");
    }


}

