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

import org.w3c.dom.Text;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {


    private List<Menu> menuList;
    Context context;
    RecyclerView rvFood;


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
        holder.mTextPrice.setText(mMenu.getPrice()+" $");

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mID;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.txt_name_food);
            mTextPrice = (TextView) itemView.findViewById(R.id.txt_food_price);
        }
    }

}
