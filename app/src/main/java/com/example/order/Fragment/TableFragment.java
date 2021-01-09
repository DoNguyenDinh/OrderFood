package com.example.order.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.MainActivity;
import com.example.order.Activity.NewTableActivity;
import com.example.order.Adapter.TableAdapter;
import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.Table;
import com.example.order.XuLy.XuLyBanAn;

import java.util.ArrayList;
import java.util.List;


public class TableFragment extends Fragment {


    private RecyclerView rvTable;
    private List<Table> menuList;
    private TableAdapter tableAdapter;

    public TableFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table, container, false);

        rvTable = (RecyclerView) view.findViewById(R.id.rv_table);
        rvTable.hasFixedSize();
        tableAdapter = new TableAdapter(getContext(), menuList);
        rvTable.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTable.setAdapter(tableAdapter);

        return view;
    }


    //load du lieu vao recyclerview
    private void initData() {
        menuList = new ArrayList<>();
        XuLyBanAn xlBanAn = new XuLyBanAn(getContext());

        //lay danh sach ban tu database
        menuList = xlBanAn.selectListTable();
        if (menuList.size() == 0) {
            Toast.makeText(getContext(), "danh sach ban trong", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

}