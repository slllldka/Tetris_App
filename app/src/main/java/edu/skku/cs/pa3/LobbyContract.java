package edu.skku.cs.pa3;

import android.content.Intent;

public interface LobbyContract {
    interface View{
        public void Update_tv_welcome(String id);
    }

    interface Presenter{
        public void Start(Intent intent);
        public void btn_start_game_pressed();
        public void btn_records_pressed();
        public void btn_ranking_pressed();
    }
}
