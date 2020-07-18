package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.feth.Adapter.ChapterAdapter;

import java.util.List;

import client.APIClient;
import interfaces.RequestAPI;
import model.Chapter;
import model.ResultChapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageChapTerActivity extends AppCompatActivity {
    private String[] arrayChapter,arrayID;
    private List<Chapter> chapterPageLists;
    private ListView listView;
    Button btnThemChuongAdmin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_chap_ter);
        listView=findViewById(R.id.ListChapterAdmin);
        btnThemChuongAdmin = findViewById(R.id.btnThemChuongAdmin);
        btnThemChuongAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManageChapTerActivity.this,AddNewChapter.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getListChapter();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    public void getListChapter() {
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<ResultChapter> call = requestAPI.getListChapter("old");
        call.enqueue(new Callback<ResultChapter>() {
            @Override
            public void onResponse(Call<ResultChapter> call, Response<ResultChapter> response) {

                if (response.body().getChapterPageList() == null) return;
                chapterPageLists = response.body().getChapterPageList().getChapter();
                int i = 0;
                arrayChapter = new String[chapterPageLists.size()];
                arrayID = new String[chapterPageLists.size()];
                for (Chapter chapter : chapterPageLists) {
                    arrayChapter[i] = chapter.getName();
                    arrayID[i] = chapter.getId();
                    i++;
                }
//                Array.sort(arrayChapter);
                final ChapterAdapter arrayAdapter = new ChapterAdapter(arrayChapter, ManageChapTerActivity.this, R.layout.item_list_chapter);
                listView.setAdapter(arrayAdapter);

            }

            @Override
            public void onFailure(Call<ResultChapter> call, Throwable t) {

            }
        });
    }
}
