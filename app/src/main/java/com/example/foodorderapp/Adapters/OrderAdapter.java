package com.example.foodorderapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderapp.DBhelper;
import com.example.foodorderapp.DetailActivity;
import com.example.foodorderapp.Models.OrdersModel;
import com.example.foodorderapp.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    ArrayList<OrdersModel> list;
    Context context;

    public OrderAdapter(ArrayList<OrdersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrdersModel model = list.get(position);

        holder.orderImage.setImageResource(model.getOrderimage());
        holder.orderName.setText(model.getSoldItemName());
        holder.orderPrice.setText("Price: " + model.getPrice() + "tk");
        holder.orderNumber.setText(model.getOrderNumber());

        // Click = Update
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("type", 2);
            intent.putExtra("id", model.getOrderNumber());  // ID পাঠানো
            context.startActivity(intent);
        });

        // Long Press = Delete
        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete Order")
                    .setMessage("Are you sure you want to delete this order?")
                    .setPositiveButton("Yes", (dialogInterface, i) -> {
                        DBhelper helper = new DBhelper(context);
                        int deleted = helper.deleteOrder(Integer.parseInt(model.getOrderNumber()));
                        if (deleted > 0) {
                            Toast.makeText(context, "Deleted ✅", Toast.LENGTH_SHORT).show();
                            list.remove(holder.getAdapterPosition());
                            notifyItemRemoved(holder.getAdapterPosition());
                        } else {
                            Toast.makeText(context, "Failed to delete ❌", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.dismiss())
                    .show();

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView orderName, orderPrice, orderNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderimage);
            orderName = itemView.findViewById(R.id.orderitemname);
            orderPrice = itemView.findViewById(R.id.orderPricetext);
            orderNumber = itemView.findViewById(R.id.orderNumber);
        }
    }
}
