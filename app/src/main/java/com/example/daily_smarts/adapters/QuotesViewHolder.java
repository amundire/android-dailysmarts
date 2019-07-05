package com.example.daily_smarts.adapters;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.daily_smarts.models.local.QuoteEntity;
import com.example.daily_smarts.databinding.QuoteItemBinding;


public class QuotesViewHolder extends RecyclerView.ViewHolder {
    QuoteItemBinding binding;
    QuoteEntity quoteEntity;

    @SuppressLint("NewApi")
    public QuotesViewHolder(final QuoteItemBinding binding, final QuotesListener listener) {
        super(binding.getRoot());
        this.binding = binding;
        binding.btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onLikeClicked(quoteEntity);
            }
        });

        binding.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onShareClicked(quoteEntity);
            }
        });
    }

    public void setQuoteEntity(QuoteEntity quoteEntity) {
        this.quoteEntity = quoteEntity;
    }

    public interface QuotesListener {
        void onLikeClicked(QuoteEntity quoteEntity);

        void onShareClicked(QuoteEntity quoteEntity);
    }
}


