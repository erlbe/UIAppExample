package com.example.telematica.uiappexample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telematica.uiappexample.MainView;
import com.example.telematica.uiappexample.R;
import com.example.telematica.uiappexample.presenters.MainPresenterImpl;
import com.example.telematica.uiappexample.presenters.contracts.MainPresenter;

/**
 * Created by Erlend on 18.11.2016.
 */

public class MainFragment extends Fragment implements MainView {
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView loadingText;

    private MainPresenter mPresenter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        loadingText = (TextView) rootView.findViewById(R.id.loadingText);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter = new MainPresenterImpl(this.getActivity(), this);
        mPresenter.addListElements(mRecyclerView);

        return rootView;
    }

    @Override
    public void loadingDone() {
        loadingText.setText("");
        Toast.makeText(getActivity(), "Loading done!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void isLoading() {
        loadingText.setText("Loading...");
    }
}
