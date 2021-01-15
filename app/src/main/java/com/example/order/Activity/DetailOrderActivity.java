package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.order.Adapter.OrderDetailAdapter;

import com.example.order.R;
import com.example.order.ShowOrder;
import com.example.order.XuLy.XuLyDatMon;

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

        madatmon = bundle.getInt("madatmon");
        maban = bundle.getInt("maban");

    }

    //khoi tao gia tri cho list
    private void initData() {
        getData();
        orderList = new ArrayList<>();
        XuLyDatMon xlDatMon = new XuLyDatMon(getApplicationContext());
        orderList = xlDatMon.getDetailOrder(madatmon);
    }

    public void themmonanvaods(View view) {

        Intent i = new Intent(getApplicationContext(), AddFoodOrder.class);
        i.putExtra("madatmon", madatmon);
        startActivity(i);

    }

    public void canel_orderDetail(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
