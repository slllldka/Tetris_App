package edu.skku.cs.pa3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter2 extends BaseAdapter {
    private ArrayList<RecordData> items;
    private Context mContext;

    public ListViewAdapter2(ArrayList<RecordData> _items, Context _mContext){
        items = _items;
        mContext = _mContext;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.listview_element_layout2, viewGroup, false);
        }

        TextView tv_rank = view.findViewById(R.id.textview_all_rank);
        TextView tv_name = view.findViewById(R.id.textview_all_name);
        TextView tv_score = view.findViewById(R.id.textview_all_score);

        RecordData data = items.get(i);

        int rank = i+1;
        tv_rank.setText(""+rank);
        tv_name.setText(data.getName());
        tv_score.setText(data.getScore());

        if(data.getName().equals(LobbyModel.id)){
            tv_rank.setTypeface(tv_rank.getTypeface(), Typeface.BOLD);
            tv_name.setTypeface(tv_name.getTypeface(), Typeface.BOLD);
            tv_score.setTypeface(tv_score.getTypeface(), Typeface.BOLD);
        }

        return view;
    }

}
