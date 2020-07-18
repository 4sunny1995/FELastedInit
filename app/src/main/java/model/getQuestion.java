package model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class getQuestion {
    @SerializedName("data")
    private QuestionDetail question;
    @SerializedName("message")
    private String message;
}
