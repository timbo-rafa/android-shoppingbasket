package com.example.rafael.onlineShopping;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ListProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Product.setDb(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_products);

        final Button placeOrderBtn = findViewById(R.id.placeOrderBtn);

        placeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent listOrders = new Intent(ListProductsActivity.this, ListOrdersActivity.class);

            if (placeOrder()) startActivity(listOrders);
            }
        });

    }

    public boolean placeOrder() {
         ProductFragmentList productFragmentList = (ProductFragmentList) getFragmentManager().findFragmentById(R.id.fragment);
         ListAdapter productList =  productFragmentList.getListAdapter();
         Log.d("timbo.fl", productFragmentList.toString());

         ArrayList<String> orders = new ArrayList<String>();
         String username = getSharedPreferences("Login", MODE_PRIVATE).getString("username", null);
         if (username == null) {
             throw new RuntimeException("Username null at placeOrder at ListProductsActivity");
         }

        Log.d("rafael.timbo.username", username);

        for (int i = 0; i < productList.getCount() ; i++) {
            Product product = (Product) productList.getItem(i);
            Log.d("rafael.timbo.product", product.toString());

            if (product.quantity > 0) {
                orders.add (
                    (String) new Order(product, username).addToDatabase().id
                );
            }
        }

        if (orders.size() > 0) return true;
        else {
            Toast.makeText(getBaseContext(), "No Products on Cart!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

}
