package com.example.daily_smarts.adapters;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.daily_smarts.R;
import com.example.daily_smarts.fragments.DailyQuoteFragment;
import com.example.daily_smarts.fragments.MyQuotesFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    Context context;
    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new DailyQuoteFragment();
            case 1:
                return new MyQuotesFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return context.getString(R.string.daily_quote);
        }else {
            return context.getString(R.string.my_quotes);
        }
    }
}

