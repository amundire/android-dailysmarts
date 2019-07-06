package com.example.daily_smarts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.daily_smarts.adapters.ViewPagerAdapter;
import com.example.daily_smarts.databinding.ActivityMainBinding;
import com.example.daily_smarts.fragments.DailyQuoteFragment;
import com.example.daily_smarts.fragments.MyQuotesFragment;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements MyQuotesFragment.OnFragmentInteractionListener, DailyQuoteFragment.OnFragmentInteractionListener {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initToolbar();
        initViewPager();
        checkInternetConnection();
    }


    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(R.string.app_name);
    }

    private void initViewPager() {
        binding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), this));
        binding.tabLayout.setupWithViewPager(binding.viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void checkInternetConnection(){
        if(!hasInternetConnection()){
            Snackbar snackbar = Snackbar
                    .make(binding.coordinatorLayout, getResources().getString(R.string.no_internet), Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }
    private boolean hasInternetConnection() {
        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return isConnected = true;
        } else {
            return isConnected = false;
        }
    }
}