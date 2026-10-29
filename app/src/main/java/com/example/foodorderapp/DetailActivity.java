package com.example.foodorderapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodorderapp.databinding.ActivityDetailBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;
    DBhelper helper;

    int quantity = 1;
    int pricePerItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        helper = new DBhelper(this);

        int type = getIntent().getIntExtra("type", 1);

        if (type == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final String priceStr = getIntent().getStringExtra("price");
            final String name = getIntent().getStringExtra("name");
            final String desc = getIntent().getStringExtra("desc");

            binding.detailimage.setImageResource(image);
            binding.detailimage.setTag(image);

            pricePerItem = Integer.parseInt(priceStr);
            quantity = 1;
            updateQuantityAndPrice();

            binding.detailname.setText(name);
            binding.fooddesc.setText(desc);

            binding.insertbutton.setText("Place Order");

            binding.insertbutton.setOnClickListener(v -> {
                boolean inserted = helper.insertOrder(
                        binding.namebox.getText().toString().trim(),
                        binding.phonebox.getText().toString().trim(),
                        pricePerItem * quantity,
                        image,
                        desc,
                        name,
                        quantity
                );

                if (inserted) {
                    Toast.makeText(DetailActivity.this, "Order Placed ", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailActivity.this, "Order Failed ", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            String idStr = getIntent().getStringExtra("id");
            if (idStr == null) {
                Toast.makeText(this, "Order ID missing!", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            int id = Integer.parseInt(idStr);
            Cursor cursor = helper.getOrderById(id);

            if (cursor != null && cursor.moveToFirst()) {
                int image = cursor.getInt(cursor.getColumnIndexOrThrow("image"));
                pricePerItem = cursor.getInt(cursor.getColumnIndexOrThrow("price"));
                quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                binding.detailimage.setImageResource(image);
                binding.detailimage.setTag(image);
                binding.detailname.setText(cursor.getString(cursor.getColumnIndexOrThrow("foodname")));
                binding.fooddesc.setText(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                binding.namebox.setText(cursor.getString(cursor.getColumnIndexOrThrow("name")));
                binding.phonebox.setText(cursor.getString(cursor.getColumnIndexOrThrow("phone")));

                updateQuantityAndPrice();

                cursor.close();
            } else {
                Toast.makeText(this, "Order not found!", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }

            binding.insertbutton.setText("Update Order");

            binding.insertbutton.setOnClickListener(v -> {
                boolean updated = helper.updateOrder(
                        binding.namebox.getText().toString().trim(),
                        binding.phonebox.getText().toString().trim(),
                        pricePerItem * quantity,
                        (int) binding.detailimage.getTag(),
                        binding.fooddesc.getText().toString().trim(),
                        binding.detailname.getText().toString().trim(),
                        quantity,
                        id
                );

                if (updated) {
                    Toast.makeText(DetailActivity.this, "Order Updated ", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailActivity.this, "Update Failed ", Toast.LENGTH_SHORT).show();
                }
            });
        }

        binding.addcart.setOnClickListener(v -> {
            quantity++;
            updateQuantityAndPrice();
        });

        binding.subtract.setOnClickListener(v -> {
            if (quantity > 1) {
                quantity--;
                updateQuantityAndPrice();
            }
        });
    }

    private void updateQuantityAndPrice() {
        binding.foodquantity.setText(String.valueOf(quantity));
        int totalPrice = pricePerItem * quantity;
        binding.detailprice.setText(String.valueOf(totalPrice));
    }
}
