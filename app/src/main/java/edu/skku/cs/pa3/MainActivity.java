package edu.skku.cs.pa3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements MainContract.View{

    private Button btn_register, btn_start;
    private EditText et_id, et_pw;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_register = findViewById(R.id.button_register);
        btn_start = findViewById(R.id.button_start);
        et_id = findViewById(R.id.editText_id);
        et_pw = findViewById(R.id.editText_pw);

        presenter = new MainPresenter(this, MainActivity.this);

        btn_register.setOnClickListener(view -> {
            presenter.btn_register_pressed(et_id.getText().toString(), et_pw.getText().toString());
        });

        btn_start.setOnClickListener(view -> {
            presenter.btn_start_pressed(et_id.getText().toString(), et_pw.getText().toString());
        });
    }

    @Override
    public void MakeToast(String msg, int length) {
        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), msg, length).show();
            }
        });
    }
}