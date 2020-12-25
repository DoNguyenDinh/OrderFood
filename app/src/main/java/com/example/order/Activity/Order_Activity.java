package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.order.Adapter.TableAdapter;
import com.example.order.R;

public class Order_Activity extends AppCompatActivity {

    private TextView tableChoose;
    String txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        RecyclerView rv=findViewById(R.id.rv_table);

        tableChoose = (TextView) findViewById(R.id.txt);
        //xt = findViewById(R.id.txt_tableName);

        getData();
    }


    //luu
    public void back_activitymain(View view) {

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }



    //lay id ban
    void getData(){
        Intent intent = getIntent();
        Bundle bundle = getIntent().getExtras();

        txt = bundle.getString("keyname");

        tableChoose.setText("Please choose food and drink "+"( Table "+txt+" )");
    }


}

