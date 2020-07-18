package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HistorResult {
    @SerializedName("data")
    private  HistoryItemResult[] historyItemResult;
    @SerializedName("message")
    private String message;
}
