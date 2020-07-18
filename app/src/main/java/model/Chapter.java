package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class Chapter {

    @SerializedName("_id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at ")
    private String updated_at;
    @SerializedName("__v")
    private int v;
}
