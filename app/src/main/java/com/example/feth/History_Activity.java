package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.example.feth.Adapter.HistoryExamAdapter;

import client.APIClient;
import interfaces.RequestAPI;
import model.HistorResult;
import model.HistoryItemResult;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History_Activity extends AppCompatActivity {

    private String id;
    private HistoryItemResult[] historyItemResults;
    private ListView listView;
    private HistoryExamAdapter historyExamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        SharedPreferences sharedPreferences=getSharedPreferences("currentUser",MODE_PRIVATE);
        id=sharedPreferences.getString("_id","id");
        listView=findViewById(R.id.listHistory);
        getHistoryExam();
    }
    private void getHistoryExam()
    {
        RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
        Call<HistorResult> call=requestAPI.getHistoryExam(id);
        call.enqueue(new Callback<HistorResult>() {
            @Override
            public void onResponse(Call<HistorResult> call, Response<HistorResult> response) {
                if(response.body()==null)return;
                historyItemResults=response.body().getHistoryItemResult();
                historyExamAdapter=new HistoryExamAdapter(historyItemResults,History_Activity.this,R.layout.history_take_item);
                listView.setAdapter(historyExamAdapter);
            }

            @Override
            public void onFailure(Call<HistorResult> call, Throwable t) {

            }
        });
    }
}
