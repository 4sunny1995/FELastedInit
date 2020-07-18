package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class resUserRegister {
    @SerializedName("first_name")
    private String first_name;
    @SerializedName("last_name")
    private String last_name;
    @SerializedName("_id")
    private String id;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("birth_day")
    private String birth_day;
    @SerializedName("created_at")
    private String created_at;
    @SerializedName("updated_at")
    private String updated_at;
    @SerializedName("created_by")
    private String created_by;
    @SerializedName("isActive")
    private Integer isActive;
    @SerializedName("role_id")
    private String role_id;
}
