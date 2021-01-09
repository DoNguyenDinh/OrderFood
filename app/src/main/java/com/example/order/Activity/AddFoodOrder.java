package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.AddFoodOrderAdapter;
import com.example.order.Adapter.MenuAdapter;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.ShowOrder;
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;


public class AddFoodOrder extends AppCompatActivity {

    RecyclerView rv;
    List<Menu> listFood;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_order_food);
        initData();


        rv=(RecyclerView)findViewById(R.id.rv_addFoodOrder);
        AddFoodOrderAdapter menuAdapter = new AddFoodOrderAdapter(this, listFood);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(menuAdapter);

    }

    private void initData() {
        listFood=new ArrayList<>();
     XuLyMonAn xl=new XuLyMonAn(getApplicationContext());

        listFood = xl.selectListMenu();

        getData();
    }


   public static int madatmon;
    //lay du lieu tu detailorder
    void getData() {
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        madatmon = bundle.getInt("madatmon");
    }
}
