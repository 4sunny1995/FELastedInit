package com.example.feth;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import client.APIClient;
import interfaces.RequestAPI;
import lombok.SneakyThrows;
import model.Result;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {
    private ProfileViewModel mViewModel;
    private User user=new User();
    private String id;
    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    public ProfileFragment(){

    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final User user=getUserFromSession();
        final View rootView=inflater.inflate(R.layout.profile_fragment, container, false);
            final EditText txtemail=rootView.findViewById(R.id.txtEmailPF);
            final EditText txtfirstname=rootView.findViewById(R.id.txtFirstNamePD);
            final EditText txtlasttname=rootView.findViewById(R.id.txtLastNamePF);
            final EditText txtBirthday=rootView.findViewById(R.id.txtBrithdayPF);
            final EditText txtAddress = rootView.findViewById(R.id.txtAddressPF);
            final EditText txtphone= rootView.findViewById(R.id.txtphonePF);
            txtemail.setText(user.getEmail());
            txtfirstname.setText(user.getFirst_name());
            txtlasttname.setText(user.getLast_name());
            txtBirthday.setText(user.getBirth_day());
            txtAddress.setText(user.getAddress());
            txtphone.setText(user.getPhone_number());
            Button btnUpdate=rootView.findViewById(R.id.btnSavePF);
            Button btnExit=rootView.findViewById(R.id.btnBackForgot);
            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),HomeActivity.class);
                    intent.putExtra("_id",id);
                    startActivity(intent);
                }
            });
            SharedPreferences sharedPreferences=getActivity().getSharedPreferences("currentUser",MODE_PRIVATE);
            id=sharedPreferences.getString("_id","");
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String firstname=txtfirstname.getText().toString()+" ";
                    String lastname=txtlasttname.getText().toString()+" ";
                    String birthday = txtBirthday.getText().toString()+" ";
                    String Address = txtAddress.getText().toString()+" ";
                    String phone=txtphone.getText().toString()+" ";

                    updateUser(id,firstname,lastname,phone,Address,birthday);
                }
            });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);


    }
    synchronized public void getUser(String id){
        final RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<Result> call = requestAPI.getUserById(id);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.isSuccessful()){
                    user=response.body().getUser();
                    return ;
                }
                else {
                    Alert("error2");
                    return;
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Alert("error1");
                System.out.println(t.getMessage());
            }
        });
    }
//    public void updateUser(String id,String firstname,String lastname,String birthday, String address,String interest,String intro,String first_name,String last_name,String phone_number){
    public void updateUser(String id,String first_name,String last_name,String phone_number,String address,String birthday){
        RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<Result> call=requestAPI.updateUser(id,first_name,last_name,phone_number,address,birthday);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {

                if(response.body()==null){
                    Alert(response.errorBody().toString());
                    return;
                }
                user=response.body().getUser();
                AlertSuccess(response.body().getMessage());
                saveCurrentUser(user);
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Alert("Máy chủ không phản hồi");
            }
        });
    }
    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this.getActivity()).create();
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
        AlertDialog alertDialog = new AlertDialog.Builder(this.getActivity()).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Xác nhận",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
    public User getUserFromSession(){
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("currentUser",MODE_PRIVATE);
        User user=new User();
        user.setId(sharedPreferences.getString("_id",""));
        user.setFirst_name(sharedPreferences.getString("first_name",""));
        user.setLast_name(sharedPreferences.getString("last_name",""));
        user.setEmail(sharedPreferences.getString("email",""));
        user.setAddress(sharedPreferences.getString("address",""));
        user.setBirth_day(sharedPreferences.getString("birth_day",""));
        user.setCreated_at(sharedPreferences.getString("created_at",""));
        user.setDeleted_at(sharedPreferences.getString("deleted_at",""));
        user.setPhone_number(sharedPreferences.getString("phone_number",""));
        return user;
    }
    public void saveCurrentUser(User user){
        if (user.getFirst_name()==null)user.setFirst_name("");
        if (user.getLast_name()==null)user.setLast_name("");
        SharedPreferences sharedPreferences= getActivity().getSharedPreferences("currentUser", Context.MODE_PRIVATE);
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
