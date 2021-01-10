package com.example.order.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.OrderDetail;
import com.example.order.R;
import com.example.order.XuLy.XuLyBanAn;
import com.example.order.XuLy.XuLyDatMon;
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;

public class InputValues extends AppCompatActivity {
    private String nameFood, idFood;
    private EditText edtQuantity;
    private TextView txtNameFood, txtIDFood;
    private Button btnInsertOrder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_values);

        getData();
        anhxa();

    }

    private void anhxa() {
        edtQuantity = (EditText) findViewById(R.id.edt_quantity);
        btnInsertOrder = (Button) findViewById(R.id.btn_insertFood);
        txtNameFood = (TextView) findViewById(R.id.txt_showNameFood);
        txtIDFood = (TextView) findViewById(R.id.txt_idFood_input);


        setDataView();

    }

    public void back_orderActivity(View view) {

        int idfood = Integer.parseInt(idFood);
        int quantity = Integer.parseInt(edtQuantity.getText().toString());


        XuLyBanAn xlBanAn = new XuLyBanAn(getApplicationContext());
        XuLyDatMon xlDatMon = new XuLyDatMon(getApplicationContext());
        XuLyMonAn xlMonAn = new XuLyMonAn(getApplicationContext());

        OrderDetail detail = insertData(idOrder, idfood, quantity);


//        Toast.makeText(getApplicationContext(), "ma dat mon" + detail.getIdOrder(), Toast.LENGTH_SHORT).show();
    //    Toast.makeText(getApplicationContext(), "ma mon an" + detail.getIdFood(), Toast.LENGTH_SHORT).show();
      //  Toast.makeText(getApplicationContext(), "so luong " + detail.getQuantityFood(), Toast.LENGTH_SHORT).show();


//
//        //kiem tra mon an da ton tai trong order chua
//

        List<String> n = new ArrayList<>();
        n = xlDatMon.selectListFoodOrdered(idOrder, idfood);
        Toast.makeText(getApplicationContext(), "check: " + n.size(), Toast.LENGTH_SHORT).show();

        if (n.size() > 0) {
            xlMonAn.updateQuantityFood(idOrder, quantity, idfood);
            Toast.makeText(getApplicationContext(), "cap nhat so luong thanh cong", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, MainActivity.class));

        } else {
            long checkResult = xlDatMon.insertDataDetail(detail);
            if (checkResult > 0) {
                int mabanan = Order_Activity.maban;

                //gan gia tri ban da duoc dat
                xlBanAn.updateTableStatus(mabanan, false);

                Toast.makeText(getApplicationContext(), "them thanh cong", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            } else {
                Toast.makeText(getApplicationContext(), "them that bai", Toast.LENGTH_SHORT).show();
            }
        }

    }

    int idOrder;

    //lay gia tri tu menudapter
    void getData() {
        Bundle bundle = getIntent().getExtras();
        nameFood = bundle.getString("tenmonan");
        idFood = bundle.getString("mamonan");
        idOrder = bundle.getInt("madatmonan");
    }

    //set du lieu cho textview
    void setDataView() {
        txtNameFood.setText(nameFood);
        // txtIDFood.setText();
    }

    //insert mon an
    OrderDetail insertData(int idorder, int idFood, int quantity) {

        OrderDetail detail = new OrderDetail(idorder, idFood, quantity);
        return detail;

    }
}
