package com.example.rafael.rafaelmatos_MAPD711_OnlinePurchase;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Order {
    public String id;
    public String customerId;
    public String employeeId;
    public String orderDate;
    public Status status;
    public String productId;
    public Double price;
    public Integer quantity;

    private static DatabaseManager db;

    private static final String tbl_order_fields[] = {
        "orderId", "customerId", "productId", "employeeId", "orderDate", "status", "price", "quantity"
    };
    private static final int size = tbl_order_fields.length;

    public Order(String id, String customerId, String productId, String employeeId, String orderDate, String status, Double price, Integer quantity){
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.employeeId = employeeId;
        this.orderDate = orderDate;

        this.status = Status.fromString(status);

        this.price = price;
        this.quantity = quantity;
    }

    public Order(Product p, String username) {
        Log.d("rafael.timbo.Order", username);
        Product product = Product.queryProductByName(p.name);
        User user = User.queryCustomerByUsername(username);
        this.id = "0";
        this.customerId = user.id;
        this.productId = product.id;
        this.employeeId = "0"; // arbitrary employee id
        this.orderDate = Calendar.getInstance().getTime().toString();
        this.status = Status.Processing;
        this.price = product.price;
        this.quantity = product.quantity;

    }

    public static void setDb(Context context) {
        db = new DatabaseManager(context);
        //db.deleteDatabase(context);
        //db.createDatabase(context);
        //initOrders();
    }

    @Override
    public String toString() {
        User customer = User.queryCustomerById(this.customerId);
        return String.format(Locale.CANADA,
                "Order %s by %s: $%.2f (%s)",
                this.id, customer.username, this.price * this.quantity, this.status);
    }

    public String toStringLog() {
        return String.format("id: %s, customerId: %s, productId: %s, employeeId: %s, orderDate: %s, status: %s, quantity: %s, price: %s",
            this.id, this.customerId, this.productId, this.employeeId, this.orderDate, this.status, this.quantity, this.price);
    }

    private String[] getRecord() {
        return new String[] { this.id, this.customerId, this.productId, this.employeeId, this.orderDate,
                              this.status.toString(), this.price.toString(), this.quantity.toString()
        };
    }

    public Order addToDatabase() {
        Log.d("rafael.timbo", "creating order " + db.toString() + this.toString());

        Long id = db.addRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.ORDER], tbl_order_fields, this.getRecord() );
        Log.d("rafael.timbo", "created order " + id.toString());
        this.id = id.toString();
        return this;
    }

    public void cancel() {
        db.deleteRecord(DatabaseManager.tables[DatabaseManager.ORDER], tbl_order_fields[0], this.id );
    }

    public void deliver() {
        this.status = Status.Delivery;
        updateDatabase();
    }

    public void updateDatabase() {
        db.updateRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.ORDER], tbl_order_fields,  this.getRecord() );
    }

    public static Order queryById(String id) {
        List orderList = db.queryTable(DatabaseManager.tables[DatabaseManager.ORDER], "orderId = ?", new String[] { id });
        orderList = convertDbResults(orderList);
        return (Order) orderList.get(0);
    }

    public static ArrayList<Order> fetchOrders(String customerId) {
        List orderList = db.queryTable(DatabaseManager.tables[DatabaseManager.ORDER], "customerId = ?", new String[] { customerId });

        Log.d("rafael.timbo.customerId", orderList.toString());
        return convertDbResults(orderList);
    }

    public static ArrayList<Order> fetchOrders() {
        List orderList = db.getTable(DatabaseManager.tables[DatabaseManager.ORDER]);
        Log.d("rafael.timbo.fetchOrder", orderList.size() + ", " + orderList.toString());
        return convertDbResults(orderList);
    }

    private static ArrayList<Order> convertDbResults(List orderList) {
        ArrayList<Order> orderArrayList = new ArrayList<Order>();
        for (int i = 0; i < orderList.size() ; i++) {
            List l = (List) orderList.get(i);
            orderArrayList.add(new Order(
                l.get(Order.size * i).toString(),
                l.get(Order.size * i + 1).toString(),
                l.get(Order.size * i + 2).toString(),
                l.get(Order.size * i + 3).toString(),
                l.get(Order.size * i + 4).toString(),
                l.get(Order.size * i + 5).toString(),
                Double.valueOf(l.get(Order.size * i + 6).toString()),
                Integer.valueOf(l.get(Order.size * i + 7).toString())
            ));
        }
        return orderArrayList;
    }
}
