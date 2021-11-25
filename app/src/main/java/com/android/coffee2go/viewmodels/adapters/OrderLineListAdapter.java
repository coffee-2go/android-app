package com.android.coffee2go.viewmodels.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.coffee2go.R;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.persistence.TransactionRepository;
import com.android.coffee2go.viewmodels.OnListItemClickListener;

import java.util.ArrayList;

/**
 * @author Michal Pupák
 * **/

public class OrderLineListAdapter extends RecyclerView.Adapter<OrderLineListAdapter.ViewHolder>{

    private final ArrayList<OrderLine> orderLines;
    final private OnListItemClickListener mOnListItemClickListener;


    public OrderLineListAdapter(OnListItemClickListener listener){
        this.orderLines = TransactionRepository.getInstance().getTransaction().getOrderLines();
        mOnListItemClickListener = listener;
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
        holder.orderLineQuantity.setText(orderLine.getQuantity() + "x");
        holder.orderLineUnitPrice.setText(orderLine.getProduct().getUnitPrice() + " DKK");
    }

    @Override
    public int getItemCount() {
        return orderLines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderLineProductName;
        TextView orderLineQuantity;
        TextView orderLineUnitPrice;

        Button orderLineButtonRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderLineProductName = itemView.findViewById(R.id.orderLine_productName);
            orderLineQuantity = itemView.findViewById(R.id.orderLine_Quantity);
            orderLineUnitPrice = itemView.findViewById(R.id.orderLine_UnitPrice);
            orderLineButtonRemove = itemView.findViewById(R.id.orderLine_Remove);

            orderLineButtonRemove.setOnClickListener(c -> {
                TransactionRepository.getInstance().getTransaction().getOrderLines()
                        .remove(getAdapterPosition());
            });
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}