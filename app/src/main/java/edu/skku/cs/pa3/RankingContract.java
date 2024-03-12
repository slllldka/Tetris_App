package edu.skku.cs.pa3;

public interface RankingContract {
    interface View{
        public void UpdateListview(ListViewAdapter2 adapter);
    }

    interface Presenter{
        public void Start();
    }
}
