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

import model.AnswerFinish;
import model.Question;

public class ListQuestionAdapter extends BaseAdapter {
    private Question[] questions;
    private Context context;
    private int resource;

    public ListQuestionAdapter(Question[] listChapter, Context context, int resource) {
        this.questions = listChapter;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return questions.length;
    }

    @Override
    public Object getItem(int position) {
        return questions[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Question question = questions[position];
        ListQuestionAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new ListQuestionAdapter.ViewHolder();
            vh1.note_title = convertView.findViewById(R.id.text12);
            convertView.setTag(vh1);
        }
        else {
            vh1 = (ListQuestionAdapter.ViewHolder) convertView.getTag();
        }
        vh1.note_title.setText(position+ 1+"");
        if(question.getIs_save() == 1){
            convertView.setBackgroundColor(Color.BLUE);
        }
        QuizActivity.viewCurrent[position] = convertView;
        return convertView;
    }
    public static class ViewHolder {
        TextView note_title;
    }
}
