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
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;

public class Order_Activity extends AppCompatActivity {

    private TextView tableChoose;
    public static String txt;
    List<Menu> menuList;
    XuLyMonAn xl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        RecyclerView rv = findViewById(R.id.rv_table);
        RecyclerView rv_menu = findViewById(R.id.rv_dsmonan);

        tableChoose = (TextView) findViewById(R.id.txt_);

        xl = new XuLyMonAn(getApplicationContext());
        menuList = xl.selectListMenu();

        MenuAdapter menuAdapter = new MenuAdapter(this, menuList);
        rv_menu.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_menu.setAdapter(menuAdapter);

        //thay doi tieu de
        setTitle(R.string.Order);
        getData();
        initData();
    }



    public void back_activitymain(View view) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }


    //load danh sach monan
    private void initData() {
        menuList = new ArrayList<>();

        xl = new XuLyMonAn(getApplicationContext());
        //lay danh sach mon an tu database
        menuList = xl.selectListMenu();

    }

    static int maban = 0;

    //lay id ban an
    void getData() {
        Bundle bundle = getIntent().getExtras();
        maban = bundle.getInt("mabanan");
        Toast.makeText(this, "ma ban" + maban, Toast.LENGTH_SHORT).show();
        tableChoose.setText("Please choose food and drink " + "( " + maban + ")");
    }


    //them moi dat mon
    public void newOrder(View view) {

        Order or = new Order(maban);
        XuLyDatMon xl=new XuLyDatMon(getApplicationContext());
        long a = xl.addOrder(or);
        if (a > 0) {
            Toast.makeText(this, "them thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Them that bai", Toast.LENGTH_SHORT).show();
        }
    }
}


