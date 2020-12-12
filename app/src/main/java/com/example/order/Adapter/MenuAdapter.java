package com.example.order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Menu;
import com.example.order.R;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    private List<Menu> menuList;
    Context context;

    public MenuAdapter(Context mtexfood, List<Menu> mlistmenu) {
        this.menuList = mlistmenu;
        context = mtexfood;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);

        MenuViewHolder viewHolder=new MenuViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        Menu mMenu =  menuList.get(position);
        holder.mImageFood.setImageResource(mMenu.getPhoto());
        holder.mTextName.setText(mMenu.getNameFood());
        holder.mTextPrice.setText(mMenu.getPrice());

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageFood;
        private TextView mTextName;
        private TextView mTextPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageFood = (ImageView) itemView.findViewById(R.id.image_view_food);
            mTextName = (TextView) itemView.findViewById(R.id.txt_name_food);
            mTextPrice=(TextView)itemView.findViewById(R.id.txt_food_price);
        }
    }
}
