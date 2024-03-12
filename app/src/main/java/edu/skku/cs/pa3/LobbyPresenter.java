package edu.skku.cs.pa3;

import android.content.Context;
import android.content.Intent;

public class LobbyPresenter implements LobbyContract.Presenter{

    private LobbyContract.View view;
    private LobbyModel model;

    private Context mcontext;

    public LobbyPresenter(LobbyContract.View view, Context mcontext){
        this.view = view;
        model = new LobbyModel();
        this.mcontext = mcontext;
    }

    @Override
    public void Start(Intent intent) {
        model.setId(intent.getStringExtra(MainModel.EXT_ID));
        view.Update_tv_welcome(LobbyModel.id);
    }

    @Override
    public void btn_start_game_pressed() {
        Intent game_intent = new Intent(mcontext, GameActivity.class);
        mcontext.startActivity(game_intent);
    }

    @Override
    public void btn_records_pressed() {
        Intent records_intent = new Intent(mcontext, RecordsActivity.class);
        mcontext.startActivity(records_intent);
    }

    @Override
    public void btn_ranking_pressed() {
        Intent ranking_intent = new Intent(mcontext, RankingActivity.class);
        mcontext.startActivity(ranking_intent);
    }
}
