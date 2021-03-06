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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.feth.R;

import java.io.InputStream;
import java.util.List;

import lombok.SneakyThrows;
import model.News;
import model.tdmuNews;

public class TDMINewsAdapter extends ArrayAdapter {
    private List<tdmuNews> newsList;
    private Context context;
    private int resource;

    public TDMINewsAdapter(@NonNull Context context, int resource, List<tdmuNews> newsList) {
        super(context, resource, newsList);
        this.newsList = newsList;
        this.context = context;
        this.resource = resource;
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
        TDMINewsAdapter.ViewHolder vh1;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(resource, parent, false);
            vh1 = new TDMINewsAdapter.ViewHolder();
            vh1.imageView = convertView.findViewById(R.id.imgNewstdmu);
            vh1.titleView = convertView.findViewById(R.id.titleNewstdmu);
            vh1.contentView = convertView.findViewById(R.id.contentNewstdmu);
            vh1.time_postView=convertView.findViewById(R.id.contentNewstdmu);
            convertView.setTag(vh1);
        } else {
            vh1 = (TDMINewsAdapter.ViewHolder) convertView.getTag();
        }

        tdmuNews news = newsList.get(position);
        new DownloadImageTask(vh1.imageView).execute(news.getImage());
        vh1.titleView.setText(news.getTitle());
        vh1.contentView.setText(news.getContent());
        vh1.time_postView.setText(news.getTime_post());
        return convertView;
    }
    public static class ViewHolder {
        ImageView imageView;
        TextView titleView;
        TextView contentView;
        TextView time_postView;
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
}
