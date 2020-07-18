package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.feth.Adapter.FinishAdapter;
import com.example.feth.Adapter.ListQuestionAdapter;

import client.APIClient;
import interfaces.RequestAPI;
import model.AnswerFinish;
import model.FinishResult;
import model.ResultFinsih;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinishExamActivity extends AppCompatActivity {

    private String id;
    FinishAdapter finishAdapter;
    AnswerFinish[] answerFinishes;
    ListView listDapAn;
    TextView tvNumberTrue,tvPoint;
    Button btnXong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish_exam);
        SharedPreferences sharedPreferences=getSharedPreferences("currentUser",MODE_PRIVATE);
        id=sharedPreferences.getString("_id","");
        listDapAn=findViewById(R.id.listDapAn);
        tvNumberTrue=findViewById(R.id.tvNumberTrue);
        tvPoint=findViewById(R.id.tvPoint);
        btnXong=findViewById(R.id.btnXong123);
        getResultFinsh();
        btnXong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FinishExamActivity.this,HomeActivity.class);
                intent.putExtra("_id",id);
                startActivity(intent);
            }
        });

    }
    private void getResultFinsh(){
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<FinishResult> call=requestAPI.getResultFinish(id);
        call.enqueue(new Callback<FinishResult>() {
            @Override
            public void onResponse(Call<FinishResult> call, Response<FinishResult> response) {
                FinishResult resultResponse=response.body();
                if (response.body()==null)return;
                answerFinishes=response.body().getResultFinsih().getAnswerFinishes();
                finishAdapter =new FinishAdapter(answerFinishes,FinishExamActivity.this,R.layout.result_excam_item);
                listDapAn.setAdapter(finishAdapter);
                tvNumberTrue.setText(response.body().getResultFinsih().getCountRight()+"/20");
                tvPoint.setText(response.body().getResultFinsih().getPoint()+"");
            }

            @Override
            public void onFailure(Call<FinishResult> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
