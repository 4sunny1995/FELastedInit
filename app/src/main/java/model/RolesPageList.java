package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class RolesPageList {
    @SerializedName("page")
    private int page;
    @SerializedName("totalPage")
    private int totalPage;
    @SerializedName("data")
    private Role[] roles;
}
