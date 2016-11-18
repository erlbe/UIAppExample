package com.example.telematica.uiappexample.presenters;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.example.telematica.uiappexample.MainView;
import com.example.telematica.uiappexample.UIAdapter;
import com.example.telematica.uiappexample.connection.HttpServerConnection;
import com.example.telematica.uiappexample.database.BookDatabase;
import com.example.telematica.uiappexample.models.Libro;
import com.example.telematica.uiappexample.presenters.contracts.MainPresenter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erlend on 17.11.2016.
 */

public class MainPresenterImpl implements MainPresenter {

    private final Activity activity;
    private final MainView mainView;

    private RecyclerView.Adapter mAdapter;

    private BookDatabase bookDatabase;

    public MainPresenterImpl(Activity activity, MainView mainView) {
        this.activity = activity;
        this.mainView = mainView;
        this.bookDatabase = new BookDatabase(activity.getApplicationContext());
    }


    @Override
    public void addListElements(final RecyclerView mRecyclerView) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute(){
                mainView.isLoading();
            }

            @Override
            protected String doInBackground(Void... params) {
                String resultado = new HttpServerConnection().connectToServer("http://www.mocky.io/v2/56990dc51200009e47e25b44", 15000);
                return resultado;
            }

            @Override
            protected void onPostExecute(String result) {
                if(result != null){
                    System.out.println(result);

                    // specify an adapter (see also next example)
                    List<Libro> books = getLista(result);
                    saveToDatabase(books);

                    mAdapter = new UIAdapter(books);
                    mRecyclerView.setAdapter(mAdapter);
                }
                mainView.loadingDone();
            }
        };
        task.execute();
    }

    private List<Libro> getLista(String result){
        List<Libro> listaLibros = new ArrayList<Libro>();
        try {
            JSONArray lista = new JSONArray(result);

            int size = lista.length();
            for(int i = 0; i < size; i++){
                Libro libro = new Libro();
                JSONObject objeto = lista.getJSONObject(i);

                libro.setId(objeto.getInt("id"));
                libro.setNombre(objeto.getString("nombre"));
                libro.setEditorial(objeto.getString("editorial"));
                libro.setGenero(objeto.getString("genero"));
                libro.setAutor(objeto.getInt("autor"));

                listaLibros.add(libro);
            }
            return listaLibros;
        } catch (JSONException e) {
            e.printStackTrace();
            return listaLibros;
        }
    }

    private void saveToDatabase(List<Libro> list){
        SQLiteDatabase db = bookDatabase.getWritableDatabase();
        if(db != null){
            db.beginTransaction();
            try {
                for (Libro book: list){
                    db.execSQL("INSERT INTO books (id, name) VALUES ('" + book.getId() + "', '" + book.getNombre() + "')");
                }
            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }
}
