package com.android.coffee2go.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.viewmodels.MenuVM;
import com.android.coffee2go.viewmodels.OnListItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class MainMenuItemsAdapter extends RecyclerView.Adapter<MainMenuItemsAdapter.ViewHolder>{
    private ArrayList<MenuItem> items;
    final private OnListItemClickListener OnListItemClickListener;
    private final MenuVM menuVM;

    public MainMenuItemsAdapter(com.android.coffee2go.viewmodels.OnListItemClickListener onListItemClickListener, MenuVM menuVM) {
        OnListItemClickListener = onListItemClickListener;
        this.menuVM = menuVM;
        items = new ArrayList<>();

    }

    @NonNull
    @Override
    public MainMenuItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_menu_items,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuItemsAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        holder.itemIcon.setBackgroundResource(items.get(position).getIconId());
        holder.itemPrice.setText(items.get(position).getUnitPrice() +" DKK");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItemsToShow(List<MenuItem> menuItems) {
        items = (ArrayList<MenuItem>) menuItems;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView itemName;
        TextView itemIcon;
        TextView itemPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.main_menu_item_icon);
            itemName = itemView.findViewById(R.id.main_menu_item_name);
            itemPrice = itemView.findViewById(R.id.main_menu_item_price);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnListItemClickListener.onListItemClick(1, getAdapterPosition());
        }
    }
}
