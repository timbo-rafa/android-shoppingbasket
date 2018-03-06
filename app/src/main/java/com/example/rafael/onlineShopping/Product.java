package com.example.rafael.onlineShopping;

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

    private static DatabaseManager db;
    public static final String tbl_product_fields[] = {
            "productId", "name", "price", "quantity", "category"
    };
    public static final int size = tbl_product_fields.length;

    public Product(String id, String name, Double price, Integer quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(List record) {
        this(record.get(0).toString(),
             record.get(1).toString(),
             Double.valueOf(record.get(2).toString()),
             Integer.valueOf(record.get(3).toString()),
             record.get(4).toString());
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
                ", quantity: " + this.quantity;
    }

    public static void initProducts() {
        db.recreateOneTable(DatabaseManager.PRODUCT);
        new Product( "1", "Bananas", 0.59, 0, "Fruits").addToDatabase();
        new Product( "2", "Tomatoes", 1.59, 0, "Vegetables").addToDatabase();
        new Product( "3", "Peppers", 1.79, 0, "Vegetables").addToDatabase();
    }

    private String[] getRecord() {
        return new String[] { this.id, this.name, String.valueOf(this.price), String.valueOf(this.quantity), this.category };
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
                l.get(Product.size * i + 4).toString()
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
