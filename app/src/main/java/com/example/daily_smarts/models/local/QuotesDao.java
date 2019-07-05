package com.example.daily_smarts.models.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuotesDao {
    @Query("SELECT * FROM QuoteEntity")
    LiveData<List<QuoteEntity>> getAll();

    @Query("DELETE FROM QuoteEntity WHERE quote_text = (:quoteText)")
    void deleteQuote(String quoteText);

    @Insert
    void insert(QuoteEntity quoteEntity);

    @Query("SELECT * FROM QuoteEntity WHERE uid = (:id)")
    QuoteEntity getSingleQuote(int id);
}
