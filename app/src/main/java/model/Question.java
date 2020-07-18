package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Question {
    @SerializedName("_id")
    private String id;
    @SerializedName("question_id")
    private String question_id;
    @SerializedName("is_save")
    private int is_save;
    @SerializedName("result")
    private String result;
}
