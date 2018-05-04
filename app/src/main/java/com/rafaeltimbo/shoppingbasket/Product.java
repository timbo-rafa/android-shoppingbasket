package com.rafaeltimbo.shoppingbasket;

import android.content.ContentValues;
import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Product implements Serializable {
    public String id;
    public String name;
    public Double price;
    public Integer quantity;
    public String category;
    public String imageUrl;

    private static DatabaseManager db;
    public static final String tbl_product_fields[] = {
            "productId", "name", "price", "quantity", "category", "imageUrl"
    };
    public static final int size = tbl_product_fields.length;

    public Product(String id, String name, Double price, Integer quantity, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Product(List record) {
        this(record.get(0).toString(),
             record.get(1).toString(),
             Double.valueOf(record.get(2).toString()),
             Integer.valueOf(record.get(3).toString()),
             record.get(4).toString(),
             record.get(5).toString());
    }

    public static void setDb(Context context) {
        db = new DatabaseManager(context);
        initProducts();
    }

    @Override
    public String toString() {
        return  "Product.toString()>> id: " + this.id + ", name: " + this.name +
                ", category: " + this.category +
                ", price: " + this.price +
                ", quantity: " + this.quantity +
                ", imageUrl: " + this.imageUrl;
    }

    public static void initProducts() {
        db.recreateOneTable(DatabaseManager.PRODUCT);
        new Product( "1", "Bananas", 0.59, 0, "Fruits",
            "http://natureandnutrition.com/wp-content/uploads/2015/04/Health-Benefits-of-Bananas.jpg")
            .addToDatabase();
        new Product( "2", "Samsung Galaxy S9 64GB", 719.99, 0, "Phones",
            "https://target.scene7.com/is/image/Target/53438666")
            .addToDatabase();
        new Product( "3", "Logitech Wireless Keyboard (K360) - Black", 29.99, 0, "Computer Accessories",
            "https://multimedia.bbycastatic.ca/multimedia/products/500x500/102/10201/10201435.jpg")
            .addToDatabase();
        new Product( "4", "Logitech M720 Triathlon Wireless Optical Mouse - Black", 49.99, 0, "Computer Accessories",
            "https://multimedia.bbycastatic.ca/multimedia/products/1500x1500/104/10482/10482681.jpg")
            .addToDatabase();
        new Product("5", "Google Home Mini - Charcoal", 79.99, 0, "Smart Home",
            "https://multimedia.bbycastatic.ca/multimedia/products/500x500/116/11615/11615336.jpg")
            .addToDatabase();
    }

    private String[] getRecord() {
        return new String[] {
            this.id,
            this.name,
            String.valueOf(this.price),
            String.valueOf(this.quantity),
            this.category,
            this.imageUrl
        };
    }

    public static Product queryProductByName(String name) {
        List productList = db.queryTable(DatabaseManager.tables[DatabaseManager.PRODUCT], "name = ?", new String[]{ name } );
        return new Product(
            (List) productList.get(0)
        );
    }

    public void addToDatabase() {
        db.addRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.PRODUCT], tbl_product_fields, this.getRecord() );
    }

    public void updateDatabase() {
        db.updateRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.PRODUCT], tbl_product_fields,  this.getRecord() );
    }

    public static ArrayList<Product> fetchProducts() {
        List productList = db.getTable(DatabaseManager.tables[DatabaseManager.PRODUCT]);
        ArrayList<Product> productArrayList = new ArrayList<Product>();
        for (int i = 0; i < productList.size() ; i++) {
            List l = (List) productList.get(i);
            productArrayList.add(new Product(
                l.get(Product.size * i).toString(),
                l.get(Product.size * i + 1).toString(),
                Double.valueOf(l.get(Product.size * i + 2).toString()),
                Integer.valueOf(l.get(Product.size * i + 3).toString()),
                l.get(Product.size * i + 4).toString(),
                l.get(Product.size * i + 5).toString()
            ));
            /*
            for (int j = 0; j < Product.size ; j++) {
                //Log.d("timbo.j " + j, l.get(j).toString());
                Log.d("timbo result", l.get(Product.size * i + j).toString());
            }
            Log.d("timbo.fetchProducts " + i, productList.get(i).toString());
            //productArrayList.add((Product) productList.get(i));
            */
        }
        return productArrayList;
        //return new ArrayList<Product>();
    }
}
