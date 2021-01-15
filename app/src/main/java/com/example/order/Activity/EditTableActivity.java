package com.example.order.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.order.Data.DBManager;
import com.example.order.Fragment.TableFragment;
import com.example.order.R;
import com.example.order.XuLy.XuLyBanAn;

import java.util.ArrayList;
import java.util.List;


public class EditTableActivity extends AppCompatActivity {

    Button btndelete, btnedit, btncancel;
    EditText edtName;
    XuLyBanAn xlBanAn;
    List<String> listTable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_table);

        xlBanAn = new XuLyBanAn(this);
        anhxa();
        setDataView();

    }

    private void setDataView() {
        getData();
        edtName.setText(nameTable + "");
    }

    private void anhxa() {
        btncancel = (Button) findViewById(R.id.btn_cancel_editTable);
        btndelete = (Button) findViewById(R.id.btn_deleteTable);
        btnedit = (Button) findViewById(R.id.btn_saveChange);
        edtName = (EditText) findViewById(R.id.edt_editNameTable);
    }

    public void deleteTable(View view) {
        String name = edtName.getText().toString();
        xlBanAn.deleteTabele(name);
        Toast.makeText(getApplicationContext(), "Xoa thanh cong: ", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(EditTableActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(i);
    }

    String nameTable;

    //lay gia tri tu tableadapter
    void getData() {
        Bundle bundle = getIntent().getExtras();
        nameTable = bundle.getString("tenbanan");
    }

    public void canel_editTable(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public  boolean checkTableName(String userName) {

        listTable = new ArrayList<>();
        xlBanAn = new XuLyBanAn(getApplicationContext());
        listTable = xlBanAn.selectlistNameTable(userName);
        if (listTable.size() == 0) {
            return false;
        }
        return true;
    }

    public void saveChangEdit(View view) {

        //lay id ban an
        Cursor cs = xlBanAn.getIDTable(nameTable);
        cs.moveToFirst();
        int id = Integer.parseInt(cs.getString(0));
        String name = edtName.getText().toString();

//        if (checkTableName(name)) {
//            Toast.makeText(getApplicationContext(), "ban nay da ton tai ", Toast.LENGTH_SHORT).show();
//
//        } else {
            xlBanAn.updateTableName(name, id);
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(getApplicationContext(), "Cap nhat ban an thanh cong ", Toast.LENGTH_SHORT).show();

       // }
    }
}
