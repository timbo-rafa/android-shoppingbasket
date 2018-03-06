package com.example.rafael.onlineShopping;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseManagerTest {
    DatabaseManager db;
    Context appContext;

    @Before
    public void setUp() throws Exception {
        appContext = InstrumentationRegistry.getTargetContext();
        db = new DatabaseManager(appContext);
        Product.setDb(appContext);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void destroyDB() throws Exception {
        db.deleteDatabase(appContext);
        db.createDatabase(appContext);

        List l = db.getTable(DatabaseManager.tables[DatabaseManager.CUSTOMER]);
        Log.e("timbo.test", l.toString());
        assertTrue(l.isEmpty());
    }

    @Test
    public void getTableTest() throws Exception {
        List l = db.getTable(DatabaseManager.tables[DatabaseManager.CUSTOMER]);
        assertFalse( l.isEmpty());
    }
}