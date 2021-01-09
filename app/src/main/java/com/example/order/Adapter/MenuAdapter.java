package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.InputValues;
import com.example.order.Activity.Order_Activity;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.R;


import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    private List<Menu> menuList;
    Context context;


    public MenuAdapter(Context mtexfood, List<Menu> mlistMenu) {
        this.menuList = mlistMenu;
        this.context = mtexfood;

    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);

        MenuViewHolder viewHolder = new MenuViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu mMenu = menuList.get(position);
        holder.mTextName.setText(mMenu.getNameFood());
        holder.mTextPrice.setText(mMenu.getPrice() + " $");
        holder.mID.setText(mMenu.getId() + "");

        byte[] foodimage = mMenu.getImg();
        Bitmap bm = BitmapFactory.decodeByteArray(foodimage,0,foodimage.length);

        holder.img.setImageBitmap(bm);

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mID;
        private Button btn_insertFood;
        private ImageView img;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.txt_name_food);
            mTextPrice = (TextView) itemView.findViewById(R.id.txt_food_price);
            mID = (TextView) itemView.findViewById(R.id.txt_idFood_item);
            img = (ImageView) itemView.findViewById(R.id.img_imageFood_menu);


            btn_insertFood = (Button) itemView.findViewById(R.id.btn_insertFood);
            btn_insertFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    DBManager db = new DBManager(context);


                    //lay ma dat mon moi nhat
                    Cursor cs = db.getIDOrder();
                    cs.moveToFirst();
                    String idOrder;
                    idOrder = cs.getString(0);
                    int idorder = Integer.parseInt(idOrder);

                    Intent i = new Intent(context, InputValues.class);
                    i.putExtra("tenmonan", mTextName.getText().toString());
                    i.putExtra("mamonan", mID.getText() + "");
                    i.putExtra("madatmonan", idorder);

                    context.startActivity(i);

                }


            });
        }
    }

}
