package edu.skku.cs.pa3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<RecordData> items;
    private Context mContext;

    public ListViewAdapter(ArrayList<RecordData> _items, Context _mContext){
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
            view = layoutInflater.inflate(R.layout.listview_element_layout, viewGroup, false);
        }

        TextView tv_score = view.findViewById(R.id.textview_score);
        TextView tv_date = view.findViewById(R.id.textview_date);

        RecordData data = items.get(i);

        tv_score.setText(data.getScore());
        tv_date.setText(data.getDate());

        return view;
    }

}
