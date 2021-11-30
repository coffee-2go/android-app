package com.android.coffee2go;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.android.coffee2go.models.MenuItem;
import com.android.coffee2go.models.OrderLine;
import com.android.coffee2go.viewmodels.MenuVM;

public class MenuItemActivity extends AppCompatActivity {
    TextView itemIcon;
    TextView itemName;
    TextView itemPrice;
    TextView itemQuantity;

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
        itemPrice.setText(item.getUnitPrice()+" DKK");

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
            OrderLine orderLine = new OrderLine(item,quantity);
            menuVM.addOrderLine(orderLine);
        });
    }
}