package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultSave {
    @SerializedName("data")
    private boolean status;
    @SerializedName("message")
    private String message;
}
