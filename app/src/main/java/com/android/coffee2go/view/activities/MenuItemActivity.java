package com.android.coffee2go.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.coffee2go.R;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.viewmodels.MenuVM;

public class MenuItemActivity extends AppCompatActivity {
    TextView itemIcon;
    TextView itemName;
    TextView itemPrice;
    TextView itemQuantity;

    ImageView itemFav;

    Button buttonAdd;
    Button buttonRemove;
    Button buttonAddToCart;

    MenuItem item;

    int quantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_item);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("position");

        MenuVM menuVM = new MenuVM();
        item = menuVM.getItem(position);

        itemIcon = findViewById(R.id.menu_item_icon);
        itemName = findViewById(R.id.menu_item_name);
        itemPrice = findViewById(R.id.menu_item_price);
        itemQuantity = findViewById(R.id.menu_item_quantity);

        itemIcon.setBackgroundResource(item.getIconId());
        itemName.setText(item.getName());
        itemPrice.setText(item.getPrice()+" DKK");

        buttonAdd = findViewById(R.id.menu_item_button_add);
        buttonAdd.setOnClickListener(v -> {
            ++quantity;
            buttonAddToCart.setEnabled(true);
            itemQuantity.setText(String.valueOf(quantity));

        });

        buttonRemove = findViewById(R.id.menu_item_button_remove);
        buttonRemove.setOnClickListener(v -> {
            if (quantity > 0){
                --quantity;
                itemQuantity.setText(String.valueOf(quantity));
            }
            if (quantity == 0){
                buttonAddToCart.setEnabled(false);
            }
        });

        buttonAddToCart = findViewById(R.id.menu_item_button_addToCart);
        if (quantity == 0){
            buttonAddToCart.setEnabled(false);
        }

        buttonAddToCart.setOnClickListener(c -> {
            Toast.makeText(getBaseContext(), "Added "+ itemQuantity.getText() +" "+itemName.getText(),
                    Toast.LENGTH_LONG).show();
            OrderLine orderLine = new OrderLine(item,quantity);
            menuVM.addOrderLine(orderLine);
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        itemFav = findViewById(R.id.menu_item_favourite);
        itemFav.setOnClickListener(c -> {
            itemFav.setImageResource(R.drawable.icon_fav_true);
            String message = "Item '"+item.getName() + "' was added to your favourites";
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
        });
    }
}