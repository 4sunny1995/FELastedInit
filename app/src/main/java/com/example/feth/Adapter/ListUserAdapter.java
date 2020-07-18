package com.example.feth.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.feth.MainActivity;
import com.example.feth.QuizActivity;
import com.example.feth.R;

import client.APIClient;
import interfaces.RequestAPI;
import model.Question;
import model.Result;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserAdapter extends BaseAdapter {
    private User[] users;
    private Context context;
    private int resource;

    public ListUserAdapter(User[] listChapter, Context context, int resource) {
        this.users = listChapter;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public int getCount() {
        return users.length;
    }

    @Override
    public Object getItem(int position) {
        return users[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final User user = users[position];
        ListUserAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new ListUserAdapter.ViewHolder();
            vh1.tvID = convertView.findViewById(R.id.tvIDAdminUser);
            vh1.NameAccount = convertView.findViewById(R.id.NameAccount);
            vh1.btnQLDeleteUser = convertView.findViewById(R.id.btnQLDeleteUser);
            convertView.setTag(vh1);
        }
        else {
            vh1 = (ListUserAdapter.ViewHolder) convertView.getTag();
        }
        vh1.tvID.setText(position+1+"");
        vh1.NameAccount.setText(user.getEmail());
        vh1.btnQLDeleteUser.setBackgroundResource(R.drawable.ic_delete_black_24dp);
        vh1.btnQLDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestAPI requestAPI =APIClient.getClient().create(RequestAPI.class);
                Call<Result> call=requestAPI.deleteUserById(user.getId());
                call.enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        System.out.println(response.body().getMessage());
                    }

                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {

                    }
                });
            }
        });
        return convertView;
    }
    public static class ViewHolder {
        TextView tvID;
        TextView NameAccount;
        Button btnQLDeleteUser;

    }
//    public void Alert(String message, String id, View context) {
//        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
//        alertDialog.setTitle("Thông báo");
//        alertDialog.setMessage(message);
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Xác nhận",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Hủy bỏ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        Button btnOK=alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
//        btnOK.setBackgroundColor(Color.GREEN);
//        btnOK.setTextColor(Color.WHITE);
//        Button btnCancel=alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL);
//        btnCancel.setBackgroundColor(Color.RED);
//        btnCancel.setTextColor(Color.WHITE);
//        alertDialog.show();
//    }
//    private void deleteUser(String id)
//    {
//        RequestAPI requestAPI= APIClient.getClient().create();
//        Call<>
//    }
}
