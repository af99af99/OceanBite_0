package com.example.foodorderapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodorderapp.Adapters.OrderAdapter;
import com.example.foodorderapp.Models.OrdersModel;
import com.example.foodorderapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {

    ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        DBhelper helper = new DBhelper(this);
        ArrayList<OrdersModel> list = helper.getOrders();

        OrderAdapter adapter = new OrderAdapter(list, this);
        binding.orderRecyclerView.setAdapter(adapter);
        binding.orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Checkout button click: go to PaymentActivity
        binding.paymentButton.setOnClickListener(v -> {
            Intent intent = new Intent(OrderActivity.this, PaymentActivity.class);
            startActivity(intent);
        });
    }
}
