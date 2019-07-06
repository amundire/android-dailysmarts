package com.example.daily_smarts.models.local;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class QuotesRepository {
    private QuotesDao quotesDao;
    private LiveData<List<QuoteEntity>> allQuotes;


    public QuotesRepository(Application application) {
        QuotesDatabase quotesDatabase = QuotesDatabase.getInstance(application);
        quotesDao = quotesDatabase.quotesDao();
        allQuotes = quotesDao.getAll();
    }

    public void insertSingleAsync(final QuoteEntity quoteEntity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                quotesDao.insert(quoteEntity);
                return null;
            }
        }.execute();
    }

    public void deleteSingleAsync(final QuoteEntity quoteEntity) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                quotesDao.deleteQuote(quoteEntity.getQuoteText());
                return null;
            }
        }.execute();
    }

    public LiveData<List<QuoteEntity>> getAllQuotes(){
        return allQuotes;
    }
}
