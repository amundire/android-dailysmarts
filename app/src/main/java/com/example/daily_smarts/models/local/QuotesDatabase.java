package com.example.daily_smarts.models.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {QuoteEntity.class}, version = 2, exportSchema = false)
public abstract class QuotesDatabase extends RoomDatabase {
    private static QuotesDatabase quoteInstance;
    public abstract QuotesDao quotesDao();

    public static synchronized QuotesDatabase getInstance(Context context){
        if(quoteInstance == null){
            quoteInstance = Room.databaseBuilder(context.getApplicationContext(),
                    QuotesDatabase.class, "quotes.db")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return quoteInstance;
    }
}

