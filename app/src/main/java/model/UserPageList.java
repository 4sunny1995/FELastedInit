package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserPageList {
    @SerializedName("page")
    private int page;
    @SerializedName("totalPage")
    private int totalPage;
    @SerializedName("data")
    private User[] users;
}
