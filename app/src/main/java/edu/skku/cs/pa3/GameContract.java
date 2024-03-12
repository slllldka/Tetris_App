package edu.skku.cs.pa3;

import java.util.ArrayList;

public interface GameContract {
    interface View{
        public void UpdateGridview(GridViewAdapter adapter);
        public void UpdateScore(int score);
        public void UpdatePauseResume(int resId, String tag);
        public void UpdateHoldImage(int resId);
        public void MakeToast(String msg, int length);
    }

    interface Presenter{
        public void SetOrder();
        public void CreateNewTetromino();
        public void HoldTetromino();
        public void Start();
        public ArrayList<Integer> CheckLines();
        public void PushDown(ArrayList<Integer> idx_arr);

        public void ibtn_p_r_pressed();
        public void btn_hold_pressed();
        public void btn_left_pressed();
        public void btn_right_pressed();
        public void btn_rotate_pressed();
        public void btn_softdrop_pressed();
        public void btn_harddrop_pressed();

        public void onStop();
        public void onResume();
        public void onDestroy();
    }
}
