package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.Order_Activity;
import com.example.order.Data.DBManager;
import com.example.order.Order;
import com.example.order.R;
import com.example.order.Table;

import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MenuViewHolder> {


    private List<Table> menuList;
    Context context;
    public static String maban;

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
        holder.id.setText(mMenu.getId() + "");


    }


    @Override
    public int getItemCount() {
        return menuList.size();
    }


    public class MenuViewHolder extends RecyclerView.ViewHolder {

        private TextView mNameTable;
        private TextView id;
        Context ct;

        int idTable;


        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            mNameTable = (TextView) itemView.findViewById(R.id.txt_tableName);
            id = (TextView) itemView.findViewById(R.id.txt_id_table);


            mNameTable.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getLayoutPosition();

                    maban = id.getText().toString();




                    //kiem tra trang thai cua ban da duoc dat chua
                    DBManager db = new DBManager(context);
                    int id = Integer.parseInt(maban);
                    Cursor status = db.getTableStatus(id);
                    status.moveToFirst();

                    int tb = status.getInt(2);
                    if (tb == 0) {

                        Intent i = new Intent(context, Order_Activity.class);
                        i.putExtra("mabanan", id);
                         i.putExtra("vitriban", pos);

                 // Toast.makeText(context, "mabanan " + id, Toast.LENGTH_SHORT).show();


                        context.startActivity(i);
                    } else {
                        Toast.makeText(context, "Ban nay da duoc dat " + maban, Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }


        private Order createOrder() {
            Order order = new Order();

            return order;
        }

        public void newOrder() {


            DBManager db = new DBManager(context);
            Order order = createOrder();

            long i = db.addOrder(order);
            if (i >= 0) {
            } else {
                Toast.makeText(context, "them that bai", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
