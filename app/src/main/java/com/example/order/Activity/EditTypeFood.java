package com.example.order.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;

import java.util.ArrayList;
import java.util.List;

public class EditTypeFood extends AppCompatActivity {

    Button btnSave, btnCancel;
    EditText edtNameType;
    String nameStyle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_typefood);

        anhxa();
        setDataView();
    }

    private void anhxa() {
        btnSave = (Button) findViewById(R.id.btn_saveChange_editType);
        btnCancel = (Button) findViewById(R.id.btn_cancel_editType);
        edtNameType = (EditText) findViewById(R.id.edt_nameStyle);
        setDataView();

    }

    private void setDataView() {
        getData();

        edtNameType.setText(nameStyle);
    }

    void getData() {
        Bundle bundle = getIntent().getExtras();
        nameStyle = bundle.getString("tenloai");
    }

    public void cancel_editType(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void saveChangeEditType(View view) {

        String s = edtNameType.getText().toString();

        if (s.matches("")) {
            Toast.makeText(getApplicationContext(), "Ban chua nhap ten loai", Toast.LENGTH_SHORT).show();
        } else {

            if (checkNameStyle(s)) {

                Toast.makeText(getApplicationContext(), "Ten nay da ton tai", Toast.LENGTH_SHORT).show();

            } else {
                XuLyMonAn xl = new XuLyMonAn(getApplicationContext());

                int id = xl.getIDFoodStyle(nameStyle);
                xl.updateNameStyle(s, id);

                List<String> check = xl.checkDel(s);
                if (check.size() != 0) {
                    Toast.makeText(getApplicationContext(), "cap nhat thanh cong ", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    Toast.makeText(getApplicationContext(), "cap nhat that  bai ", Toast.LENGTH_SHORT).show();

                }
            }

        }
    }

    //kiem tra ten da co chua
    boolean checkNameStyle(String name) {

        List<String> list = new ArrayList<>();
        XuLyMonAn xl = new XuLyMonAn(getApplicationContext());
        //lay danh sach ten loai mon an
        list = xl.selectListNameStyle();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).matches(name)) {
                return true;
            }
        }
        return false;

    }

}
