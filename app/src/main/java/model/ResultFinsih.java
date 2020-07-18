package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResultFinsih {
    @SerializedName("point")
    private float point;
    @SerializedName("countWrong")
    private int countWrong;
    @SerializedName("countRight")
    private int countRight;
    @SerializedName("arrayResult")
    private AnswerFinish[] answerFinishes;

}
