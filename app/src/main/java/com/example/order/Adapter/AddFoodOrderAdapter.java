package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.AddFoodOrder;
import com.example.order.Activity.InputValues;
import com.example.order.Data.DBManager;
import com.example.order.Menu;
import com.example.order.R;

import java.util.List;

public class AddFoodOrderAdapter extends RecyclerView.Adapter<AddFoodOrderAdapter.FoodViewHolder> {

    private List<Menu> menuList;
    Context context;


    public AddFoodOrderAdapter(Context mtexfood, List<Menu> mlistMenu) {
        this.menuList = mlistMenu;
        this.context = mtexfood;

    }

    @NonNull
    @Override
    public AddFoodOrderAdapter.FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);

        AddFoodOrderAdapter.FoodViewHolder viewHolder = new AddFoodOrderAdapter.FoodViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddFoodOrderAdapter.FoodViewHolder holder, int position) {
        Menu mMenu = menuList.get(position);
        holder.mTextName.setText(mMenu.getNameFood());
        holder.mTextPrice.setText(mMenu.getPrice() + " $");
        holder.mID.setText(mMenu.getId() + "");

    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mID;
        private Button btn_insertFood;

        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = (TextView) itemView.findViewById(R.id.txt_name_food);
            mTextPrice = (TextView) itemView.findViewById(R.id.txt_food_price);
            mID = (TextView) itemView.findViewById(R.id.txt_idFood_item);
            btn_insertFood = (Button) itemView.findViewById(R.id.btn_insertFood);
            btn_insertFood.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, InputValues.class);
                    i.putExtra("tenmonan", mTextName.getText().toString());
                    i.putExtra("mamonan", mID.getText() + "");
                    i.putExtra("madatmonan", AddFoodOrder.madatmon);

                    context.startActivity(i);

                }


            });
        }
    }
}
