package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerFinish {
    @SerializedName("_id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("A")
    private  String A;
    @SerializedName("B")
    private  String B;
    @SerializedName("C")
    private  String C;
    @SerializedName("D")
    private  String D;
    @SerializedName("result")
    private  String result;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("__v")
    private String __v;
    @SerializedName("your_answer")
    private String your_answer;
    @SerializedName("is_correct")
    private boolean is_correct;

}
