package edu.skku.cs.pa3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.text.SimpleDateFormat;

public class GameActivity extends AppCompatActivity implements GameContract.View{

    private TextView tv_game_score;
    private ImageButton ibtn_p_r, ibtn_close;
    private ImageView iv_hold;
    private Button btn_hold, btn_left, btn_right, btn_rotate, btn_softdrop, btn_harddrop;
    private GridView gridview;

    private GameContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tv_game_score = findViewById(R.id.textview_game_score);
        ibtn_p_r = findViewById(R.id.imagebutton_p_r);
        ibtn_close = findViewById(R.id.imagebutton_close);
        iv_hold = findViewById(R.id.imageview_hold);
        btn_hold = findViewById(R.id.button_hold);
        btn_left = findViewById(R.id.button_left);
        btn_right = findViewById(R.id.button_right);
        btn_rotate = findViewById(R.id.button_rotate);
        btn_softdrop = findViewById(R.id.button_softdrop);
        btn_harddrop = findViewById(R.id.button_harddrop);
        gridview = findViewById(R.id.gridview1);

        presenter = new GamePresenter(this, GameActivity.this);

        ibtn_p_r.setOnClickListener(view -> {
            presenter.ibtn_p_r_pressed();
        });

        ibtn_close.setOnClickListener(view -> {
            GameActivity.this.finish();
        });

        btn_hold.setOnClickListener(view -> {
            presenter.btn_hold_pressed();
        });
        btn_left.setOnClickListener(view -> {
            presenter.btn_left_pressed();
        });
        btn_right.setOnClickListener(view -> {
            presenter.btn_right_pressed();
        });
        btn_rotate.setOnClickListener(view -> {
            presenter.btn_rotate_pressed();
        });
        btn_softdrop.setOnClickListener(view -> {
            presenter.btn_softdrop_pressed();
        });
        btn_harddrop.setOnClickListener(view -> {
            presenter.btn_harddrop_pressed();
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ibtn_p_r.getTag().toString().equals("run")) {
            presenter.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void UpdateGridview(GridViewAdapter adapter) {
        GameActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                gridview.setAdapter(adapter);
            }
        });
    }

    @Override
    public void UpdateScore(int score) {
        GameActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_game_score.setText("Score: "+score);
            }
        });
    }

    @Override
    public void UpdatePauseResume(int resId, String tag) {
        GameActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ibtn_p_r.setImageResource(resId);
                ibtn_p_r.setTag(tag);
            }
        });
    }

    @Override
    public void UpdateHoldImage(int resId) {
        GameActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                iv_hold.setImageResource(resId);
            }
        });
    }

    @Override
    public void MakeToast(String msg, int length) {
        GameActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, length).show();
            }
        });
    }
}