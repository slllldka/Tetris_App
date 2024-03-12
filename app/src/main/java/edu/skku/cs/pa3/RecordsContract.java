package edu.skku.cs.pa3;

public interface RecordsContract {
    interface View{
        public void UpdateListview(ListViewAdapter adapter);
        public void Update_tv_records(String id);
    }

    interface Presenter{
        public void Start();
    }
}
