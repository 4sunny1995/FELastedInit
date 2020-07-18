package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class RoleResultList {
    @SerializedName("data")
    private RolesPageList rolesPageList;
    @SerializedName("message")
    private String message;
}
