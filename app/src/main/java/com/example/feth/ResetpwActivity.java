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

public class ResetpwActivity extends AppCompatActivity {
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resetpw);
        Intent intent=getIntent();
        email=intent.getStringExtra("email");
        final EditText txtotpCode=(EditText) findViewById(R.id.txtcodeOTPResetPassword);
        final EditText txtpassword = (EditText) findViewById(R.id.txtNewPasswordResetPassword);
        final EditText txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmNewPasswordResetPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLoginResetPassword);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password=txtpassword.getText().toString();
                String confirmPassword=txtConfirmPassword.getText().toString();
                String otp=txtotpCode.getText().toString();
                if (!isValid(password,confirmPassword)){
                    return;
                }
                resetPasswordOTP(otp,password,confirmPassword);
            }
        });
    }
    private void resetPasswordOTP(String otp,String password,String confirmPassword){
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<Result> call=requestAPI.resetPassword(email,otp,password,confirmPassword);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()){
                    AlertSuccess(response.body().getMessage());
                }
                else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        String message = jObjError.getString("message");
                        Alert(message);
                    } catch (Exception e) {
                        Toast.makeText(ResetpwActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
//                    Alert(message);
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(ResetpwActivity.this, "Server can not response", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private boolean isValid(String password,String rePassword){
        Validator validator=new Validator();
        if(!validator.isValidConfirmPassword(password,rePassword)){
            Alert("Mật khẩu xác nhận không chính xác");
            return false;
        }
        return true;
    }
    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(ResetpwActivity.this).create();
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
    public void AlertSuccess(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(ResetpwActivity.this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        openLoginAutomatic();
                    }
                });
        alertDialog.show();
    }
    public void  openLoginAutomatic(){
        Intent intent=new Intent(ResetpwActivity.this,MainActivity.class);
        intent.putExtra("email",email);
        intent.putExtra("password",password);
        startActivity(intent);
    }
}
