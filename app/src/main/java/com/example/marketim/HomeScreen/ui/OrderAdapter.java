package com.example.marketim.HomeScreen.ui;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marketim.HomeScreen.HomeScreenContract;
import com.example.marketim.HomeScreen.OrderModel;
import com.example.marketim.R;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> implements HomeScreenContract.Adapter {

    private List<OrderModel> orderList;
    private final ItemClickListener listener;
    //Ay tanımlamaları
    private String[] months = {"", "Ocak", "Şubat", "Mart", "Nisan", "Mayıs", "Haziran", "Temmuz", "Ağustos", "Eylül", "Ekim", "Kasım", "Aralık"};

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txt_day, txt_month, txt_market_name, txt_order_name, txt_product_price, orderDetail, summaryPrice;
        public ConstraintLayout detailLayout, preparingLayout, inCargoLayout, waitApprovalLayout;

        public MyViewHolder(View view) {
            super(view);
            txt_day = view.findViewById(R.id.txt_day);
            txt_month = view.findViewById(R.id.txt_month);
            txt_market_name = view.findViewById(R.id.txt_market_name);
            txt_order_name = view.findViewById(R.id.txt_order_name);
            txt_product_price = view.findViewById(R.id.txt_product_price);
            orderDetail = view.findViewById(R.id.order_detail);
            summaryPrice = view.findViewById(R.id.summary_price);
            detailLayout = view.findViewById(R.id.detail_layout);
            preparingLayout = view.findViewById(R.id.preparig_layout);
            inCargoLayout = view.findViewById(R.id.in_cargo_layout);
            waitApprovalLayout = view.findViewById(R.id.wait_approval_layout);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {
                    if (detailLayout.getVisibility() == View.VISIBLE)
                        detailLayout.setVisibility(View.GONE);
                    else
                        detailLayout.setVisibility(View.VISIBLE);
                }
            });
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(view, getAdapterPosition(), orderList.get(getAdapterPosition()));
            }
        }
    }

    public OrderAdapter(ItemClickListener listener) {
        this.orderList = new ArrayList<OrderModel>();
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_adapter_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        OrderModel order = orderList.get(position);
        holder.txt_day.setText(String.valueOf(order.getDay()));
        holder.txt_month.setText(months[order.getMonth()]);
        holder.txt_market_name.setText(order.getMarketName());
        holder.txt_order_name.setText(order.getOrderName());
        holder.txt_product_price.setText(String.valueOf(order.getProductPrice()) + " TL");
        holder.orderDetail.setText(order.getOrderDetail());
        holder.summaryPrice.setText(String.valueOf(order.getSummaryPrice()) + " TL");
        // sipariş durumuna göre ilgili layoutu seç
        if (order.getProductState().equals("Hazırlanıyor"))
            holder.preparingLayout.setVisibility(View.VISIBLE);
        else if (order.getProductState().equals("Yolda"))
            holder.inCargoLayout.setVisibility(View.VISIBLE);
        else if (order.getProductState().equals("Onay Bekliyor"))
            holder.waitApprovalLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public void addList(List<OrderModel> data) {
        orderList.addAll(data);
        notifyDataSetChanged();
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position, OrderModel data);
    }
}