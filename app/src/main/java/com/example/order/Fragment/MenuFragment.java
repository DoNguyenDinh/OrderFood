package com.example.order.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.LoadFoodAndStyleAdapter;
import com.example.order.Adapter.MenuAdapter;
import com.example.order.TypeFood;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;


public class MenuFragment extends Fragment {


    private RecyclerView rvMenu;
    private List<Menu> menuList;
    private MenuAdapter menuAdapter;
    private List<TypeFood> listStyle;

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

        LoadFoodAndStyleAdapter food=new LoadFoodAndStyleAdapter(getContext(),listStyle);
        rvMenu.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMenu.setAdapter(food);
        rvMenu.setHasFixedSize(true);

        return view;
    }


    //khoi tao gia tri cho danh sach mon an
    private void initData() {
        menuList = new ArrayList<>();
        XuLyMonAn xl = new XuLyMonAn(getContext());

        //lay danh sach ban tu database
        menuList = xl.selectListMenu();
        listStyle=xl.selectListFoodStyle();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

}