package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.feth.Adapter.ListUserAdapter;

import client.APIClient;
import interfaces.RequestAPI;
import model.ResultListUser;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MangeUserActivity extends AppCompatActivity {
    private ListView listView;
    private User[] users;
    TextView tvSLUser;
    Button btnThemUserAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mange_user);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listView=findViewById(R.id.listAccount);
        tvSLUser=findViewById(R.id.tvSLUser);
        btnThemUserAdmin=findViewById(R.id.btnThemUserAdmin);
        getAllUser();
        btnThemUserAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MangeUserActivity.this,AdminCreateUser.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void getAllUser()
    {
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<ResultListUser> call=requestAPI.getAllUser(0);
        call.enqueue(new Callback<ResultListUser>() {
            @Override
            public void onResponse(Call<ResultListUser> call, Response<ResultListUser> response) {
                if(response.body()==null)return;
                users=response.body().getUserPageList().getUsers();
                ListUserAdapter listUserAdapter=new ListUserAdapter(users,MangeUserActivity.this,R.layout.manage_user_item_list);
                listView.setAdapter(listUserAdapter);
                tvSLUser.setText(users.length+"");
            }

            @Override
            public void onFailure(Call<ResultListUser> call, Throwable t) {

            }
        });
    }
}
