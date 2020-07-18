package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Result {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private User user;
}
