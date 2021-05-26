package com.ditapp.foodapp03.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.ditapp.foodapp03.Fragment.FoodMeFragment;
import com.ditapp.foodapp03.Fragment.FoodSaveFragment;

import org.jetbrains.annotations.NotNull;

public class PaperAdapter extends FragmentPagerAdapter  {
    private final int numOfTabs;
    private String tabTitles[] = new String[]{"Món ăn đã lưu", "Món ăn của bạn"};

    public PaperAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new FoodSaveFragment();
            case 1:
                return new FoodMeFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {


        return numOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
