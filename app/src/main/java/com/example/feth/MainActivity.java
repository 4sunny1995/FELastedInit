package com.example.feth;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;

import client.APIClient;
import interfaces.RequestAPI;
import model.Result;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import validation.Validator;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getName();
    private Validator validator=new Validator();
    private  String username, password,_id;
    private User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });
        findViewById(R.id.forgotUsermain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotActivity();
            }
        });
        findViewById(R.id.lblActive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ActiveActivity.class);
                startActivity(intent);
            }
        });
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        Button btnCancel = (Button) findViewById(R.id.btnExit);
        final EditText txtUsername = (EditText) findViewById(R.id.txtUsernamemain);
        final EditText txtPassword = (EditText) findViewById(R.id.txtPasswordmain);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username=txtUsername.getText().toString();
                password=txtPassword.getText().toString();
                if (!validator.isValidEmail(username)){
                    Alert("Email không hợp lệ");
                    return;
                }
                if (!validator.isValidMinMax(username,6,255)){
                    Alert("Độ dài email phải từ 6-255 ký tự");
                    return;
                }
                if (!validator.isValidMinMax(password,6,20)){
                    Alert("Mật khẩu phải từ 6-20 ký tự");
                    return;
                }
                try {
                    Login();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Login() throws IOException {
        RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<Result> call = requestAPI.login(username, password);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                if (result==null){
                    Alert("Thông tin không chính xác");
                    return;
                }
                currentUser=result.getUser();
                saveCurrentUser(currentUser);
                if(currentUser.getRole_id().equals("admin"))openAdminHomeActivity();
                else openProfileActivity();
            }
            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                Toast.makeText(MainActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
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
    private void  openProfileActivity(){
        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
        intent.putExtra("_id",_id);
        startActivity(intent);
    }
    private void openRegisterActivity(){
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    private void openForgotActivity(){
        Intent intent = new Intent(MainActivity.this, ForgotActivity.class);
        startActivity(intent);
    }
    private void openActiveActivity(){
        Intent intent = new Intent(MainActivity.this, ActiveActivity.class);
        startActivity(intent);
    }
    private void openAdminHomeActivity()
    {
        Intent intent =new Intent(MainActivity.this,AdminHomeActivity.class);
        startActivity(intent);
    }
    public void saveCurrentUser(User user){
        if (user.getFirst_name()==null)user.setFirst_name("");
        if (user.getLast_name()==null)user.setLast_name("");
        SharedPreferences sharedPreferences=getSharedPreferences("currentUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor userEditor=sharedPreferences.edit();
        userEditor.putString("_id",user.getId());
        userEditor.putString("email",user.getEmail());
        userEditor.putString("first_name",user.getFirst_name());
        userEditor.putString("last_name",user.getLast_name());
        userEditor.putString("address",user.getAddress());
        userEditor.putString("phone_number",user.getPhone_number());
        userEditor.putString("birth_day",user.getBirth_day());
        userEditor.putString("created_at",user.getCreated_at());
        userEditor.putString("deleted_at",user.getDeleted_at());
        userEditor.putString("role",user.getRole_id());
        userEditor.apply();
    }
}
