package com.example.foodorderapp;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView; 

import com.example.foodorderapp.Adapters.MainAdapter;
import com.example.foodorderapp.Models.MainModel;

import java.util.ArrayList;

public class DessertFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MainModel> list;
    MainAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

       
        ImageView bgImage = view.findViewById(R.id.bgImage);
        bgImage.setImageResource(R.drawable.dessertback1); 

        recyclerView = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        // Dummy dessert data
        list.add(new MainModel(R.drawable.oceandessart, "Chocolate Cake", "500", "Rich chocolate cake with creamy frosting"));
        list.add(new MainModel(R.drawable.oceandessart, "Ice Cream", "300", "Vanilla ice cream with chocolate syrup"));
        list.add(new MainModel(R.drawable.desser4, "Pancakes", "400", "Fluffy pancakes served with honey and berries"));
        list.add(new MainModel(R.drawable.dessart3, "Chocolate Cake", "500", "Rich chocolate cake with creamy frosting"));
        list.add(new MainModel(R.drawable.dessart2, "Ice Cream", "300", "Vanilla ice cream with chocolate syrup"));
        list.add(new MainModel(R.drawable.desser4, "Pancakes", "400", "Fluffy pancakes served with honey and berries"));

        adapter = new MainAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
