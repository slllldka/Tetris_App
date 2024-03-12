package edu.skku.cs.pa3;

import android.content.Context;

import androidx.annotation.NonNull;

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

public class RecordsPresenter implements RecordsContract.Presenter{

    private RecordsContract.View view;
    private RecordsModel model;

    private ListViewAdapter adapter;

    public RecordsPresenter(RecordsContract.View view, Context mcontext){
        this.view = view;
        model = new RecordsModel();
        adapter = new ListViewAdapter(model.getItems(), mcontext);
    }

    @Override
    public void Start() {
        view.Update_tv_records(LobbyModel.id);

    /*    OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://sfbsl3u7sj.execute-api.ap-northeast-2.amazonaws.com/pa3/get/records").newBuilder();
        urlBuilder.addQueryParameter("name", LobbyModel.id);
        String url = urlBuilder.build().toString();

        Request req = new Request.Builder().url(url).build();

        client.newCall(req).enqueue(new Callback(){
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myResponse = response.body().string();

                try {
                    JSONArray jsonArray = new JSONArray(myResponse);
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Gson gson = new GsonBuilder().create();
                        RecordData data = gson.fromJson(jsonObject.toString(), RecordData.class);
                        model.addItems(data);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                view.UpdateListview(adapter);
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

        });*/
    }
}
