package edu.skku.cs.pa3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LobbyActivity extends AppCompatActivity implements LobbyContract.View{

    private TextView tv_welcome;
    private Button btn_start_game, btn_records, btn_ranking;

    private LobbyContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        tv_welcome = findViewById(R.id.textview_welcome);
        btn_start_game = findViewById(R.id.btn_start_game);
        btn_records = findViewById(R.id.btn_records);
        btn_ranking = findViewById(R.id.btn_ranking);

        presenter = new LobbyPresenter(this, LobbyActivity.this);

        presenter.Start(getIntent());

        btn_start_game.setOnClickListener(view -> {
            presenter.btn_start_game_pressed();
        });

        btn_records.setOnClickListener(view -> {
            presenter.btn_records_pressed();
        });

        btn_ranking.setOnClickListener(view -> {
            presenter.btn_ranking_pressed();
        });
    }

    @Override
    public void Update_tv_welcome(String id) {
        tv_welcome.setText("Welcome, " + id + "!");
    }
}