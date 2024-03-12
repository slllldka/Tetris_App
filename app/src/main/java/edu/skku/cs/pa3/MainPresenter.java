package edu.skku.cs.pa3;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View view;
    private MainModel model;

    private Context mcontext;

    public MainPresenter(MainContract.View view, Context mcontext){
        this.view = view;
        model = new MainModel();
        this.mcontext = mcontext;
    }

    @Override
    public void btn_register_pressed(String id, String pw) {
        String json;
        json = "{" + "\"name\":\""+id+"\",\"passwd\":\""+pw+"\"}";

    /*    OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://sfbsl3u7sj.execute-api.ap-northeast-2.amazonaws.com/pa3/adduser").newBuilder();
        String url = urlBuilder.build().toString();

        Request req = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), json)).build();

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myResponse = response.body().string();
                Gson gson = new GsonBuilder().create();
                SuccessFailData data = gson.fromJson(myResponse, SuccessFailData.class);

                if(data.getSuccess()){
                    view.MakeToast("Success", Toast.LENGTH_SHORT);
                }
                else{
                    view.MakeToast("Already Existing ID", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
        });*/
    }

    @Override
    public void btn_start_pressed(String id, String pw) {
        String json;
        json = "{" + "\"name\":\""+id+"\",\"passwd\":\""+pw+"\"}";

    /*    OkHttpClient client = new OkHttpClient();

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://sfbsl3u7sj.execute-api.ap-northeast-2.amazonaws.com/pa3/login").newBuilder();
        String url = urlBuilder.build().toString();

        Request req = new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"), json)).build();

        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String myResponse = response.body().string();
                Gson gson = new GsonBuilder().create();
                SuccessFailData data = gson.fromJson(myResponse, SuccessFailData.class);

                if(data.getSuccess()){
                    Intent intent = new Intent(mcontext, LobbyActivity.class);
                    intent.putExtra(MainModel.EXT_ID, id);
                    mcontext.startActivity(intent);
                }
                else{
                    view.MakeToast("Wrong ID or Password", Toast.LENGTH_SHORT);
                }
            }

            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
        });*/

        Intent intent = new Intent(mcontext, LobbyActivity.class);
        intent.putExtra(MainModel.EXT_ID, id);
        mcontext.startActivity(intent);
    }
}
