package com.example.order.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Activity.LoginActivity;
import com.example.order.Activity.MainActivity;
import com.example.order.Adapter.TableAdapter;
import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.Table;
import com.example.order.XuLy.XuLyDangNhap;

import java.util.ArrayList;
import java.util.List;


public class StaffFragment extends Fragment {

    TextView txt_name_staff;
    Button btn_logout;

    public StaffFragment() {
        // Required empty public constructor
    }


    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff, container, false);
        anhxa(view);
        return view;
    }

    private void anhxa(View view) {
        txt_name_staff = view.findViewById(R.id.txt_name_staff);
        int id = LoginActivity.ma_nhan_vien;
        if (id != 0) {
            txt_name_staff.setText(LoginActivity.ten_nv);
        } else {
            Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}