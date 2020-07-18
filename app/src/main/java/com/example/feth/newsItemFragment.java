//package com.example.feth;
//
//import android.content.DialogInterface;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Adapter;
//import android.widget.ListView;
//
//import com.example.feth.Adapter.newsAdapter;
//
//import java.util.Collection;
//import java.util.Iterator;
//import java.util.List;
//import java.util.ListIterator;
//
//import client.APIClient;
//import interfaces.RequestAPI;
//import model.News;
//import model.Result;
//import model.ResultNews;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
///**
// * A simple {@link Fragment} subclass.
// * Use the {@link newsItemFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class newsItemFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_img = "param1";
//    private static final String ARG_href = "param2";
//    private static final String ARG_title = "param3";
//    private static final String ARG_content = "param4";
//    private List<News> newsList= null;
//    // TODO: Rename and change types of parameters
//    private String mImg;
//    private String mHref;
//    private String mTitle;
//    private String mContent;
//    private News[] data;
//    public newsItemFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param img Parameter 1.
//     * @param href Parameter 2.
//     * @param title Parameter 3.
//     * @param content Parameter 3.
//     * @return A new instance of fragment newsItemFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static newsItemFragment newInstance(String img,String href, String title,String content) {
//        newsItemFragment fragment = new newsItemFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_img, img);
//        args.putString(ARG_href, href);
//        args.putString(ARG_title, title);
//        args.putString(ARG_content, content);
//        fragment.setArguments(args);
//        return fragment;
//    }
//    public static newsItemFragment newInstance() {
//        return new newsItemFragment();
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mImg = getArguments().getString(ARG_img);
//            mHref = getArguments().getString(ARG_href);
//            mTitle = getArguments().getString(ARG_title);
//            mContent = getArguments().getString(ARG_content);
//        }
//        getNews();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        final View rootView = inflater.inflate(R.layout.fragment_news_item, container, false);
//        getNews();
//        newsAdapter newsAdapter =new newsAdapter(this.getActivity(),newsList);
//        ListView newListView = rootView.findViewById(R.id.newListView);
//        newListView.setAdapter(newsAdapter);
//        // Inflate the layout for this fragment
//        return rootView;
//    }
//    public void getNews(){
//        final RequestAPI requestAPI= APIClient.getClient().create(RequestAPI.class);
//        Call<ResultNews> call=requestAPI.getNews();
//        call.enqueue(new Callback<ResultNews>() {
//            @Override
//            public void onResponse(Call<ResultNews> call, Response<ResultNews> response) {
//                ResultNews resultNews = response.body();
//
//                if (response.isSuccessful()){
//
//                    }
//                }
//                else Alert(response.body().getMessage());
//            }
//
//            @Override
//            public void onFailure(Call<ResultNews> call, Throwable t) {
//                Alert("Không nhận được phản hồi");
//                Alert(1+"");
//            }
//        });
//    }
//    public void Alert(String message) {
//        AlertDialog alertDialog = new AlertDialog.Builder(this.getActivity()).create();
//        alertDialog.setTitle("Thông báo lỗi");
//        alertDialog.setMessage(message);
//        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Xác nhận",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//        alertDialog.show();
//    }
//
//}
