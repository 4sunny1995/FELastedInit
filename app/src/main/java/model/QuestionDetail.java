package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class QuestionDetail {
    @SerializedName("_id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("A")
    private String answerA;
    @SerializedName("B")
    private String answerB;
    @SerializedName("C")
    private String answerC;
    @SerializedName("D")
    private String answerD;
    @SerializedName("result")
    private String result;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("__v")
    private int v;
}
