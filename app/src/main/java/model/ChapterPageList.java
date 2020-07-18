package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChapterPageList {
    @SerializedName("page")
    private String page;
    @SerializedName("totalPage")
    private int totalPage;
    @SerializedName("data")
    List<Chapter> chapter;
}
