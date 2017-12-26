package com.example.rafael.rafaelmatos_MAPD711_OnlinePurchase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListOrdersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_orders);


        final Button listProducts = findViewById(R.id.viewProductsBtn);

        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        final boolean isClerk = preferences.getBoolean("isClerk", false);

        if (isClerk) listProducts.setVisibility(View.GONE);
        listProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listProducts = new Intent(ListOrdersActivity.this, ListProductsActivity.class);
                startActivity(listProducts);
            }
        });
    }
}
