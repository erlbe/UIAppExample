package com.example.telematica.uiappexample;

import android.app.FragmentManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telematica.uiappexample.connection.HttpServerConnection;
import com.example.telematica.uiappexample.fragments.MainFragment;
import com.example.telematica.uiappexample.models.Libro;
import com.example.telematica.uiappexample.presenters.MainPresenterImpl;
import com.example.telematica.uiappexample.presenters.contracts.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment = new MainFragment();
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.activity_main, mainFragment).commit();
    }


}
