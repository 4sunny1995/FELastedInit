package com.example.feth;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.feth.ui.home.HomeFragment;
import com.google.android.material.navigation.NavigationView;

import client.APIClient;
import interfaces.RequestAPI;
import model.Result;
import model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity{

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer ;
    NavigationView navigationView;
    private String _id;
    private User user;

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences=getSharedPreferences("currentUser",MODE_PRIVATE);
        _id=sharedPreferences.getString("_id","");
        getUser(_id);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_news, R.id.nav_profile, R.id.nav_reviews)
                .setDrawerLayout(drawer)
                .build();
        final FragmentManager transaction =  getSupportFragmentManager();
        NewsFragment newsItemFragment = new NewsFragment().newInstance();
        transaction.beginTransaction()
                .add(R.id.nav_host_fragment, newsItemFragment)
                .commit();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.

                switch (item.getItemId()) {
                    case R.id.nav_news:{
                        NewsFragment newsItemFragment = new NewsFragment().newInstance();
                        transaction.beginTransaction()
                                .replace(R.id.nav_host_fragment, newsItemFragment)
                                .commit();
                        break;
                    }
                    case R.id.nav_profile:{
                        ProfileFragment profile = new ProfileFragment().newInstance();
                        transaction.beginTransaction()
                                .replace(R.id.nav_host_fragment, profile)
                                .commit();
                        break;
                    }
                    case R.id.nav_reviews:{
                        ReviewFragment reviewFragment = new ReviewFragment().newInstance();
                        transaction.beginTransaction()
                                .replace(R.id.nav_host_fragment, reviewFragment)
                                .commit();
                        break;
                    }


                }
                //close navigation drawer
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return false;
//                return false;
            }
        });
//        NavController navController =Navigation.findNavController(this,R.id.nav_host_fragment);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void Alert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
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
    synchronized public void getUser(String id){
        final RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<Result> call = requestAPI.getUserById(id);

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.body()==null){
                    Alert("Có lỗi xảy ra ");
                }
                user=response.body().getUser();

            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Alert("error1");
                System.out.println(t.getMessage());
            }
        });
    }


}
