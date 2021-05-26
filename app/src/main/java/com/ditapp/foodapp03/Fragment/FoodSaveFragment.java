package com.ditapp.foodapp03.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ditapp.foodapp03.R;


public class FoodSaveFragment extends Fragment {

    public FoodSaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_food_save, container, false);
        // Inflate the layout for this fragment
        return view;
    }
}