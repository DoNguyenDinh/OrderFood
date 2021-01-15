package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Adapter.PayDisplayAdapter;
import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.ShowOrder;
import com.example.order.XuLy.XuLyBanAn;
import com.example.order.XuLy.XuLyDatMon;

import java.util.List;

public class PayActivity extends AppCompatActivity {

    RecyclerView rv_pay;
    List<ShowOrder> listPay;
    TextView txttongtien;
    XuLyDatMon xlDatMon;
    XuLyBanAn xlbanAn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        setTitle(R.string.pay);
        anhxa();
        txttongtien = (TextView) findViewById(R.id.txt_tongsotien);

        xlDatMon = new XuLyDatMon(getApplicationContext());
        int iddatmon = Integer.parseInt(txt);
        listPay = xlDatMon.getInfo(iddatmon);

        int giatien = 0;
        for (int i = 0; i < listPay.size(); i++) {
            giatien += listPay.get(i).getThanhtien();
        }
        txttongtien.setText("Tong so tien; " + giatien + " $");


        PayDisplayAdapter p = new PayDisplayAdapter(getApplicationContext(), listPay);

        rv_pay = (RecyclerView) findViewById(R.id.rv_ListFoodAndPrice);
        rv_pay.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rv_pay.setAdapter(p);

       // Toast.makeText(this, "ma dat mon an: " + iddatmon, Toast.LENGTH_SHORT).show();

    }

    private void anhxa() {
        getData();
    }

    public void back_activitymain_pay(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    String txt;
    int maban;


    //lay du lieu tu orderadapter
    void getData() {

        Bundle bundle = getIntent().getExtras();
        txt = bundle.getString("madatmon");
        maban = bundle.getInt("maban");

    }

    public void thanhtoan(View view) {

        DBManager db = new DBManager(getApplicationContext());

        xlbanAn = new XuLyBanAn(getApplicationContext());
        int iddatmon = Integer.parseInt(txt);
        db.updateStatusorder(iddatmon);

        Toast.makeText(this, "Thanh toan thanh cong", Toast.LENGTH_SHORT).show();

        //cap nhat lai trang thai ban chua duoc dat
        xlbanAn.updateTableStatus(maban, true);
        startActivity(new Intent(this, MainActivity.class));

    }
}
