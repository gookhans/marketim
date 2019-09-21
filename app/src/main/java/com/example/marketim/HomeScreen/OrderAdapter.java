package com.example.marketim.HomeScreen;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marketim.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {

    private List<OrderModel> orderList;
    private ItemClickListener mClickListener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_day, txt_month, txt_market_name, txt_order_name, txt_product_price;

        public MyViewHolder(View view) {
            super(view);
            txt_day = view.findViewById(R.id.txt_day);
            txt_month =  view.findViewById(R.id.txt_month);
            txt_market_name =  view.findViewById(R.id.txt_market_name);
            txt_order_name =  view.findViewById(R.id.txt_order_name);
            txt_product_price =  view.findViewById(R.id.txt_product_price);
        }
    }


    public OrderAdapter(ItemClickListener itemClickListener) {
        this.orderList = new ArrayList<OrderModel>();
        this.mClickListener = itemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_adapter_row, parent, false);

        return new MyViewHolder(itemView);
    }
    public interface ItemClickListener {
        void onOrderModelClick(View view, int position, OrderModel data);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderModel order = orderList.get(position);
        holder.txt_day.setText(String.valueOf(order.getDay()));
        holder.txt_month.setText(String.valueOf(order.getMonth()));
        holder.txt_market_name.setText(order.getMarketName());
        holder.txt_order_name.setText(order.getOrderName());
        holder.txt_product_price.setText(String.valueOf(order.getProductPrice()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }
    public void addList(List<OrderModel> data) {
        orderList.addAll(data);
        notifyDataSetChanged();
    }
}