package edu.skku.cs.pa3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RankingActivity extends AppCompatActivity implements RankingContract.View{

    private ListView listview;
    private ListViewAdapter2 adapter;

    private RankingContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        listview = findViewById(R.id.listview2);

        presenter = new RankingPresenter(this, RankingActivity.this);

        presenter.Start();
    }

    @Override
    public void UpdateListview(ListViewAdapter2 adapter) {
        RankingActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listview.setAdapter(adapter);
            }
        });
    }
}