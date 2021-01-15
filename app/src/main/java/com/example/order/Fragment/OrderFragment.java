package com.example.order.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.MenuAdapter;
import com.example.order.Adapter.OrderAdapter;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.Order;
import com.example.order.R;
import com.example.order.XuLy.XuLyDatMon;

import java.util.ArrayList;
import java.util.List;


public class OrderFragment extends Fragment {



    public OrderFragment(){

    }
    private RecyclerView rvOrder;
    private List<Order> orderList;
    private OrderAdapter orderAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }


    private void initData() {
        orderList = new ArrayList<>();
        XuLyDatMon xlDatMon=new XuLyDatMon(getContext());

        //lay danh sach ban tu database
        orderList = xlDatMon.selectListOrdered();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_order, container, false);

        rvOrder = (RecyclerView) view.findViewById(R.id.rv_listOrdered);
        orderAdapter = new OrderAdapter(getContext(), orderList);
        rvOrder.setLayoutManager(new LinearLayoutManager(getContext()));
        rvOrder.setAdapter(orderAdapter);
        rvOrder.setHasFixedSize(true);

        return view;
    }
}