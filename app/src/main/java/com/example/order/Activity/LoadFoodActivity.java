package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.LoadFoodAdapter;
import com.example.order.Adapter.OrderDetailAdapter;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;

import java.util.List;

public class LoadFoodActivity extends AppCompatActivity {

    String nameStyle;
    RecyclerView rv;
    List<Menu> menuList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_food);

        getData();
        setTitle(nameStyle);

        init();

    }


    void getData() {
        Bundle bundle = getIntent().getExtras();
        nameStyle = bundle.getString("tenloai");
    }

    LoadFoodAdapter foodAdapter;

    private void init() {

        rv = (RecyclerView) findViewById(R.id.rv_loadFood);

        XuLyMonAn xuLyMonAn=new XuLyMonAn(getApplicationContext());
        menuList=xuLyMonAn.selectMenu(nameStyle);

        foodAdapter = new LoadFoodAdapter(getApplicationContext(), menuList);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv.setAdapter(foodAdapter);
        rv.setHasFixedSize(true);
    }

    public void cancel_load(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void newFood_Load(View view) {
        startActivity(new Intent(this,NewFoodActivity.class));
    }
}
