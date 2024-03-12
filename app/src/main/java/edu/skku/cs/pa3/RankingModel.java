package edu.skku.cs.pa3;

import java.util.ArrayList;

public class RankingModel {
    private ArrayList<RecordData> items = new ArrayList<RecordData>();

    public ArrayList<RecordData> getItems() {
        return items;
    }

    public void setItems(int idx, RecordData value) {
        items.set(idx, value);
    }

    public void addItems(RecordData data){
        items.add(data);
    }
}
