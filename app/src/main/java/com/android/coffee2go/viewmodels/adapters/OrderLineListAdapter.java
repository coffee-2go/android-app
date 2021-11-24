package com.android.coffee2go.viewmodels.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.coffee2go.R;
import com.android.coffee2go.models.OrderLine;
import java.util.ArrayList;

public class OrderLineListAdapter extends RecyclerView.Adapter<OrderLineListAdapter.ViewHolder>{

    private final ArrayList<OrderLine> orderLines;

    public OrderLineListAdapter(ArrayList<OrderLine> orderLines){
        this.orderLines = orderLines;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.orderline_item,parent,false);
        return new OrderLineListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderLineListAdapter.ViewHolder holder, int position) {
        OrderLine orderLine = orderLines.get(position);
        holder.orderLineProductName.setText(orderLine.getProduct().getName());
        holder.orderLineQuantity.setText(String.valueOf(orderLine.getQuantity()));
        holder.orderLineUnitPrice.setText(String.valueOf(orderLine.getProduct().getUnitPrice()));
        holder.orderLineTotalPrice.setText(String.valueOf(orderLine.getTotal()));
    }

    @Override
    public int getItemCount() {
        return orderLines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderLineProductName;
        TextView orderLineQuantity;
        TextView orderLineUnitPrice;
        TextView orderLineTotalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderLineProductName = itemView.findViewById(R.id.orderLine_productName);
            orderLineQuantity = itemView.findViewById(R.id.orderLine_Quantity);
            orderLineUnitPrice = itemView.findViewById(R.id.orderLine_UnitPrice);
            orderLineTotalPrice = itemView.findViewById(R.id.orderLine_TotalPrice);
        }
    }
}