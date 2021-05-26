package com.ditapp.foodapp03.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ditapp.foodapp03.Activity.LoginActivity;
import com.ditapp.foodapp03.Activity.SettingsActivity;
import com.ditapp.foodapp03.Adapter.PaperAdapter;
import com.ditapp.foodapp03.Models.UserModel;
import com.ditapp.foodapp03.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;


public class MeFragment extends Fragment {

    private MaterialToolbar toolbar;
    private TabLayout tableLayout;
    private TextView mTitle;
    private ImageView imgUser;
    private TabItem tabItemSave, tabItemMe;
    private ViewPager viewPager;
    private SharedPreferences shp;
    private SharedPreferences.Editor shpEditor;

    public MeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_me,container,false);


        toolbar = (MaterialToolbar) view.findViewById(R.id.toolbar);
        imgUser= view.findViewById(R.id.imgUser);

        tableLayout = view.findViewById(R.id.tabLayout);
        tabItemSave = view.findViewById(R.id.food_save_tab);
        tabItemMe = view.findViewById(R.id.food_me_tab);
        viewPager = view.findViewById(R.id.viewPager);



        PagerAdapter pagerAdapter = new PaperAdapter(
               getChildFragmentManager(), tableLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);
        tableLayout.setupWithViewPager(viewPager);
        tableLayout.addOnTabSelectedListener(tabSelectedListener);

        ((AppCompatActivity) getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        shp = this.getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        CheckLogin();
        // Inflate the layout for this fragment
        return view;
    }

    private final TabLayout.OnTabSelectedListener tabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            viewPager.setCurrentItem(tab.getPosition());
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_me_frag, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.logout_item:  {
                Logout();
                Toast.makeText(getContext(), "Logout", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.seting_item:  {
                Intent i = new Intent(this.getActivity(), SettingsActivity.class);
                startActivity(i);
                return true;
            }
            case R.id.about_item: {
                // save profile changes
                Toast.makeText(getContext(), "About", Toast.LENGTH_SHORT).show();
                return true;
            }
            case R.id.help_item: {
                // save profile changes
                Toast.makeText(getContext(), "Help", Toast.LENGTH_SHORT).show();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void CheckLogin() {
        if (shp == null) shp = this.getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = shp.getString("DataUser", "");
        UserModel dataObj = gson.fromJson(json, UserModel.class);
        String token = shp.getString("token", "");

        if (token != null && !token.equals("")) {
            mTitle.setText(dataObj.getData().get(0).getName());
            Log.d("IMAGE", dataObj.getData().get(0).getAvt());
            RequestOptions requestOptions=new RequestOptions();
            requestOptions.error(R.color.md_red_200);
            Glide.with(MeFragment.this)
                    .load(dataObj.getData().get(0).getAvt())
                    .apply(requestOptions)
                    .into(imgUser);
        } else
        {
            Intent i = new Intent(this.getActivity(), LoginActivity.class);
            startActivity(i);
            this.getActivity().finish();
        }
    }

    public void Logout() {
        try {
            if (shp == null)
                shp = this.getActivity().getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

            shpEditor = shp.edit();
            shpEditor.putString("DataUser", "");
            shpEditor.putString("token", "");
            shpEditor.apply();

            Intent i = new Intent(this.getActivity(), LoginActivity.class);
            startActivity(i);
            this.getActivity().finish();

        } catch (Exception ex) {
            Toast.makeText(this.getActivity(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}