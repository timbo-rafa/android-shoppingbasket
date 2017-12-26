package com.example.rafael.rafaelmatos_MAPD711_OnlinePurchase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent getProductDetails = getIntent();

        final Product product = (Product) getProductDetails.getSerializableExtra("product");
        final int productPosition = getProductDetails.getIntExtra("productPosition", 0);

        Button addToCartBtn = findViewById(R.id.addToCartBtn);

        TextView name = findViewById(R.id.productDetailsName);
        TextView price = findViewById(R.id.productDetailsPrice);
        TextView category = findViewById(R.id.productDetailsCategory);
        TextView quantity = findViewById(R.id.productDetailsQuantity);

        final EditText newQuantity = findViewById(R.id.productDetailsNewQuantity);

        name.setText(product.name);
        category.setText(product.category);
        price.setText(product.price.toString());
        quantity.setText(product.quantity.toString());

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    product.quantity = Integer.valueOf(newQuantity.getText().toString());
                    product.updateDatabase();

                    Intent listProducts = new Intent(ProductDetailsActivity.this, ListProductsActivity.class);
                    startActivity(listProducts);
                } catch (NumberFormatException numberFormatException) {
                    Toast.makeText(getApplicationContext(), "Invalid quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
