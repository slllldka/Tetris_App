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

public class RankingPresenter implements RankingContract.Presenter{

    private RankingContract.View view;
    private RankingModel model;

    private ListViewAdapter2 adapter;

    public RankingPresenter(RankingContract.View view, Context mcontext){
        this.view = view;
        model = new RankingModel();
        adapter = new ListViewAdapter2(model.getItems(), mcontext);
    }
    @Override
    public void Start() {
    /*    OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://sfbsl3u7sj.execute-api.ap-northeast-2.amazonaws.com/pa3/get/all_records").newBuilder();
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
