package com.example.daily_smarts.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.daily_smarts.models.local.QuoteViewModel;
import com.example.daily_smarts.models.local.QuoteEntity;
import com.example.daily_smarts.adapters.QuotesAdapter;
import com.example.daily_smarts.adapters.QuotesViewHolder;
import com.example.daily_smarts.R;
import com.example.daily_smarts.databinding.FragmentMyQuotesBinding;

import java.util.List;

public class MyQuotesFragment extends Fragment {
    private FragmentMyQuotesBinding binding;
    private QuoteViewModel quoteViewModel;
    private OnFragmentInteractionListener mListener;

    public MyQuotesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_my_quotes, container, false);

        initRecView();
        return binding.getRoot();
    }

    private void initRecView() {
        binding.recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        final QuotesAdapter adapter = new QuotesAdapter(new QuotesViewHolder.QuotesListener() {
            @Override
            public void onLikeClicked(QuoteEntity quoteEntity) {
                quoteViewModel = ViewModelProviders.of(getActivity()).get(QuoteViewModel.class);
                quoteViewModel.deleteSingle(quoteEntity);
            }

            public void onShareClicked(QuoteEntity quoteEntity) {
                String shareBody = quoteEntity.getQuoteText() + " -- " + quoteEntity.getQuoteAuthor();
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.share_using)));
            }
        });
        binding.recView.setAdapter(adapter);

        quoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel.class);
        quoteViewModel.getAllQuotes().observe(this, new Observer<List<QuoteEntity>>() {
            @Override
            public void onChanged(List<QuoteEntity> quoteEntities) {
                adapter.submitList(quoteEntities);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }


}
