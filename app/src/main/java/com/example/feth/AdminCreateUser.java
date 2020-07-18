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
import model.resAdminRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import validation.Validator;

public class AdminCreateUser extends AppCompatActivity {

    Button btnAdminRegister,btnAdminCancel;
    private String email,name,lastname,password,repassword;
    private String firstname=" zz";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_admin);
        btnAdminRegister=findViewById(R.id.btnAdminRegisterRegister);
        btnAdminCancel=findViewById(R.id.btnAdminCancel);

    }

    @Override
    protected void onStart() {
        super.onStart();
        final EditText txtName=(EditText) findViewById(R.id.txtAdminNameRegister);
        final EditText txtusername=(EditText) findViewById(R.id.txtAdminEmailRegister);
        final EditText txtpassword=(EditText) findViewById(R.id.txtAdminPasswordRegister);
        final EditText txtrepassword=(EditText) findViewById(R.id.txtAdminRePassRegister);
        btnAdminRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=txtusername.getText().toString();
                name=txtName.getText().toString();
                lastname=name;
                password=txtpassword.getText().toString();
                repassword=txtrepassword.getText().toString();
                boolean valid=isValidator(email,name,password,repassword);
                if (valid)Register(firstname,lastname,email,password);
                else return;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void Register(String firstname,String lastname,String email,String password){
        RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<resAdminRegister> call = requestAPI.AdminCreateUser(firstname,lastname,email,password,"user");
        call.enqueue(new Callback<resAdminRegister>() {
            @Override
            public void onResponse(Call<resAdminRegister> call, Response<resAdminRegister> response) {
                if (response.body()==null)return;
                AlertSuccess("Tạo mới thành công");
                Intent intent=new Intent(AdminCreateUser.this,MangeUserActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<resAdminRegister> call, Throwable t) {
                Toast.makeText(AdminCreateUser.this, "Server can not response", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(AdminCreateUser.this).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(AdminCreateUser.this).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(AdminCreateUser.this,MangeUserActivity.class);
                        startActivity(intent);

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
}
