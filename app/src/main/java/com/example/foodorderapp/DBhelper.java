package com.example.foodorderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBhelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 1;

    public DBhelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE orders (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "name TEXT," +
                        "phone TEXT," +
                        "price INTEGER," +
                        "image INTEGER," +
                        "quantity INTEGER," +
                        "description TEXT," +
                        "foodname TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS orders");
        onCreate(sqLiteDatabase);
    }

    // Insert new order
    public boolean insertOrder(String name, String phone, int price, int image, String desc, String foodname, int quantity) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodname);
        values.put("quantity", quantity);

        long id = database.insert("orders", null, values);
        return id != -1;
    }

    // Get all orders
    public ArrayList<OrdersModel> getOrders() {
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT id, foodname, image, price FROM orders", null);

        if (cursor.moveToFirst()) {
            do {
                OrdersModel model = new OrdersModel(
                        cursor.getInt(2), // image
                        cursor.getString(1), // foodname
                        cursor.getString(3), // price
                        String.valueOf(cursor.getInt(0)) // id
                );
                orders.add(model);
            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();
        return orders;
    }

    // Get order by ID
    public Cursor getOrderById(int id) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM orders WHERE id = ?", new String[]{String.valueOf(id)});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // Update order
    public boolean updateOrder(String name, String phone, int price, int image, String desc, String foodname, int quantity, int id) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", desc);
        values.put("foodname", foodname);
        values.put("quantity", quantity);

        int rows = database.update("orders", values, "id=?", new String[]{String.valueOf(id)});
        return rows > 0;
    }

    // Delete order
    public int deleteOrder(int id) {
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders", "id = ?", new String[]{String.valueOf(id)});
    }
}
