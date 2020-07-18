package com.example.feth;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.feth.Adapter.ChapterAdapter;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import client.APIClient;
import interfaces.RequestAPI;
import model.Chapter;
import model.ChapterPageList;
import model.ResultChapter;
import model.ResultNews;
import model.getQuestion;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class ReviewFragment extends Fragment {

    private String[] arrayChapter,arrayID;
    private List<Chapter> chapterPageLists;
    public ReviewFragment() {
        // Required empty public constructor
    }
    private ListView listView;
    Button btnViewHistory;
    String _id;
    public static ReviewFragment newInstance() {

        return new ReviewFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        getListChapter();
        SharedPreferences sharedPreferences=this.getActivity().getSharedPreferences("currentUser",MODE_PRIVATE);
        _id=sharedPreferences.getString("_id","");
        btnViewHistory= getActivity().findViewById(R.id.btnViewHistory);
        btnViewHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),History_Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView=view.findViewById(R.id.ListChapter);
    }

    public void getListChapter(){
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<ResultChapter> call = requestAPI.getListChapter("old");
        call.enqueue(new Callback<ResultChapter>() {
            @Override
            public void onResponse(Call<ResultChapter> call, Response<ResultChapter> response) {

                if(response.body().getChapterPageList()==null)return;
                chapterPageLists=response.body().getChapterPageList().getChapter();
                int i =0;
                arrayChapter=new String[chapterPageLists.size()];
                arrayID =new  String[chapterPageLists.size()];
                for(Chapter chapter:chapterPageLists){
                    arrayChapter[i]=chapter.getName();
                    arrayID[i]=chapter.getId();
                    i++;
                }
//                Array.sort(arrayChapter);
                final ChapterAdapter arrayAdapter= new ChapterAdapter(arrayChapter, getActivity(), R.layout.item_list_chapter);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                        AlertSuccess("Bắt đầu thi.",position);

                    }
                });
            }

            @Override
            public void onFailure(Call<ResultChapter> call, Throwable t) {

            }
        });
    }
    private void openQuizzActivity(){
        Intent intent=new Intent(getActivity(),QuizActivity.class);
        startActivity(intent);
    }
    public void AlertSuccess(String message,int position) {
        final int position_temp = position;
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Thông báo");
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
                        Call<getQuestion> call=requestAPI.takeQuizz(arrayID[position_temp],_id);
                        call.enqueue(new Callback<getQuestion>() {
                            @Override
                            public void onResponse(Call<getQuestion> call, Response<getQuestion> response) {
                                if(response.body() == null){
                                    AlertContine();
                                    return;
                                }
                                openQuizzActivity();
                            }
                            @Override
                            public void onFailure(Call<getQuestion> call, Throwable t) {
                            }
                        });

                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Huỷ bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
    public  void AlertContine(){
        final AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
        alertDialog.setTitle("Thong bao");
        alertDialog.setMessage("Tiếp tục bài thi");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Đồng ý",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        openQuizzActivity();
                    }
                });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Huỷ bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog.show();
    }
}
