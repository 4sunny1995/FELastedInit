package model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class getQuiz {
    @SerializedName("data")
    private Exam exam;
    @SerializedName("message")
    private String message;
}
