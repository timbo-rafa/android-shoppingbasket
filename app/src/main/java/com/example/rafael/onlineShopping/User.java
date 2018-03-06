package com.example.rafael.onlineShopping;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class User {
    private static DatabaseManager db;
    public static final String tbl_customer_fields[] = {"customerId", "username", "password",
            "firstname", "lastname", "address", "city", "postalCode" };
    public static final String tbl_clerk_fields[] = {"employeeId", "username", "password",
            "firstname", "lastname", "address", "city", "postalCode"};

    protected String id;
    public String username;
    public String password;
    public String firstname;
    public String lastname;
    public String address;
    public String city;
    public String postalCode;

    public User(String id, String username, String password, String firstname, String lastname, String address, String city, String postalCode){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
    }

    private User(List record) {
        this(record.get(0).toString(),
            record.get(1).toString(),
            record.get(2).toString(),
            record.get(3).toString(),
            record.get(4).toString(),
            record.get(5).toString(),
            record.get(6).toString(),
            record.get(7).toString()
        );
    }

    public User(String record[]) {
        this.id = record[0];
        this.username = record[1];
        this.password = record[2];
        this.firstname = record[3];
        this.lastname = record[4];
        this.address = record[5];
        this.city = record[6];
        this.postalCode = record[7];
    }

    public String fullName() {
        return this.firstname + " " + this.lastname;
    }

    protected static User queryUserByUsername(String tbl_name, String username) {
        List userList = db.queryTable(tbl_name, "username = ?", new String[]{ username } );
        Log.d("rafael.timbo.queryUser", userList.toString());
        Log.d("rafael.timbo.Customers", db.getTable(DatabaseManager.tables[DatabaseManager.CUSTOMER]).toString());
        Log.d("rafael.timbo.Clerks", db.getTable(DatabaseManager.tables[DatabaseManager.CLERK]).toString());
        if (userList.isEmpty()) throw new NullPointerException();
        else return new User(
            (List) userList.get(0)
        );
    }

    public static User queryCustomerByUsername(String username) {
        return queryUserByUsername(DatabaseManager.tables[DatabaseManager.CUSTOMER], username);
    }
    public static User queryClerkByUsername(String username) {
        return queryUserByUsername(DatabaseManager.tables[DatabaseManager.CLERK], username);
    }

    public static User queryCustomerById(String id) {
        List userList = db.queryTable(DatabaseManager.tables[DatabaseManager.CUSTOMER], "customerId = ?", new String[] { id });
        Log.d("rafael.timbo.customerId", userList.toString());
        return new User( (List) userList.get(0));
    }

    public static void setDb(Context context) {
        db = new DatabaseManager(context);
    }

    private String[] getRecord() {
        return new String[] { this.id, this.username, this.password, this.firstname, this.lastname, this.address, this.city, this.postalCode };
    }

    public long addToDatabaseClerk() {
        Long id = db.addRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.CLERK], tbl_clerk_fields, this.getRecord());
        Log.d("rafael.timbo", "created clerk " + id.toString());
        return id;
    }

    public long addToDatabaseCustomer() {
        Long id = db.addRecord(new ContentValues(), DatabaseManager.tables[DatabaseManager.CUSTOMER], tbl_customer_fields, this.getRecord());
        Log.d("rafael.timbo", "created customer " + id.toString());
        return id;

    }
}