package com.example.telematica.uiappexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telematica.uiappexample.connection.HttpServerConnection;
import com.example.telematica.uiappexample.models.Libro;
import com.example.telematica.uiappexample.presenters.MainPresenterImpl;
import com.example.telematica.uiappexample.presenters.contracts.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView loadingText;

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadingText = (TextView) findViewById(R.id.loadingText);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mPresenter = new MainPresenterImpl(this, this);
        mPresenter.addListElements(mRecyclerView);
    }

    @Override
    public void isLoading() {
        loadingText.setText("Loading...");
    }

    @Override
    public void loadingDone() {
        loadingText.setText("");
        Toast.makeText(this, "Loading done!", Toast.LENGTH_LONG).show();
    }


}
