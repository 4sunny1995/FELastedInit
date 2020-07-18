package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class News {
    @SerializedName("href")
    private String href;
    @SerializedName("title")
    private String title;
    @SerializedName("image")
    private String image;
    @SerializedName("content")
    private String content;
}
