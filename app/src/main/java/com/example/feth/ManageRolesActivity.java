package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.feth.Adapter.RoleListAdapter;

import client.APIClient;
import interfaces.RequestAPI;
import model.Role;
import model.RoleResultList;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageRolesActivity extends AppCompatActivity {

    private  Role[] roles;
    ListView listView;
    Button btnThemRoleMoi;
    EditText nameRole;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_roles);
        listView=findViewById(R.id.listAccAdmin);

        btnThemRoleMoi=findViewById(R.id.btnThemRoleMoi);
        btnThemRoleMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManageRolesActivity.this,AddNewRole.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        getListAdmin();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void getListAdmin()
    {
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<RoleResultList> call =requestAPI.getListRole();
        call.enqueue(new Callback<RoleResultList>() {
            @Override
            public void onResponse(Call<RoleResultList> call, Response<RoleResultList> response) {
                if(response.body()==null)return;
                roles=response.body().getRolesPageList().getRoles();
                RoleListAdapter roleListAdapter=new RoleListAdapter(roles,ManageRolesActivity.this,R.layout.role_list_item);
                listView.setAdapter(roleListAdapter);
            }

            @Override
            public void onFailure(Call<RoleResultList> call, Throwable t) {

            }
        });
    }
}
