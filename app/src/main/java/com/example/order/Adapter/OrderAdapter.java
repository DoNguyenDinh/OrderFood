package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.DetailOrderActivity;
import com.example.order.Activity.Order_Activity;
import com.example.order.Activity.PayActivity;
import com.example.order.Data.DBManager;
import com.example.order.Order;
import com.example.order.R;
import com.example.order.ShowOrder;
import com.example.order.XuLy.XuLyBanAn;
import com.example.order.XuLy.XuLyDatMon;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {


    private List<Order> orderList;
    Context context;


    public OrderAdapter(Context mtexfood, List<Order> mlistMenu) {
        this.orderList = mlistMenu;
        this.context = mtexfood;
    }

    @NonNull
    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);

        OrderAdapter.OrderViewHolder viewHolder = new OrderAdapter.OrderViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {

        XuLyDatMon xlDatMon = new XuLyDatMon(context);
        XuLyBanAn xuLyBanAn=new XuLyBanAn(context);
        Order order = orderList.get(position);



        holder.txtIDTable.setText(order.getIdTable() + "");
        holder.txtIDOrder.setText(order.getId() + "");

        int idtable=Integer.parseInt(holder.txtIDTable.getText().toString());
        String nameTble=xuLyBanAn.nameTable(idtable);

        //hien thi ten ban an
        holder.txtNameTable.setText(nameTble);

        //hien thi tong so tien
        int iddatmon;
        iddatmon = Integer.parseInt(holder.txtIDOrder.getText().toString());
        List<ShowOrder> listPay;
        listPay = xlDatMon.getInfo(iddatmon);

        int giatien = 0;
        for (int i = 0; i < listPay.size(); i++) {
            giatien += listPay.get(i).getThanhtien();
        }
        holder.txtTotalMoney.setText("Tong so tien; " + giatien + " $");
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView txtIDOrder, txtIDTable;
        TextView txtTotalMoney,txtNameTable;
        Button btn_pay, btn_detailOrder;


        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIDOrder = (TextView) itemView.findViewById(R.id.txt_TotalCost);
            txtNameTable= (TextView) itemView.findViewById(R.id.txt_nameTable_order);
            txtTotalMoney = (TextView) itemView.findViewById(R.id.txt_TotalCost_order);
            txtIDTable=(TextView)itemView.findViewById(R.id.txt_id_table_order) ;

            btn_pay = (Button) itemView.findViewById(R.id.btn_pay);
            btn_pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, PayActivity.class);
                    String madatmon = txtIDOrder.getText() + "";
                    int maban = Integer.parseInt(txtIDTable.getText() + "");

                    i.putExtra("madatmon", madatmon);
                    i.putExtra("maban", maban);
                    context.startActivity(i);
                }
            });

            btn_detailOrder = (Button) itemView.findViewById(R.id.btn_chitiet);
            btn_detailOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, DetailOrderActivity.class);
                    int madatmon = Integer.parseInt(txtIDOrder.getText() + "");
                    int maban = Integer.parseInt(txtIDTable.getText() + "");

                    i.putExtra("madatmon", madatmon);
                    i.putExtra("maban", maban);
                    context.startActivity(i);
                }
            });
        }
    }
}

