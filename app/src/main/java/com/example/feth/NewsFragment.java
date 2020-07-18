package com.example.feth;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.feth.Adapter.NewsAdapter;
import com.example.feth.Adapter.TDMINewsAdapter;

import java.util.ArrayList;
import java.util.List;

import client.APIClient;
import interfaces.RequestAPI;
import model.News;
import model.ResultNews;
import model.TDMUNewsResult;
import model.tdmuNews;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    private List<News> newsList = new ArrayList<>();
    private List<tdmuNews> tdmuNewsArrayList= new ArrayList<>();
    private NewsAdapter newsAdapter;
    private TDMINewsAdapter tdmiNewsAdapter;
    private ListView list_news;
    Button btnTDMU;
    Button btnChinhPhu;
    private int onTab =1;
    public static NewsFragment newInstance() {
        return new NewsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnTDMU=view.findViewById(R.id.btnNewTDMU);
        btnChinhPhu=view.findViewById(R.id.btnNewChinhPhu);
        list_news = view.findViewById(R.id.newListView);


    }

    @Override
    public void onStart() {
        super.onStart();
//        getNews();
        if(onTab == 1){
            if(tdmuNewsArrayList.size() ==0){
                getTDMUNews();
            }
        }else{
            if(newsList.size() ==0){
                getNews();
            }
        }
        btnTDMU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTDMUNews();
                btnTDMU.setBackgroundResource(R.color.colorWhite);
                btnTDMU.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnChinhPhu.setBackgroundResource(R.color.colorPrimary);
                btnChinhPhu.setTextColor(getResources().getColor(R.color.colorWhite));
                onTab =1;
            }
        });
        btnChinhPhu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getNews();
                btnChinhPhu.setBackgroundResource(R.color.colorWhite);
                btnChinhPhu.setTextColor(getResources().getColor(R.color.colorPrimary));
                btnTDMU.setBackgroundResource(R.color.colorPrimary);
                btnTDMU.setTextColor(getResources().getColor(R.color.colorWhite));
                onTab =2;
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private void getNews() {
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<ResultNews> call = requestAPI.getNews();

        call.enqueue(new Callback<ResultNews>() {
            @Override
            public void onResponse(Call<ResultNews> call, Response<ResultNews> response) {
                newsList.clear();
                ResultNews resultNews = response.body();
                List<News> _newsList = resultNews.getNewsList();
                Toast.makeText(getActivity(), resultNews.getMessage(), Toast.LENGTH_SHORT);

                if (newsList == null) {
                    return;
                }

                // Update news
                newsList.addAll(_newsList);
                newsAdapter = new NewsAdapter(getActivity(), R.layout.list_news_item, newsList);
                list_news.setAdapter(newsAdapter);
                list_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WebView wb;
                        News news = newsList.get(position);
                        wb= new WebView(view.getContext());
                        wb.loadUrl(news.getHref());
                    }
                });
//                newsAdapter.notifyDataSetChanged();
//                list_news.invalidate();
            }

            @Override
            public void onFailure(Call<ResultNews> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT);
            }
        });

    }
    private void getTDMUNews(){
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<TDMUNewsResult> call=requestAPI.getTDMUNews();
        call.enqueue(new Callback<TDMUNewsResult>() {
            @Override
            public void onResponse(Call<TDMUNewsResult> call, Response<TDMUNewsResult> response) {
                tdmuNewsArrayList.clear();
                TDMUNewsResult resultNews = response.body();
                List<tdmuNews> _newsList = resultNews.getTdmuNewsList();
                if (tdmuNewsArrayList == null) {
                    return;
                }
                tdmuNewsArrayList.addAll(_newsList);
                tdmiNewsAdapter = new TDMINewsAdapter(getActivity(), R.layout.list_news_tdmu_item, tdmuNewsArrayList);
                list_news.setAdapter(tdmiNewsAdapter);
                list_news.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        WebView wb;
                        tdmuNews news = tdmuNewsArrayList.get(position);
                        wb= new WebView(view.getContext());
                        wb.loadUrl(news.getHref());
                    }
                });

            }

            @Override
            public void onFailure(Call<TDMUNewsResult> call, Throwable t) {


            }
        });
    }
}
