package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@RequiredArgsConstructor
public class resAdminRegister {
    @SerializedName("data")
    private resUserRegister userRegister;
    @SerializedName("message")
    private String message;
}
