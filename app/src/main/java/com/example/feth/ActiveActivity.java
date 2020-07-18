package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import client.APIClient;
import interfaces.RequestAPI;
import model.ActiveResult;
import model.resAdminRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActiveActivity extends AppCompatActivity {
    String email,otp;
    Button btnBackActive,btnContinueActive;
    EditText txtOTPActiveCode,txtEmailActive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);
        txtEmailActive=findViewById(R.id.txtEmailActive);
        txtOTPActiveCode=findViewById(R.id.txtOTPActiveCode);

        btnBackActive=findViewById(R.id.btnBackActive);
        btnContinueActive=findViewById(R.id.btnContinueActive);



        btnBackActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnContinueActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=txtEmailActive.getText().toString();
                otp=txtOTPActiveCode.getText().toString();
                ActiveUser(email,otp);
            }
        });
    }
    private void ActiveUser(String email, String otp)
    {
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<ActiveResult> call=requestAPI.activeUser(email,otp);
        call.enqueue(new Callback<ActiveResult>() {
            @Override
            public void onResponse(Call<ActiveResult> call, Response<ActiveResult> response) {
                if(response.body()==null)return;
                openNotication("Kích hoạt thành công");
            }

            @Override
            public void onFailure(Call<ActiveResult> call, Throwable t) {

            }
        });
    }
    private void openNotication(String notica)
    {
        Intent intent=new Intent(ActiveActivity.this,NotecationActivity.class);
        intent.putExtra("notication",notica);
        startActivity(intent);
    }
}
