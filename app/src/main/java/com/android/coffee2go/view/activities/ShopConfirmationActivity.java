package com.android.coffee2go.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.coffee2go.R;
import com.android.coffee2go.viewmodels.LocationVMImpl;

import java.util.Calendar;

public class ShopConfirmationActivity extends AppCompatActivity {
    private LocationVMImpl locationVM;
    private TextView shopName;
    private Button confirm_button;
    private Button back_button;
    private TimePicker timePicker;
    private TextView time;
    private Calendar calendar;
    private String format = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_confirmation);
        initComponents();

        locationVM = new ViewModelProvider(this).get(LocationVMImpl.class);

        //Get the name of the cafe from location fragment
        String title = getIntent().getStringExtra("title");
        shopName.append(title);

        //Display the current time initially
        calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        showTime(hour, min);

        //Button actions
        back_button.setOnClickListener(view -> {
            Toast.makeText(getBaseContext(), "Cancelled pickup location",
                    Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        });

        confirm_button.setOnClickListener(view -> {
            Toast.makeText(ShopConfirmationActivity.this, "Confirmed " + shopName.getText() + " at " + time.getText(),
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            //locationVM.setStore(shopName);
        });
    }

    public void showTime(int hour, int min) {
        if (hour == 0) {
            hour += 12;
            format = "AM";
        } else if (hour == 12) {
            format = "PM";
        } else if (hour > 12) {
            hour -= 12;
            format = "PM";
        } else {
            format = "AM";
        }

        time.setText(new StringBuilder().append(hour).append(" : ").append(min)
                .append(" ").append(format));
    }

    public void setTime(View view) {
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        showTime(hour, min);
    }

    public void initComponents() {
        back_button = findViewById(R.id.back_button);
        confirm_button = findViewById(R.id.confirm_button);
        shopName = findViewById(R.id.marker);
        timePicker = findViewById(R.id.time_picker);
        time = findViewById(R.id.timetextview);
    }

}