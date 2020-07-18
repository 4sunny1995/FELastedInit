package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HistoryItemResult {
    @SerializedName("chapter_name")
    private String chapter_name;
    @SerializedName("percent")
    private  float percent;
    @SerializedName("created_at")
    private String created_at;
}
