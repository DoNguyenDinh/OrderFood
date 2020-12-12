package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.order.R;

public class Order_Activity extends AppCompatActivity {

    private TextView tableChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();

        String txt = intent.getStringExtra(MainActivity.idTable);
        tableChoose = (TextView) findViewById(R.id.txt);


        tableChoose.setText("Please choose food and drink "+"( Table "+txt+" )");

    }

    public void back_activitymain(View view) {

        Intent i = new Intent(this, MainActivity.class);

        startActivity(i);
    }


}