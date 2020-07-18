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

public class RegisterActivity extends AppCompatActivity {
    private String email,name,password,repassword;
    private String firstname="zxcxzc";
    private String lastname=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnRegister = (Button) findViewById(R.id.btnRegisterRegister);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);

        final EditText txtName=(EditText) findViewById(R.id.txtNameRegister);
        final EditText txtusername=(EditText) findViewById(R.id.txtEmailRegister);
        final EditText txtpassword=(EditText) findViewById(R.id.txtPasswordRegister);
        final EditText txtrepassword=(EditText) findViewById(R.id.txtRePassRegister);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=txtusername.getText().toString();
                name=txtName.getText().toString();
                lastname=name;
                password=txtpassword.getText().toString();
                repassword=txtrepassword.getText().toString();
                boolean valid=isValidator(email,name,password,repassword);
                if (valid)Register(firstname,lastname,email,password,repassword);
                else return;
            }
        });
    }
    private void Register(String firstname,String lastname,String email,String password,String repassword){
        RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<Result> call = requestAPI.signUp(firstname,lastname,email,password,repassword);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.body()==null)return;
                Intent intent=new Intent(RegisterActivity.this,ActiveActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Server can not response", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        openMainActivity();
                    }
                });
        alertDialog.show();
    }
    public boolean isValidator(String email,String name,String password,String repassword){
        Validator validator=new Validator();
        if (!validator.isValidEmail(email)){
            Alert("Email không hợp lệ ");
            return false;
        }
        if (!validator.isValidMinMax(email,6,255)){
            Alert("Email phải từ 6-255 ký tự");
            return false;
        }
        if (!validator.isValidMinMax(name,1,100)){
            Alert("Họ và tên phải từ 1-100 ký tự");
            return false;
        }
        if (!validator.isValidMinMax(password,6,20)){
            Alert("Mật khẩu phải từ 6-20 ký tự");
            return false;
        }
        if(!validator.isValidConfirmPassword(password,repassword)){
            Alert("Nhập lại mật khẩu không chính xách");
            return false;
        }
        return true;
    }
    private void openMainActivity(){
        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
