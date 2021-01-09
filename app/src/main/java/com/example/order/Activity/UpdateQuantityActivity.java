package com.example.order.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;

public class UpdateQuantityActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edi_quantity_food);
        setDataView();
    }

    String tenmon = "";
    int solg = 0;

    TextView txtten;
    EditText edtSL;


    //set du lieu cho textview
    void setDataView() {

        getData();
        txtten = (TextView) findViewById(R.id.txt_foodname_edit);
        edtSL = (EditText) findViewById(R.id.edt_quantity_orderdetail);

        edtSL.setText(solg + "");
        txtten.setText(tenmon);

    }

    int maban;


    //lay du lieu tu orderadapter
    void getData() {
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        tenmon = bundle.getString("tenmonan");
        solg = bundle.getInt("soluong");
        maban = bundle.getInt("maban");

    }


    //cap nhat lai so luong mon an
    public void update_quantity(View view) {

        String tes = edtSL.getText().toString();

        if (tes.matches("") || tes == null) {
            Toast.makeText(this, "so luong trong", Toast.LENGTH_SHORT).show();
        } else {
            int iddatmon = DetailOrderActivity.madatmon;

            XuLyMonAn xlMonan=new XuLyMonAn(getApplicationContext());
            Cursor cs = xlMonan.getIDFood(tenmon);
            cs.moveToFirst();

            //lay ma mon an
            int idfood = cs.getInt(0);

            int solg = Integer.parseInt(edtSL.getText() + "");

            xlMonan.updateQuantityFood(iddatmon, solg, idfood);

            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();
        }
    }
}
