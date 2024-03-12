package edu.skku.cs.pa3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

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

public class RecordsActivity extends AppCompatActivity implements RecordsContract.View{

    private ListView listview;
    private ListViewAdapter adapter;

    private TextView tv_records;

    private RecordsContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);

        listview = findViewById(R.id.listview1);
        tv_records = findViewById(R.id.textview_records);

        presenter = new RecordsPresenter(this, RecordsActivity.this);

        presenter.Start();
    }

    @Override
    public void UpdateListview(ListViewAdapter adapter) {
        RecordsActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listview.setAdapter(adapter);
            }
        });
    }

    @Override
    public void Update_tv_records(String id) {
        tv_records.setText(id+"'s records");
    }
}