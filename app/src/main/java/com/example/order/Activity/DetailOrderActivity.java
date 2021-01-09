package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.OrderAdapter;
import com.example.order.Adapter.OrderDetailAdapter;
import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.ShowOrder;

import java.util.ArrayList;
import java.util.List;

public class DetailOrderActivity extends AppCompatActivity {

    RecyclerView rv_OrderDetail;
    OrderDetailAdapter orderAdapter;
    List<ShowOrder> orderList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initData();
        rv_OrderDetail = (RecyclerView) findViewById(R.id.rv_chitietdatmon);
        orderAdapter = new OrderDetailAdapter(getApplicationContext(), orderList);
        rv_OrderDetail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_OrderDetail.setAdapter(orderAdapter);
        rv_OrderDetail.setHasFixedSize(true);


    }

    public static int madatmon;
    int maban;


    //lay du lieu duoc goi tu orderAdapter
    void getData() {
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        //String txt = bundle.getString("madatmon");
        madatmon = bundle.getInt("madatmon");
        maban = bundle.getInt("maban");

    }

    //khoi tao gia tri cho list
    private void initData() {
        getData();
        orderList = new ArrayList<>();
        DBManager dbManager = new DBManager(getApplicationContext());
        orderList = dbManager.getDetailOrder(madatmon);
}

    public void themmonanvaods(View view) {


        Intent i = new Intent(getApplicationContext(), AddFoodOrder.class);
        i.putExtra("madatmon", madatmon);
        startActivity(i);

        Toast.makeText(getApplicationContext(), "ma dat mon: "+madatmon, Toast.LENGTH_SHORT).show();

    }

    public void canel_orderDetail(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
