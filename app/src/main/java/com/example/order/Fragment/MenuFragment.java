package com.example.order.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.R;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {


    private RecyclerView rvMenu;
    private List<Menu> menuList;
    private MenuAdapter menuAdapter;

    public MenuFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        rvMenu = (RecyclerView) view.findViewById(R.id.rv_menufood);
        menuAdapter = new MenuAdapter(getContext(), menuList);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMenu.setAdapter(menuAdapter);
        rvMenu.setHasFixedSize(true);


        return view;
    }

    private void initData() {
        menuList = new ArrayList<>();
        DBManager dbManager = new DBManager(getContext());

        //lay danh sach ban tu database
        menuList = dbManager.selectListMenu();


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

}