package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class tdmuNews {
    @SerializedName("href")
    private String href;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("content")
    private String content;
    @SerializedName("time_post")
    private String time_post;
}
