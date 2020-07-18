package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Role {
    @SerializedName("_id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("created_by")
    private  String created_by;
    @SerializedName("updated_by")
    private String updated_by;
    @SerializedName("__v")
    private int __v;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private  String updated_at;

}
