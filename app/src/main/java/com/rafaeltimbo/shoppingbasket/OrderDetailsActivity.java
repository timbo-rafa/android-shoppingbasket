package com.rafaeltimbo.shoppingbasket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class OrderDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        String orderId = getIntent().getStringExtra("order");
        final Order order = Order.queryById(orderId);
        User customer = User.queryCustomerById(order.customerId);
        Product product = Product.queryProductById(order.productId);
        SharedPreferences preferences = getSharedPreferences("Login", MODE_PRIVATE);
        boolean isClerk = preferences.getBoolean("isClerk", false);

        final Button deliverBtn = findViewById(R.id.deliverBtn);
        final Button cancelOrderBtn = findViewById(R.id.cancelOrderBtn);

        final TextView idtv = findViewById(R.id.orderId);
        final TextView orderCustomertv = findViewById(R.id.orderCustomer);
        final TextView datetv = findViewById(R.id.orderDate);
        final TextView statustv = findViewById(R.id.orderStatus);
        final TextView pricetv = findViewById(R.id.orderPrice);
        final TextView producttv = findViewById(R.id.orderProduct);


        idtv.setText(order.id);
        orderCustomertv.setText(customer.fullName());
        datetv.setText(order.orderDate);
        statustv.setText(order.status.toString());
        pricetv.setText(String.format(Locale.CANADA,"$%5.2f", order.price * order.quantity));
        producttv.setText(product.name);

        //default
        cancelOrderBtn.setVisibility(View.VISIBLE);
        deliverBtn.setVisibility(View.VISIBLE);

        //reflect system state
        if (isClerk) {
            cancelOrderBtn.setVisibility(View.GONE);
        } else {
            deliverBtn.setVisibility(View.GONE);
        }
        if (order.status == Status.Delivery) {
            deliverBtn.setEnabled(false);
            cancelOrderBtn.setEnabled(false);
        }

        deliverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.deliver();
                deliverBtn.setEnabled(false);
                deliverBtn.setText(R.string.onItsWay);
                statustv.setText(order.status.toString());
            }
        });

        cancelOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                order.cancel();
                cancelOrderBtn.setText(R.string.canceled);
                statustv.setText(R.string.canceled);
                cancelOrderBtn.setEnabled(false);
                Toast.makeText(getApplicationContext(), "Order canceled!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(OrderDetailsActivity.this, ListOrdersActivity.class));
            }
        });

    }
}
