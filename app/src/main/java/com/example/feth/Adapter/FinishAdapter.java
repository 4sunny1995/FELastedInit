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

import java.util.List;

import model.AnswerFinish;
import model.Question;

public class FinishAdapter extends BaseAdapter {
    private AnswerFinish[] answerFinishes;
    private Context context;
    private int resource;

    public FinishAdapter(AnswerFinish[] listChapter, Context context, int resource) {
        this.answerFinishes = listChapter;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return answerFinishes.length;
    }

    @Override
    public Object getItem(int position) {
        return answerFinishes[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AnswerFinish answerFinish = answerFinishes[position];
        FinishAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new FinishAdapter.ViewHolder();
            vh1.lblQuestionFinsish = convertView.findViewById(R.id.lblQuestionFinsish);
            vh1.lblKetqua = convertView.findViewById(R.id.lblKetqua);
            vh1.lblYourAnswer = convertView.findViewById(R.id.lblYourAnswer);
            vh1.lblCorrectAnswer = convertView.findViewById(R.id.lblCorrectAnswer);
            convertView.setTag(vh1);
        }
        else {
            vh1 = (FinishAdapter.ViewHolder) convertView.getTag();
        }
        vh1.lblQuestionFinsish.setText(answerFinish.getTitle());
        vh1.lblKetqua.setText(answerFinish.is_correct() ? "Right." : "Wrong!");
        vh1.lblKetqua.setTextColor(answerFinish.is_correct() ? Color.GREEN : Color.RED);
        vh1.lblYourAnswer.setText(answerFinish.getYour_answer());
        vh1.lblCorrectAnswer.setText(answerFinish.is_correct() ? "" : answerFinish.getResult());
        return convertView;
    }
    public static class ViewHolder {
        TextView lblQuestionFinsish;
        TextView lblKetqua;
        TextView lblYourAnswer;
        TextView lblCorrectAnswer;
    }
}
