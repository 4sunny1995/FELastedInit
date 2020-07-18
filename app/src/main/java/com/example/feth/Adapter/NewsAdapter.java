package com.example.feth.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.feth.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import lombok.SneakyThrows;
import model.News;

public class NewsAdapter extends ArrayAdapter {
    private List<News> newsList;
    private Context context;
    private int resource;

    public NewsAdapter(Context context, int resource, List<News> newsList) {
        super(context, resource, newsList);

        this.context = context;
        this.resource = resource;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SneakyThrows
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh = new ViewHolder();

//            vh.imageView = ;
            vh.imageView = convertView.findViewById(R.id.imgNews);
            vh.titleView = convertView.findViewById(R.id.titleNews);
            vh.contentView = convertView.findViewById(R.id.contentNews);

            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        News news = newsList.get(position);
        new DownloadImageTask(vh.imageView).execute(news.getImage());
        vh.titleView.setText(news.getTitle());
        vh.contentView.setText(news.getContent());

        return convertView;
    }

    public static class ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView contentView;
    }


}
 class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
