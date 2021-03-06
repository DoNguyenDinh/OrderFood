package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Adapter.ViewPagerAdapter;
import com.example.order.Menu;
import com.example.order.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    public Button btn;
    private RecyclerView rvMenuFood;
    public MenuAdapter menuAdapter;
    private List<Menu> mlistFood = new ArrayList<>();
    public static String idTable;
    public final static String titleTable = "Table";
    public static String idStaff, nameStaff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();

//        Toast.makeText(this, "Ma nhan vien" + LoginActivity.ma_nhan_vien, Toast.LENGTH_SHORT).show();
    }

    private void anhxa() {
        mtabLayout = findViewById(R.id.tablayout);
        mviewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(viewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);

    }

    @SuppressLint("ResourceType")
    public void table_Order(View view) {

        Intent i = new Intent(MainActivity.this, Order_Activity.class);
        startActivity(i);

    }


    //mở activity thêm món ăn
    public void newFood(View view) {

        Intent intent = new Intent(MainActivity.this, NewFoodActivity.class);
        startActivity(intent);
    }


    //mở activity thêm bàn ăn
    public void newTable(View view) {

        Intent intent = new Intent(MainActivity.this, NewTableActivity.class);
        startActivity(intent);
    }


    //thanh toán tiền hóa đơn
    public void payMoney(View view) {
        startActivity(new Intent(MainActivity.this, PayActivity.class));
    }

    public void logOut(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        LoginActivity.ma_nhan_vien = 0;
        LoginActivity.ten_nv = "";

    }
}