package com.example.daily_smarts.models.services;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.daily_smarts.R;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitWrapper extends AndroidViewModel {

    private static final String base_url = "http://api.forismatic.com/api/1.0/";
    private MutableLiveData<QuoteModel> singleQuote;

    private static RetrofitWrapper quoteService;

    public RetrofitWrapper(@NonNull Application application) {
        super(application);
    }

    public LiveData<QuoteModel> getQuotes() {
        singleQuote = new MutableLiveData<>();
        loadQuote();
        return singleQuote;
    }


    private void loadQuote() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitService api = retrofit.create(RetrofitService.class);
        Call<QuoteModel> call = api.getQuote();

        call.enqueue(new Callback<QuoteModel>() {
            @Override
            public void onResponse(Call<QuoteModel> call, Response<QuoteModel> response) {
                if(response.body() != null){
                    singleQuote.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<QuoteModel> call, Throwable t) {
                Log.e("Retrofit", "onFailure: " + t);
            }
        });
    }
}

