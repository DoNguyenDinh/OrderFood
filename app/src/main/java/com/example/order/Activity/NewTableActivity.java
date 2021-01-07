package com.example.order.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.order.Adapter.TableAdapter;
import com.example.order.Data.DBManager;
import com.example.order.Fragment.TableFragment;
import com.example.order.R;
import com.example.order.Table;

import java.util.List;


public class NewTableActivity extends AppCompatActivity {

    EditText edtNameTable;
    Button btnsave;
    List<Table> tables;
    TableAdapter tableAdapter;
    RecyclerView rvTable;
    public static final String NAME = "tableFood";
    public static final String Va = "idtable";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_table);
        anhxa();

        setTitle(R.string.new_table);
    }


    public void save_table(View view) {

        String s = edtNameTable.getText().toString();
        if (s.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap ten ban", Toast.LENGTH_SHORT).show();
        } else {
            DBManager dbManager = new DBManager(NewTableActivity.this);

            Table table = createTable();
           long checkResult = dbManager.addTableFood(table);

           if(checkResult>0) {
               Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();
           }else {
               Toast.makeText(this, "Them that bai ", Toast.LENGTH_SHORT).show();

           }

        }
    }

    private Table createTable() {
        String name = edtNameTable.getText().toString();
        Table table = new Table(name);
        return table;
    }

    void anhxa() {
        edtNameTable = (EditText) findViewById(R.id.edt_nameTable);
        btnsave = (Button) findViewById(R.id.btn_saveTable);
        DBManager dbManager = new DBManager(this);
        tables = dbManager.selectListTable();
        rvTable = (RecyclerView) findViewById(R.id.rv_table);
    }


    public void cancel_table(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}