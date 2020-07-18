package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import client.APIClient;
import interfaces.RequestAPI;
import model.Role;
import model.RoleCreateResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewRole extends AppCompatActivity {

    Button btnAddRoleRole;
    EditText txtNameRoleAdd;
    String roleName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_role);
        btnAddRoleRole= findViewById(R.id.btnAddRoleRole);
        txtNameRoleAdd=findViewById(R.id.txtNameRoleAdd);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnAddRoleRole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roleName=txtNameRoleAdd.getText().toString();
                RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
                Call<RoleCreateResult> call=requestAPI.createRole(roleName);
                call.enqueue(new Callback<RoleCreateResult>() {
                    @Override
                    public void onResponse(Call<RoleCreateResult> call, Response<RoleCreateResult> response) {
                        if(response.body()==null)return;
                        Role role=response.body().getRole();
                        Intent intent=new Intent(AddNewRole.this,ManageRolesActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<RoleCreateResult> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
