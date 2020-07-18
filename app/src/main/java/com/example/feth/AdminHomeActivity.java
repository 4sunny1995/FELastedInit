package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminHomeActivity extends AppCompatActivity {

    Button btnUser,btnChapter,btnRoles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        btnUser=findViewById(R.id.btnQLUser);
        btnChapter=findViewById(R.id.btnQLChapter);
        btnRoles=findViewById(R.id.btnQLRoles);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManageUserActivity();
            }
        });
        btnChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManageChapterActivity();
            }
        });
        btnRoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openManageRolesActivity();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void openManageUserActivity(){
        Intent intent=new Intent(AdminHomeActivity.this,MangeUserActivity.class);
        startActivity(intent);
    }
    private void openManageChapterActivity(){
        Intent intent=new Intent(AdminHomeActivity.this,ManageChapTerActivity.class);
        startActivity(intent);
    }
    private void openManageRolesActivity(){
        Intent intent=new Intent(AdminHomeActivity.this,ManageRolesActivity.class);
        startActivity(intent);
    }
}
