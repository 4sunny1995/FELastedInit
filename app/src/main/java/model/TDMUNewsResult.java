package model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class TDMUNewsResult {
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private List<tdmuNews> tdmuNewsList;
}
