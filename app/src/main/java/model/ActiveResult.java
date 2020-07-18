package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ActiveResult {
    @SerializedName("data")
    UserActive userActive;
    @SerializedName("message")
    private String message;
}
