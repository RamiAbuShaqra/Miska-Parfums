package com.example.miskaparfums;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class CategoryFragmentAdapter extends FragmentPagerAdapter {

    /** Context of the app */
    private final Context context;

    public CategoryFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new ProductsFragment();
            case 1: return new OrderFragment();
            case 2: return new SocialContactFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return context.getString(R.string.products);
            case 1: return context.getString(R.string.order);
            case 2: return context.getString(R.string.follow_us);
        }
        return null;
    }
}
