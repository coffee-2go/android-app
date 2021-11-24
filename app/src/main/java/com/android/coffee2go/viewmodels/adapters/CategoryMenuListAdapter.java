package com.android.coffee2go.viewmodels.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.viewmodels.OnListItemClickListener;
import java.util.ArrayList;

public class CategoryMenuListAdapter extends RecyclerView.Adapter<CategoryMenuListAdapter.ViewHolder> {
    private ArrayList<MenuItem> items;
    final private OnListItemClickListener mOnListItemClickListener;


    public CategoryMenuListAdapter(ArrayList<MenuItem> items, OnListItemClickListener listener) {
        this.items = items;
        mOnListItemClickListener = listener;
    }

    @NonNull
    @Override
    public CategoryMenuListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.category_item,parent,false);
        return new CategoryMenuListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMenuListAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        holder.itemIcon.setBackgroundResource(items.get(position).getIconId());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemName;
        TextView itemIcon;

        Button buttonAdd;
        Button buttonRemove;
        Button buttonAddToCart;
        TextView viewQuantity;

        int quantityCounter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.categoryItemIcon);
            itemName = itemView.findViewById(R.id.categoryItemName);
            itemView.setOnClickListener(this);

            viewQuantity = itemView.findViewById(R.id.quantity);
            buttonAdd = itemView.findViewById(R.id.buttonAdd);
            buttonAdd.setOnClickListener(v -> {
                ++quantityCounter;
                buttonAddToCart.setEnabled(true);
                viewQuantity.setText(String.valueOf(quantityCounter));
            });

            buttonRemove = itemView.findViewById(R.id.buttonRemove);
            buttonRemove.setOnClickListener(v -> {
                if (quantityCounter > 0){
                    --quantityCounter;
                    viewQuantity.setText(String.valueOf(quantityCounter));
                }
                if (quantityCounter == 0){
                    buttonAddToCart.setEnabled(false);
                }
            });

            buttonAddToCart = itemView.findViewById(R.id.buttonAddToCart);
            if (quantityCounter == 0){
                buttonAddToCart.setEnabled(false);
            }
            buttonAddToCart.setOnClickListener(c -> {
                OrderLine orderLine = new OrderLine(items.get(getAdapterPosition()),quantityCounter);
                Log.i("ADD TO CART BUTTON",orderLine.toString());
            });
        }

        @Override
        public void onClick(View v) {
            mOnListItemClickListener.onListItemClick(getAdapterPosition());
        }
    }
}
