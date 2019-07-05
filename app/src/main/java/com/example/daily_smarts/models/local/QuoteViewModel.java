package com.example.daily_smarts.models.local;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.daily_smarts.models.local.QuotesDao;

import java.util.List;

public class QuoteViewModel extends AndroidViewModel {

    private QuotesRepository quotesRepository;
    LiveData<List<QuoteEntity>> allQuotes;
    QuotesDao quotesDao;

    public QuoteViewModel(@NonNull Application application) {
        super(application);
        quotesRepository = new QuotesRepository(application);
        allQuotes = quotesRepository.getAllQuotes();
    }

    public void insertSingle(QuoteEntity quoteEntity){
        quotesRepository.insertSingleAsync(quoteEntity);
    }

    public void deleteSingle(QuoteEntity quoteEntity){
        quotesRepository.deleteSingleAsync(quoteEntity);
    }

    public LiveData<List<QuoteEntity>> getAllQuotes(){
        return allQuotes;
    }
}

