package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChapterCreateResult {
    @SerializedName("data")
    private Chapter chapter;
    @SerializedName("message")
    private String message;
}
