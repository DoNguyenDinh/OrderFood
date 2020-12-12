package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Adapter.ViewPagerAdapter;
import com.example.order.Menu;
import com.example.order.R;
import com.google.android.material.tabs.TabLayout;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mtabLayout = findViewById(R.id.tablayout);
        mviewPager = findViewById(R.id.viewpager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mviewPager.setAdapter(viewPagerAdapter);
        mtabLayout.setupWithViewPager(mviewPager);

        //intitview();
    }

//    private void intitview() {
//        mlistfood=new ArrayList<>();
//        for(int i=0;i<5;i++){
//            mlistfood.add(new Menu("food_1",R.drawable.cha_hai_san_boc_xa_nuong));
//            mlistfood.add(new Menu("food_2",R.drawable.nen_nuong));
//            mlistfood.add(new Menu("food_3",R.drawable.nen_nuong));
//
//        }
//        recyclerView = (RecyclerView) findViewById(R.id.recyvleview);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        menuAdapter=new MenuAdapter(mlistfood);
//        recyclerView.setAdapter(menuAdapter);
//    }

    final int flag = 0;

    public void tableorder(View view) {

    }
}