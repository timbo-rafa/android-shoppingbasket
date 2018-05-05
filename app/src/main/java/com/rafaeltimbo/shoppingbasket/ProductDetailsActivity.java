package com.rafaeltimbo.shoppingbasket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

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
        ImageView image = findViewById(R.id.productDetailsImage);

        final EditText newQuantity = findViewById(R.id.productDetailsNewQuantity);

        name.setText(product.name);
        category.setText(product.category);
        price.setText(String.format(Locale.CANADA, "$%.2f", product.price));
        quantity.setText(product.quantity.toString());

        new ImageDownloader(getApplicationContext()).download(image, product.imageUrl);

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    product.setQuantity( Integer.valueOf(newQuantity.getText().toString()) );

                    //Intent listProducts = new Intent(ProductDetailsActivity.this, ListProductsActivity.class);
                    onBackPressed();
                    //startActivity(listProducts);
                } catch (NumberFormatException numberFormatException) {
                    Toast.makeText(getApplicationContext(), "Invalid quantity!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
