package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultListUser {
    @SerializedName("data")
    private UserPageList userPageList;
    @SerializedName("message")
    private String Message;
}
