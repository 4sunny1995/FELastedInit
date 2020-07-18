package model;


import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RoleCreateResult {
    @SerializedName("data")
    Role role;
    @SerializedName("message")
    private String message;
}
