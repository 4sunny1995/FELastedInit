package com.example.feth.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.feth.QuizActivity;
import com.example.feth.R;

import model.Question;
import model.Role;

public class RoleListAdapter extends BaseAdapter {
    private Role[] roles;
    private Context context;
    private int resource;

    public RoleListAdapter(Role[] listChapter, Context context, int resource) {
        this.roles = listChapter;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return roles.length;
    }

    @Override
    public Object getItem(int position) {
        return roles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Role role = roles[position];
        RoleListAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new RoleListAdapter.ViewHolder();
            vh1.lblSTTRole = convertView.findViewById(R.id.lblSTTRole);
            vh1.lblIDRole = convertView.findViewById(R.id.lblIDRole);
            vh1.lblNameRole = convertView.findViewById(R.id.lblNameRole);
            convertView.setTag(vh1);
        }
        else {
            vh1 = (RoleListAdapter.ViewHolder) convertView.getTag();
        }
        vh1.lblSTTRole.setText(position+1+"");
        vh1.lblIDRole.setText(role.getId());
        vh1.lblNameRole.setText(role.getTitle());
        return convertView;
    }
    public static class ViewHolder {
        TextView lblSTTRole;
        TextView lblIDRole;
        TextView lblNameRole;
    }
}
