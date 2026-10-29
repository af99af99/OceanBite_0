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

public class SeafoodFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MainModel> list;
    MainAdapter adapter;
    ImageView bgImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        bgImage = view.findViewById(R.id.bgImage);
        bgImage.setImageResource(R.drawable.seafoodback11);

        recyclerView = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        
        list.add(new MainModel(R.drawable.sea3, "Garlic Butter Crab", "750", "Grilled chicken with vegetables"));
        list.add(new MainModel(R.drawable.sea6, "Lobster Thermidor", "450", "Egg fried rice with chicken"));
        list.add(new MainModel(R.drawable.sea4, "Spicy Chili Shrimp", "750", "Grilled chicken with vegetables"));
        list.add(new MainModel(R.drawable.sea5, "Grilled Scallops with Lemon Butter", "850", "Pasta with shrimps and sauce"));
        list.add(new MainModel(R.drawable.sea7, "Creamy Garlic Mussels", "900", "Grilled fish with lemon"));
        list.add(new MainModel(R.drawable.sea5, "Oyster Rockefeller", "850", "Pasta with shrimps and sauce"));
        list.add(new MainModel(R.drawable.sea7, "Calamari Fritti", "900", "Grilled fish with lemon"));
        list.add(new MainModel(R.drawable.sea6, "Seafood Paella", "450", "Egg fried rice with chicken"));
        list.add(new MainModel(R.drawable.sea4, "Butter Garlic Lobster Tail", "750", "Grilled chicken with vegetables"));

        // Adapter setup
        adapter = new MainAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
