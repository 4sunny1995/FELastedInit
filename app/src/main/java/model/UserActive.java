package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserActive {
    @SerializedName("isActive")
    private int isActive;
    @SerializedName("_id")
    private String id;
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("role_id")
    private String role_id;
    @SerializedName("created_by")
    private int created_by;
    @SerializedName("birth_day")
    private String birth_day;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("__v")
    private int __v;
    @SerializedName("token")
    private String token;
}
