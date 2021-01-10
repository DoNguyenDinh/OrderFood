package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.EditTypeFood;
import com.example.order.Activity.LoadFoodActivity;
import com.example.order.Activity.MainActivity;
import com.example.order.FoodStyle;
import com.example.order.Menu;
import com.example.order.R;
import com.example.order.XuLy.XuLyMonAn;


import java.util.List;

public class LoadFoodAndStyleAdapter extends RecyclerView.Adapter<LoadFoodAndStyleAdapter.LoadViewHolder> {

    private List<FoodStyle> menuList;
    Context context;
    List<Menu> listFood;


    public LoadFoodAndStyleAdapter(Context mtexfood, List<FoodStyle> mlistMenu) {
        this.menuList = mlistMenu;
        this.context = mtexfood;

    }

    @NonNull
    @Override
    public LoadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_style_food, parent, false);

        LoadFoodAndStyleAdapter.LoadViewHolder viewHolder = new LoadFoodAndStyleAdapter.LoadViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadViewHolder holder, int position) {

        FoodStyle food = menuList.get(position);
        holder.btn.setText(food.getNameFoodStyle() + "");
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class LoadViewHolder extends RecyclerView.ViewHolder {

        Button btn, btn_del, btn_edit;
        RecyclerView rvMenu;

        public LoadViewHolder(@NonNull View itemView) {
            super(itemView);

            rvMenu = (RecyclerView) itemView.findViewById(R.id.rv_listFood);


            btn_edit = (Button) itemView.findViewById(R.id.btn_edit_type);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name=btn.getText()+"";

                    Intent i = new Intent(context, EditTypeFood.class);
                    i.putExtra("tenloai",name);
                    context.startActivity(i);
                }
            });


            btn_del = (Button) itemView.findViewById(R.id.btn_delete_type);
            btn_del.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = btn.getText() + "";

                    XuLyMonAn xuLyMonAn = new XuLyMonAn(context);
                    xuLyMonAn.deleteTypeFood(name);
                    List<String> check = xuLyMonAn.checkDel(name);
                    if (check.size() == 0) {
                        Toast.makeText(context, "Xoa thanh cong ", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, MainActivity.class));
                    } else {
                        Toast.makeText(context, "xoa that bai ", Toast.LENGTH_SHORT).show();

                    }

                }
            });


            btn = (Button) itemView.findViewById(R.id.btn_loai_mon_an);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String nameStyle = btn.getText().toString();
                    Intent i = new Intent(context, LoadFoodActivity.class);
                    i.putExtra("tenloai", nameStyle);

                    Toast.makeText(context, "ten loai " + nameStyle, Toast.LENGTH_SHORT).show();

                    context.startActivity(i);

                }
            });
        }
    }
}
