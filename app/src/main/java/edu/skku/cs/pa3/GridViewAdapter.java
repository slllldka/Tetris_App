package edu.skku.cs.pa3;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {

    private ArrayList<String> items;
    private Context mContext;

    public GridViewAdapter(Context _mContext){
        items = GameModel.items;
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
            view = layoutInflater.inflate(R.layout.gridview_element_layout, viewGroup, false);
        }

        String color = items.get(i);
        ImageView iv_cell = view.findViewById(R.id.imageview_cell);
        ConstraintLayout cl = view.findViewById(R.id.gridview_layout);
        if(i < 20 && color.equals(GameModel.black)){
            cl.setBackgroundColor(Color.parseColor("#FFFFFF"));
            iv_cell.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else {
            iv_cell.setBackgroundColor(Color.parseColor(color));
        }

        return view;
    }
}
