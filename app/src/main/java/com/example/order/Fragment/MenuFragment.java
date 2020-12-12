package com.example.order.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Menu;
import com.example.order.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MenuFragment extends Fragment {


    private RecyclerView recyclerView;
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

        recyclerView = (RecyclerView) view.findViewById(R.id.food_recycleview);
        MenuAdapter menuAdapter = new MenuAdapter(getContext(), menuList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(menuAdapter);


        return view;
    }

    private void initData() {
        menuList = new ArrayList<>();
for(int i=0;i<20;i++){
    menuList.add(new Menu("food_"+i, R.drawable.nen_nuong, "100"+i));

}
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

}