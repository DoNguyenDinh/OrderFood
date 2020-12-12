package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Adapter.ViewPagerAdapter;
import com.example.order.Menu;
import com.example.order.R;
import com.google.android.material.tabs.TabLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private TabLayout mtabLayout;
    private ViewPager mviewPager;
    public Button btn;
    private RecyclerView recyclerView;
    public MenuAdapter menuAdapter;
    private List<Menu> mlistfood;
    public static String  idTable;
    public final static String titleTable = "Table";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtabLayout = findViewById(R.id.tablayout);
        mviewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(viewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);

    }

    final int flag = 0;


    @SuppressLint("ResourceType")
    public void table_Order(View view) {

        Intent i = new Intent(MainActivity.this, Order_Activity.class);

        startActivity(i);
        Log.d("user clicked order", "user clicked");
        Button btn = (Button) view;
        idTable = btn.getText().toString();

        Intent intent = new Intent(MainActivity.this,Order_Activity.class);
        intent.putExtra(titleTable,idTable);
        startActivity(intent);

    }
}