package com.example.foodorderapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FoodPagerAdapter extends FragmentStateAdapter {

    public FoodPagerAdapter(@NonNull FragmentActivity fa) {
        super(fa);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new SeafoodFragment();
            case 1:
                return new DessertFragment();
            case 2:
                return new DrinksFragment();
            default:
                return new SeafoodFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // 3 tabs
    }
}
