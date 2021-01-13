package com.example.order.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order.Data.DBManager;
import com.example.order.Fragment.MenuFragment;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.Table;
import com.example.order.XuLy.XuLyMonAn;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class NewFoodActivity extends AppCompatActivity {

    EditText edtName, edtPrice;
    Button btnSave, btnAddImage;
    Spinner spnFoodStyle;
    List<String> listFoodStyle;
    XuLyMonAn xlMonAn;
    ImageView imgfood;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_food);

        anhxa();
        setTitle(R.string.new_food);
        setupSpinner();

    }

    private void setupSpinner() {

        xlMonAn = new XuLyMonAn(getApplicationContext());
        listFoodStyle = new ArrayList<>();

        listFoodStyle = xlMonAn.selectListNameStyle();


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, listFoodStyle);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnFoodStyle.setAdapter(arrayAdapter);
        selectFoodStyle();
    }

    private void selectFoodStyle() {
        spnFoodStyle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "ten loai mon an " + listFoodStyle.get(position), Toast.LENGTH_SHORT).show();

                nameStyle = listFoodStyle.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(), "chua chon loai mon an", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void anhxa() {
        edtName = (EditText) findViewById(R.id.edt_nameFood);
        edtPrice = (EditText) findViewById(R.id.edt_priceFood);
        btnSave = (Button) findViewById(R.id.btn_saveFood);
        spnFoodStyle = (Spinner) findViewById(R.id.spn_FoodStyle);
        imgfood = (ImageView) findViewById(R.id.img_imageFood);
    }

    String nameStyle;
    int ID;

    //them mon an moi vao thucdon
    public void save_Food(View view) {

        XuLyMonAn xlMonAn = new XuLyMonAn(getApplicationContext());

        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();

        //lay id loai mon an
        ID = xlMonAn.getIDFoodStyle(nameStyle);

        if (name.matches("") || price.matches("")) {
            Toast.makeText(getApplicationContext(), "chua nhap", Toast.LENGTH_SHORT).show();
        } else {

            Menu menu = createMenu();
            long check = xlMonAn.addFood(menu);
            if (check > 0) {
                Toast.makeText(this, "Them thanh cong", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "Them that bai?!!", Toast.LENGTH_SHORT).show();

            }
        }
    }

    //chuyen kieu du lieu anh tu imageview sang byte[]
    private byte[] imageViewToByte(ImageView imageView) {
        Bitmap bm = ((BitmapDrawable) imageView.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    //create new food
    private Menu createMenu() {
        String name = edtName.getText().toString();
        String price = edtPrice.getText().toString();

        Menu table = new Menu(name, price, ID, imageViewToByte(imgfood));
        return table;
    }


    final int REQUEST_CODE = 999;

    //back to main activity
    public void cancel_food(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void btn_addNewFoodStyle(View view) {
        startActivity(new Intent(this, NewFoodStyleActivity.class));
    }

    public void chooseImage(View view) {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, REQUEST_CODE);
            } else {
                Toast.makeText(this, "ban khong co quyen truy cap hinh anh", Toast.LENGTH_SHORT).show();

            }
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {


        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bmap = BitmapFactory.decodeStream(inputStream);
                imgfood.setImageBitmap(bmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}