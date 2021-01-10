package com.example.order.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order.FoodStyle;
import com.example.order.Menu;
import com.example.order.R;

import java.util.List;

public class LoadFoodAdapter extends RecyclerView.Adapter<LoadFoodAdapter.LoadFoodViewHolder> {

    private List<Menu> menuList;
    Context context;

    public LoadFoodAdapter(Context mtexfood, List<Menu> mlistMenu) {
        this.menuList = mlistMenu;
        this.context = mtexfood;

    }

    @NonNull
    @Override
    public LoadFoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuView = LayoutInflater.from(context).inflate(R.layout.item_food, parent, false);

        LoadFoodAdapter.LoadFoodViewHolder viewHolder = new LoadFoodAdapter.LoadFoodViewHolder(menuView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoadFoodViewHolder holder, int position) {

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


    public class LoadFoodViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mID;
        private Button btnedit;
        private ImageView img;

        public LoadFoodViewHolder(@NonNull View itemView) {
            super(itemView);

            mTextName = (TextView) itemView.findViewById(R.id.txt_name_food);
            mTextPrice = (TextView) itemView.findViewById(R.id.txt_food_price);
            mID = (TextView) itemView.findViewById(R.id.txt_idFood_item);
            img = (ImageView) itemView.findViewById(R.id.img_imageFood_menu);
            btnedit=(Button)itemView.findViewById(R.id.btn_insertFood);

            //btnedit.setOnClickListener();
        }
    }
}
