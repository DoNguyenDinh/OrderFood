package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.MainActivity;
import com.example.order.Activity.Order_Activity;
import com.example.order.R;
import com.example.order.Table;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MenuViewHolder> {


    private List<Table> menuList;
    Context context;
    RecyclerView rvTable;
    static String NAME_TB = "name";


    public TableAdapter(Context mtexfood, List<Table> mlistMenu) {
        this.menuList = mlistMenu;
        this.context = mtexfood;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_table, parent, false);

        MenuViewHolder viewHolder = new MenuViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MenuViewHolder holder, final int position) {
        Table mMenu = menuList.get(position);


        holder.mNameTable.setText(mMenu.getNameTable());


    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTable;
        private TextView id;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTable = (TextView) itemView.findViewById(R.id.txt_tableName);

            mNameTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos;//vi tri item duoc click
                    pos = getLayoutPosition();


                    Toast.makeText(context, pos + "", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, Order_Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("keyname", pos + 1 + "");

                    i.putExtras(bundle);
                    context.startActivity(i);
                }
            });
        }
    }
}
