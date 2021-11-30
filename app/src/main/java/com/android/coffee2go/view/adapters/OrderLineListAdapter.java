package com.android.coffee2go.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.android.coffee2go.R;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.persistence.TransactionRepository;
import com.android.coffee2go.viewmodels.CartVM;
import com.android.coffee2go.viewmodels.CartVMImpl;
import com.android.coffee2go.viewmodels.OnListItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class OrderLineListAdapter extends RecyclerView.Adapter<OrderLineListAdapter.ViewHolder>{

    private final List<OrderLine> orderLines;
    final private OnListItemClickListener mOnListItemClickListener;
    private CartVM cartVM;

    public OrderLineListAdapter(CartVM cartVM,OnListItemClickListener listener){
        orderLines = new ArrayList<>();
        this.cartVM = cartVM;
        mOnListItemClickListener = listener;

        cartVM.getTransactionOrderLines().observeForever(orders ->{
            if (!orders.isEmpty()){
                orderLines.addAll(orders);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart_item,parent,false);
        return new OrderLineListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderLineListAdapter.ViewHolder holder, int position) {
        OrderLine orderLine = orderLines.get(position);
        holder.orderLineProductName.setText(orderLine.getProduct().getName());
        holder.orderLineQuantity.setText(orderLine.getQuantity() + "x");
        holder.orderLineUnitPrice.setText(String.valueOf(orderLine.getProduct().getUnitPrice()));
        holder.orderLineImage.setBackgroundResource(orderLine.getProduct().getIconId());
    }

    @Override
    public int getItemCount() {
        return orderLines.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView orderLineProductName;
        TextView orderLineQuantity;
        TextView orderLineUnitPrice;

        TextView orderLineImage;

        Button orderLineButtonRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            orderLineProductName = itemView.findViewById(R.id.cartItem_name);
            orderLineQuantity = itemView.findViewById(R.id.cartItem_quantity);
            orderLineUnitPrice = itemView.findViewById(R.id.cartItem_unitPrice);
            orderLineButtonRemove = itemView.findViewById(R.id.cartItem_buttonRemove);
            orderLineImage = itemView.findViewById(R.id.cartItem_icon);

            orderLineButtonRemove.setOnClickListener(c -> {
                cartVM.removeOrder(getAdapterPosition());
            });
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(0,getAdapterPosition());
        }
    }
}