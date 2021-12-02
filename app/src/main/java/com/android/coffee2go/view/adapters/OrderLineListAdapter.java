package com.android.coffee2go.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.coffee2go.R;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.viewmodels.CartVM;
import com.android.coffee2go.viewmodels.OnListItemClickListener;
import java.util.ArrayList;
import java.util.List;


public class OrderLineListAdapter extends RecyclerView.Adapter<OrderLineListAdapter.ViewHolder>{

    private List<OrderLine> orderLines;
    final private OnListItemClickListener mOnListItemClickListener;
    private final CartVM cartVM;

    public OrderLineListAdapter(CartVM cartVM,OnListItemClickListener listener){
        orderLines = new ArrayList<>();
        this.cartVM = cartVM;
        mOnListItemClickListener = listener;
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
        OrderLine cartItem = orderLines.get(position);
        holder.cartItemProductName.setText(cartItem.getProduct().getName());
        holder.cartItemQuantity.setText(cartItem.getQuantity() + "x");
        holder.cartItemImage.setBackgroundResource(cartItem.getProduct().getIconId());
        holder.quantity = cartItem.getQuantity();
        holder.cartItemTotal.setText(cartItem.getTotal()+" DKK");
    }

    @Override
    public int getItemCount() {
        return orderLines.size();
    }


    public void setItemsToShow(List<OrderLine> items) {
        orderLines = items;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cartItemProductName;
        TextView cartItemQuantity;
        TextView cartItemTotal;
        TextView cartItemImage;

        Button cartItemButtonDelete;
        Button cartItemButtonAdd;
        Button cartItemButtonRemove;
        int quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cartItemProductName = itemView.findViewById(R.id.cart_item_name);
            cartItemQuantity = itemView.findViewById(R.id.cart_item_quantity);
            cartItemButtonDelete = itemView.findViewById(R.id.cart_button_delete);
            cartItemImage = itemView.findViewById(R.id.cart_item_icon);
            cartItemTotal = itemView.findViewById(R.id.cart_item_total);
            cartItemButtonDelete.setOnClickListener(c -> cartVM.removeOrder(getAdapterPosition()));

            cartItemButtonAdd = itemView.findViewById(R.id.cart_button_add);
            cartItemButtonAdd.setOnClickListener(v -> {
                quantity++;
                cartVM.changeQuantity(getAdapterPosition(),quantity);
                cartItemQuantity.setText(quantity +"x");
            });

            cartItemButtonRemove = itemView.findViewById(R.id.cart_button_remove);
            cartItemButtonRemove.setOnClickListener(v -> {
                if (quantity > 1){
                    --quantity;
                    cartVM.changeQuantity(getAdapterPosition(),quantity);
                    cartItemQuantity.setText(quantity +"x");
                }
            });
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(0,getAdapterPosition());
        }
    }
}