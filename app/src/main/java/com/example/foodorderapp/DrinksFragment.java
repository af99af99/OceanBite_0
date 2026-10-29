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

public class DrinksFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<MainModel> list;
    MainAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_list, container, false);

        
        ImageView bgImage = view.findViewById(R.id.bgImage);
        bgImage.setImageResource(R.drawable.drinksback2); 

        recyclerView = view.findViewById(R.id.recyclerView);
        list = new ArrayList<>();

        // Dummy drinks data
        list.add(new MainModel(R.drawable.drink1, "Coffee", "150", "Hot brewed coffee with aroma"));
        list.add(new MainModel(R.drawable.drink2,"Orange Juice", "120", "Freshly squeezed orange juice"));
        list.add(new MainModel(R.drawable.drink3, "Tea", "100", "Green tea served hot"));
        list.add(new MainModel(R.drawable.drink4, "Coffee", "150", "Hot brewed coffee with aroma"));
        list.add(new MainModel(R.drawable.drink5,"Orange Juice", "120", "Freshly squeezed orange juice"));
        list.add(new MainModel(R.drawable.drink6, "Tea", "100", "Green tea served hot"));

        adapter = new MainAdapter(list, getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}
