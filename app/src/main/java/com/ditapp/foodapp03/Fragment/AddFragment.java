package com.ditapp.foodapp03.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ditapp.foodapp03.Activity.FoodAddActivity;
import com.ditapp.foodapp03.R;


public class AddFragment extends Fragment {

    View inflaterLay;
    Button btnAddFood;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflaterLay = inflater.inflate(R.layout.fragment_add, container, false);
        btnAddFood = inflaterLay.findViewById(R.id.addNewFood);

        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(inflaterLay.getContext(), FoodAddActivity.class);
                startActivity(i);
            }
        });

        // Inflate the layout for this fragment
        return  inflaterLay;
    }
}