package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Exam {
    @SerializedName("_id")
    private String id;
    @SerializedName("user_id")
    private String user_id;
    @SerializedName("is_complete")
    private int is_complete;
    @SerializedName("array_question")
    private Question[] questions;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("__v")
    private int v;
}
