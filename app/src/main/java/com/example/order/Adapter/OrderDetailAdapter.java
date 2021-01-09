package com.example.order.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.Activity.DetailOrderActivity;
import com.example.order.Activity.MainActivity;
import com.example.order.Activity.UpdateQuantityActivity;
import com.example.order.Data.DBManager;
import com.example.order.R;
import com.example.order.ShowOrder;
import com.example.order.XuLy.XuLyMonAn;

import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.OrderDetailViewholder> {

    Context context;
    List<ShowOrder> list;

    public OrderDetailAdapter(Context context, List<ShowOrder> detailList) {
        this.context = context;
        this.list = detailList;
    }

    @NonNull
    @Override
    public OrderDetailAdapter.OrderDetailViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_order_detail, parent, false);

        OrderDetailAdapter.OrderDetailViewholder viewHolder = new OrderDetailAdapter.OrderDetailViewholder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.OrderDetailViewholder holder, int position) {

        ShowOrder order = list.get(position);
        holder.txt_tenmonan.setText(order.getTenmonan() + "");
        holder.txt_soluong.setText(order.getSoluong() + "");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class OrderDetailViewholder extends RecyclerView.ViewHolder {

        TextView txt_tenmonan, txt_soluong;
        Button btn_edit, btn_delete;

        public OrderDetailViewholder(@NonNull View itemView) {
            super(itemView);
            txt_tenmonan = (TextView) itemView.findViewById(R.id.txt_nameFood_OrderDetail);
            txt_soluong = (TextView) itemView.findViewById(R.id.txt_quantityFood_OrderDetail);
            btn_delete = (Button) itemView.findViewById(R.id.btn_delete_OrderDetail);


            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XuLyMonAn xlMonAN=new XuLyMonAn(context);

                    DBManager db=new DBManager(context);
                    Cursor cs=xlMonAN.getIDFood(txt_tenmonan.getText().toString());
                    cs.moveToFirst();
                    int idfood=Integer.parseInt(cs.getString(0));
                    int iddatmon = DetailOrderActivity.madatmon;

                    db.deleteFoodOrder(iddatmon,idfood);
                    Intent i = new Intent(context, MainActivity.class);
                    i.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);;
                }
            });


            btn_edit = (Button) itemView.findViewById(R.id.btn_edit_OrderDetail);
            btn_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i=new Intent(context, UpdateQuantityActivity.class);

                    int slg=Integer.parseInt(txt_soluong.getText().toString());
                    String txtName=txt_tenmonan.getText().toString();

                    i.putExtra("tenmonan",txtName);
                    i.putExtra("soluong",slg);
                    context.startActivity(i);
                }
            });


        }
    }
}
