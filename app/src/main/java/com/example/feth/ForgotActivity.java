package com.example.feth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import client.APIClient;
import interfaces.RequestAPI;
import model.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import validation.Validator;

public class ForgotActivity extends AppCompatActivity {
    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        Button btnContinue = (Button)findViewById(R.id.btnContinueForgot);
        Button btnBack = (Button) findViewById(R.id.btnBackForgot);
        final EditText txtEmail = (EditText) findViewById(R.id.txtEmailforgot);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString();
                if (!isEmailValid(email)){
                    Alert("Email không hợp lệ");
                    return;
                }
                else {
                    String type="forgot";
                    Forgot(email,type);
                }
            }
        });
    }
    private boolean isEmailValid(String email){
        Validator validator=new Validator();
        return validator.isValidEmail(email);
    }
    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(ForgotActivity.this).create();
        alertDialog.setTitle("Thông báo lỗi");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Xác nhận",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    private void Forgot(String email, String type){
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<Result> call=requestAPI.getOTP(email,type);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()){
                    openResetpassword();
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Alert(message);
                    } catch (Exception e) {
                        Toast.makeText(ForgotActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
//                    Alert(message);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(ForgotActivity.this, "Server can not response", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void openResetpassword(){
        Intent intent=new Intent(ForgotActivity.this,ResetpwActivity.class);
        intent.putExtra("email",email);
        startActivity(intent);
    }

}
