package com.example.feth.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.feth.R;

public class ChapterAdapter extends BaseAdapter {
    private String[] listChapter;
    private Context context;
    private int resource;

    public ChapterAdapter(String[] listChapter, Context context, int resource) {
        this.listChapter = listChapter;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return listChapter.length;
    }

    @Override
    public Object getItem(int position) {
        return listChapter[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name=listChapter[position];
        ChapterAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new ChapterAdapter.ViewHolder();
            vh1.note_title = convertView.findViewById(R.id.note_title);
            convertView.setTag(vh1);
        }
        else {
            vh1 = (ChapterAdapter.ViewHolder) convertView.getTag();
        }
        vh1.note_title.setText(name);
        return convertView;
    }
    public static class ViewHolder {
        TextView note_title;
    }
}
