package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.order.R;
import com.example.order.ShowOrder;

import java.util.List;

public class PayDisplayAdapter extends RecyclerView.Adapter<PayDisplayAdapter.PayViewHolder> {


    private List<ShowOrder> orderList;
    Context context;


    public PayDisplayAdapter(Context mtexfood, List<ShowOrder> mlistMenu) {
        this.orderList = mlistMenu;
        this.context = mtexfood;

    }

    @NonNull
    @Override
    public PayDisplayAdapter.PayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_food_price, parent, false);

        PayDisplayAdapter.PayViewHolder viewHolder = new  PayDisplayAdapter.PayViewHolder(menuView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PayViewHolder holder, int position) {
        ShowOrder order = orderList.get(position);
        holder.txtten.setText( order.getTenmonan()+"");
        holder.txtsoluong.setText( order.getSoluong()+"");
        holder.txtthanhtien.setText(order.getThanhtien()+"");

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class PayViewHolder extends RecyclerView.ViewHolder {

        TextView txtten, txtsoluong,txtthanhtien;

        public PayViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=(TextView)itemView.findViewById(R.id.txt_ten);
            txtsoluong=(TextView)itemView.findViewById(R.id.txt_soluong);
            txtthanhtien=(TextView)itemView.findViewById(R.id.txt_thanhtien);

        }
    }
}

