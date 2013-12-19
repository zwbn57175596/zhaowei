package com.zhaowei.HelloNN;

import java.util.List;

import com.zhaowei.HelloNN.DB.pojo.Fruit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Copyright (C) 2010,Under the supervision of China Telecom Corporation Limited
 * Guangdong Research Institute The New Vphone Project
 * 
 * @Author fonter.yang
 * @Create date：2010-10-11
 * 
 */
public class GridAdapter extends BaseAdapter {

    private class GridHolder {
        ImageView appImage;
        TextView appName;
    }

    private Context context;

    private List<Fruit> list;
    private LayoutInflater mInflater;

    public GridAdapter(Context c) {
        super();
        this.context = c;
    }

    public void setList(List<Fruit> list) {
        this.list = list;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int index) {

        return list.get(index);
    }

    @Override
    public long getItemId(int index) {
        return index;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup parent) {
        GridHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.grid_item, null);
            holder = new GridHolder();
            holder.appImage = (ImageView) convertView.findViewById(R.id.itemImage);
            holder.appName = (TextView) convertView.findViewById(R.id.itemText);
            convertView.setTag(holder);

        } else {
            holder = (GridHolder) convertView.getTag();

        }
        Fruit info = list.get(index);
        if (info != null) {
            holder.appName.setText(info.getName());
            String pinyin = info.getPinyin();
            int res = convertView.getResources()
                    .getIdentifier(pinyin, "drawable", parent.getContext().getPackageName());
            if (res != 0) {
                holder.appImage.setImageResource(res);
            } else {
                holder.appImage.setImageResource(R.drawable.search);
            }
        }
        return convertView;
    }

}
