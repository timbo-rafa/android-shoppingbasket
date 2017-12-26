package com.example.rafael.rafaelmatos_MAPD711_OnlinePurchase;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class UserTest {
    DatabaseManager db;

    @Before
    public void setUp() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        db = new DatabaseManager(appContext);
        User.setDb(appContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void queryUserByUsername() throws Exception {
        User user = new User("0", "androidtest", "test", "test", "test", "test", "test", "test");
        user.addToDatabaseCustomer();
        User retrievedUser = User.queryCustomerByUsername(user.username);
        assertNotNull(retrievedUser);
        assertEquals(user.username, retrievedUser.username);
    }

    @Test
    public void addToDatabaseClerk() throws Exception {

    }

    @Test
    public void addToDatabaseCustomer() throws Exception {
        User user = new User("0", "test", "test", "test", "test", "test", "test", "test");
        user.addToDatabaseCustomer();
        List l = db.queryTable(DatabaseManager.tables[DatabaseManager.CUSTOMER], "username = ?", new String[] { user.username });
        Log.d("timbo.test", l.toString());
        assertFalse(l.isEmpty());
        assertFalse(( (List) l.get(0)).isEmpty());
    }

}