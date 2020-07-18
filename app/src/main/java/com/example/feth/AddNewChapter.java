package com.example.feth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import client.APIClient;
import interfaces.RequestAPI;
import model.ChapterCreateResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewChapter extends AppCompatActivity {

    Button btnThemChuongAdminName;
    EditText txtTenChuongThem;
    private String nameChapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_chapter);
        btnThemChuongAdminName=findViewById(R.id.btnThemChuongAdminName);
        txtTenChuongThem=findViewById(R.id.txtTenChuongThem);


        btnThemChuongAdminName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameChapter=txtTenChuongThem.getText().toString();

                AddNewChapter();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void AddNewChapter()
    {
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<ChapterCreateResult> call=requestAPI.createChapter(nameChapter);
        call.enqueue(new Callback<ChapterCreateResult>() {
            @Override
            public void onResponse(Call<ChapterCreateResult> call, Response<ChapterCreateResult> response) {
                if(response.body()==null)return;
//                Alert(response.body().getMessage());
                Intent intent=new Intent(AddNewChapter.this,ManageChapTerActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ChapterCreateResult> call, Throwable t) {

            }
        });
    }
    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(AddNewChapter.this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Xác nhận",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }
}
