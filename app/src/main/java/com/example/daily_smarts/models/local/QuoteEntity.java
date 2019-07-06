package com.example.daily_smarts.models.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class QuoteEntity {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "quote_text")
    public String quoteText;

    @ColumnInfo(name = "quote_author")
    public String quoteAuthor;

    @ColumnInfo(name = "favorite_quote")
    private boolean isLiked;

    public QuoteEntity() {
        isLiked = false;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getQuoteText() {
        return quoteText;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public void setQuoteAuthor(String quoteAuthor) {
        this.quoteAuthor = quoteAuthor;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    @Override
    public String toString(){
        return getQuoteText() + "" + getQuoteAuthor();
    }
}

