package edu.skku.cs.pa3;

public interface MainContract {
    interface View{
        public void MakeToast(String msg, int length);
    }

    interface Presenter{
        public void btn_register_pressed(String id, String pw);
        public void btn_start_pressed(String id, String pw);
    }
}
