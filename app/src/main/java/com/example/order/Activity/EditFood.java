package com.example.order.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public class EditFood extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_food);

        anhxa();
        setDataView();
    }

    private void setDataView() {
        getData();

        edtNameFood.setText(name);
        edtPriceFood.setText(price);

        Bitmap bm = BitmapFactory.decodeByteArray(img, 0, img.length);
        imgFood.setImageBitmap(bm);

    }

    String name, price, id;
    byte[] img;

    //lay du lieu tu loadfoodadapter
    void getData() {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("namefood");
        price = bundle.getString("pricefood");
        id = bundle.getString("id");
        img = bundle.getByteArray("image");
    }

    EditText edtNameFood, edtPriceFood;
    Button btnChooesImage, btnSave, btnCancel, btnDelFood;
    ImageView imgFood;


    final int REQUEST_CODE = 999;

    private void anhxa() {
        edtNameFood = (EditText) findViewById(R.id.edt_nameFood_Edit);
        edtPriceFood = (EditText) findViewById(R.id.edt_Money_edit);
        btnCancel = (Button) findViewById(R.id.btn_cancel_edit);
        btnSave = (Button) findViewById(R.id.btn_saveChange_edit);
        btnChooesImage = (Button) findViewById(R.id.btn_changeImage);
        btnDelFood = (Button) findViewById(R.id.btn_delFood);
        imgFood = (ImageView) findViewById(R.id.img_imageFood_edit);
    }

    public void cancel_editFood(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private byte[] imageViewToByte(ImageView imgfood) {
        Bitmap bm = ((BitmapDrawable) imgfood.getDrawable()).getBitmap();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
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
                imgFood.setImageBitmap(bmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void chooseimageFood(View view) {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE
        );
    }

    public void changeImage(View view) {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                REQUEST_CODE
        );
    }

    public void saveFoodChange(View view) {

        String name, price;
        name = edtNameFood.getText() + "";
        price = edtPriceFood.getText().toString();

        byte[] imga = imageViewToByte(imgFood);

        if (name.matches("") || price.matches("")) {
            Toast.makeText(this, "ban khong chua nhap", Toast.LENGTH_SHORT).show();
        } else {

            int idfood = Integer.parseInt(id);
            XuLyMonAn xuLyMonAn = new XuLyMonAn(getApplicationContext());
            //xuLyMonAn.updateFood(name,price,idfood,imga);

            xuLyMonAn.update(imga, name, price, idfood);
            Toast.makeText(this, "Cap nhat thanh cong", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(this, MainActivity.class));
        }

    }

    public void delFood(View view) {
        XuLyMonAn xuLyMonAn = new XuLyMonAn(getApplicationContext());

        xuLyMonAn.deleteFood(id);

        List<String> checkDel = xuLyMonAn.checkDelFood(id);
        if (checkDel.size() > 0) {
            Toast.makeText(this, "Xoa That bai", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));

        } else {

            Toast.makeText(this, "xoa thanh cong", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }


    }
}
