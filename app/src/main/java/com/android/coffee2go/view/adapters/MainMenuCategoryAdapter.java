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

public class MainMenuCategoryAdapter extends RecyclerView.Adapter<MainMenuCategoryAdapter.ViewHolder>{
    private ArrayList<MenuItem> items;
    final private OnListItemClickListener OnListItemClickListener;
    private MenuVM menuVM;

    public MainMenuCategoryAdapter(OnListItemClickListener onListItemClickListener, MenuVM menuVM) {
        OnListItemClickListener = onListItemClickListener;
        this.menuVM = menuVM;

        items = (ArrayList<MenuItem>) menuVM.getCategories();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.viewholder_category_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.categoryItem_Icon);
            itemName = itemView.findViewById(R.id.categoryItem_Name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            OnListItemClickListener.onListItemClick(0,getAdapterPosition());
        }
    }
}
