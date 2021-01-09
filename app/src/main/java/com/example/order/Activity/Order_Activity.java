package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Adapter.TableAdapter;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.Order;
import com.example.order.R;
import com.example.order.XuLy.XuLyDatMon;

import java.util.ArrayList;
import java.util.List;

public class Order_Activity extends AppCompatActivity {

    private TextView tableChoose;
    public static String txt;
    List<Menu> menuList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        RecyclerView rv = findViewById(R.id.rv_table);
        RecyclerView rv_menu = findViewById(R.id.rv_dsmonan);

        tableChoose = (TextView) findViewById(R.id.txt_);

        DBManager db = new DBManager(getApplicationContext());
        menuList = db.selectListMenu();

        MenuAdapter menuAdapter = new MenuAdapter(this, menuList);
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

        DBManager db = new DBManager(getApplicationContext());

        startActivity(i);
    }


    //load danh sach monan
    private void initData() {
        menuList = new ArrayList<>();
        DBManager dbManager = new DBManager(this);

        //lay danh sach mon an tu database
        menuList = dbManager.selectListMenu();

    }

    int vitri = 0;
    static int maban = 0;

    //lay id ban an
    void getData() {

        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        maban = bundle.getInt("mabanan");
        Toast.makeText(this, "ma ban"+maban, Toast.LENGTH_SHORT).show();

//        txt = TableAdapter.maban;
        tableChoose.setText("Please choose food and drink " + "( " + maban + ")");
    }


    //create new order
    private Order createOrder() {
        Order order = new Order(maban);
        return order;
    }


    //them moi dat mon
    public void newOrder(View view) {

        //Order or=createOrder();
        Order or = new Order(maban);
        DBManager db = new DBManager(getApplicationContext());
        long a=db.addOrder(or);
        if(a>0){
            Toast.makeText(this, "them thanh cong", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();

        }
    }

}


