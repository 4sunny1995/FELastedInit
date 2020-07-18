package com.example.feth.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.feth.R;

import model.HistoryItemResult;

public class HistoryExamAdapter extends BaseAdapter {
    private HistoryItemResult[] listChapter;
    private Context context;
    private int resource;

    public HistoryExamAdapter(HistoryItemResult[] listChapter, Context context, int resource) {
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
        HistoryItemResult historyItemResult=listChapter[position];
        HistoryExamAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new HistoryExamAdapter.ViewHolder();
            vh1.lblTenChuongDaLam = convertView.findViewById(R.id.lblTenChuongDaLam);
            vh1.lblDiem = convertView.findViewById(R.id.lblDiem);
            vh1.lblNgay = convertView.findViewById(R.id.lblNgay);
            convertView.setTag(vh1);
        }
        else {
            vh1 = (HistoryExamAdapter.ViewHolder) convertView.getTag();
        }
        vh1.lblTenChuongDaLam.setText(historyItemResult.getChapter_name());
        vh1.lblDiem.setText(historyItemResult.getPercent()+"%");
        vh1.lblNgay.setText(historyItemResult.getCreated_at());
        return convertView;
    }
    public static class ViewHolder {
        TextView lblTenChuongDaLam;
        TextView lblDiem;
        TextView lblNgay;


    }
}
