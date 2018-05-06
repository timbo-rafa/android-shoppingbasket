package com.rafaeltimbo.shoppingbasket;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

        ImageView imageiv = findViewById(R.id.productDetailsImage);
        //final TextView idtv = findViewById(R.id.productDetailsOrderId);
        TextView orderCustomertv;// = findViewById(R.id.productDetailsCustomer);
        TextView datetv = findViewById(R.id.productDetailsOrderDate);
        final TextView statustv = findViewById(R.id.productDetailsStatus);
        TextView pricetv = findViewById(R.id.productDetailsPrice);
        TextView producttv = findViewById(R.id.productDetailsName);
        TextView categorytv = findViewById(R.id.productDetailsCategory);

        TextView currentQuantitytv = findViewById(R.id.productDetailsCurrentQuantityTag);
        TextView quantitytv = findViewById(R.id.productDetailsQuantity);
        TextView newQuantityTagtv = findViewById(R.id.productDetailsNewQuantityTag);
        TextView inputQuantityTagtv = findViewById(R.id.productDetailsNewQuantity);

        Button addToCartBtn = findViewById(R.id.addToCartBtn);
        addToCartBtn.setVisibility(View.GONE);

        orderCustomertv = categorytv;

        //idtv.setText(order.id);
        orderCustomertv.setText(customer.fullName());
        datetv.setText(order.orderDate);
        statustv.setVisibility(View.VISIBLE);
        statustv.setText(order.status.toString());
        statustv.setTypeface(null, Typeface.BOLD);
        pricetv.setText(String.format(Locale.CANADA,"$%5.2f", order.price * order.quantity));
        producttv.setText(order.id + " - " + product.name);

        //differ from default ProductDetails screen
        currentQuantitytv.setText(getString(R.string.amountPurchased));
        quantitytv.setText(order.quantity.toString());
        newQuantityTagtv.setVisibility(View.GONE);
        inputQuantityTagtv.setVisibility(View.GONE);
        //idtv.setVisibility(View.VISIBLE);
        orderCustomertv.setVisibility(View.VISIBLE);

        ProgressBar spinningWheel = findViewById(R.id.productDetailsProgressBar);
        new ImageDownloader(getApplicationContext()).download(imageiv, spinningWheel, product.imageUrl);

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
            cancelOrderBtn.setText(getResources().getString(R.string.itemDeliveredUserInfo));
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
