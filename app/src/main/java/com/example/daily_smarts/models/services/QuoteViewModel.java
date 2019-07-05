package com.example.daily_smarts.models.services;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteViewModel extends AndroidViewModel {

    private static final String base_url = "http://api.forismatic.com/api/1.0/";
    private MutableLiveData<QuoteModel> singleQuote;

    private static QuoteViewModel quoteService;

    public QuoteViewModel(@NonNull Application application) {
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

        QuoteApi api = retrofit.create(QuoteApi.class);
        Call<QuoteModel> call = api.getQuote();


        call.enqueue(new Callback<QuoteModel>() {
            @Override
            public void onResponse(Call<QuoteModel> call, Response<QuoteModel> response) {
                singleQuote.setValue(response.body());
            }

            @Override
            public void onFailure(Call<QuoteModel> call, Throwable t) {

            }
        });
    }
}

