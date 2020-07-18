package com.example.feth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.feth.Adapter.ListQuestionAdapter;

import java.util.zip.Inflater;

import client.APIClient;
import interfaces.RequestAPI;
import model.Exam;
import model.Question;
import model.QuestionDetail;
import model.ResultSave;
import model.User;
import model.getQuestion;
import model.getQuiz;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuizActivity extends AppCompatActivity {
    String[] QuestionLength=new String[20];
    GridView gridView;
    Button exit,submit,btnSubmitExam;
    TextView question,show;
    RadioButton selectedAnswer,answerA,answerB,answerC,answerD;
    RadioGroup radioGroup;
    String id;
    Question[] questions;
    Question thisQuestion=null;
    int temp=-1;
    Exam exam;
    static public View[] viewCurrent = new View[20];
    static public int postion_current=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        exit=(Button) findViewById(R.id.btnBackQuiz);
        submit=(Button) findViewById(R.id.btnNextQuiz);
        question=(TextView)findViewById(R.id.lblQuestion);
        radioGroup=(RadioGroup)findViewById(R.id.rdgAnswer);
        show=findViewById(R.id.lblShowKQ);
        answerA=findViewById(R.id.rdbA);
        answerB=findViewById(R.id.rdbB);
        answerC=findViewById(R.id.rdbC);
        answerD=findViewById(R.id.rdbD);
        btnSubmitExam=findViewById(R.id.btnSubmitExam);

    }

    @Override
    protected void onStart() {
        super.onStart();
        gridView=(GridView) findViewById(R.id.grvQuestionNumber);

        getQuizz();

    }
    protected void getSaveQuizz(View view,int position){
        String asw=null;
        boolean i1=answerA.isChecked();
        boolean i2=answerB.isChecked();
        boolean i3=answerC.isChecked();
        boolean i4=answerD.isChecked();
        if(i1)asw="A";
        if(i2)asw="B";
        if(i3)asw="C";
        if(i4)asw="D";
        if (asw==null){
            viewCurrent[position].setBackgroundColor(Color.WHITE);
            return;
        }
        if (asw!=""){
            viewCurrent[position].setBackgroundColor(Color.BLUE);
            save(thisQuestion.getId(),asw);
        }

    }
    protected  void getQuestionByPostion(boolean isNext){
        if(postion_current == 0 && isNext == false){
            return;
        }
        if(postion_current == 19 && isNext == true){
            return;
        }
        int position = isNext ? postion_current + 1 : postion_current -1;
        getSaveQuizz(viewCurrent[position],postion_current);
        postion_current = position;

        final  Question item=questions[position];
        for(int i=0;i<viewCurrent.length;i++){
            if(i == position){
                viewCurrent[i].setBackgroundColor(Color.RED);
            }
        }
        thisQuestion = item;
        final int num=position+1;
//                save();
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<getQuestion> call=requestAPI.getQuestion(item.getQuestion_id());
        call.enqueue(new Callback<getQuestion>() {
            @Override
            public void onResponse(Call<getQuestion> call, Response<getQuestion> response) {

                QuestionDetail questionDetail=response.body().getQuestion();
                question.setText("Câu hỏi "+ num+" : "+questionDetail.getTitle());
                answerA.setText(questionDetail.getAnswerA());
                answerB.setText(questionDetail.getAnswerB());
                answerC.setText(questionDetail.getAnswerC());
                answerD.setText(questionDetail.getAnswerD());
            }

            @Override
            public void onFailure(Call<getQuestion> call, Throwable t) {

            }
        });
        if (item.getIs_save()==1){
            radioGroup.clearCheck();
            switch (item.getResult()){
                case "A": answerA.setChecked(true);break;
                case "B":answerB.setChecked(true);break;
                case "C":answerC.setChecked(true);break;
                case "D":answerD.setChecked(true);break;

            }

        }
        else {
            radioGroup.clearCheck();
            answerA.setChecked(false);
            answerB.setChecked(false);
            answerC.setChecked(false);
            answerD.setChecked(false);
        }
    }

    protected  void getQuestion(){

        ListQuestionAdapter arrayAdapter=new ListQuestionAdapter(questions,QuizActivity.this,R.layout.number_question_layout);
        gridView.setAdapter(arrayAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                clearQuestion();
                getSaveQuizz(view,postion_current);
                postion_current = position;

                final  Question item=questions[position];
                for(int i=0;i<viewCurrent.length;i++){
                    if(i == position){
                        viewCurrent[i].setBackgroundColor(Color.RED);
                    }
                }
                thisQuestion = item;
                final int num=position+1;
//                save();
                final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
                Call<getQuestion> call=requestAPI.getQuestion(item.getQuestion_id());
                call.enqueue(new Callback<getQuestion>() {
                    @Override
                    public void onResponse(Call<getQuestion> call, Response<getQuestion> response) {

                        QuestionDetail questionDetail=response.body().getQuestion();
                        question.setText("Câu hỏi "+ num+" : "+questionDetail.getTitle());
                        answerA.setText(questionDetail.getAnswerA());
                        answerB.setText(questionDetail.getAnswerB());
                        answerC.setText(questionDetail.getAnswerC());
                        answerD.setText(questionDetail.getAnswerD());
                    }

                    @Override
                    public void onFailure(Call<getQuestion> call, Throwable t) {

                    }
                });
                if (item.getIs_save()==1){
                    radioGroup.clearCheck();
                    switch (item.getResult()){
                        case "A": answerA.setChecked(true);break;
                        case "B":answerB.setChecked(true);break;
                        case "C":answerC.setChecked(true);break;
                        case "D":answerD.setChecked(true);break;

                    }

                }
                else {
                    radioGroup.clearCheck();
                    answerA.setChecked(false);
                    answerB.setChecked(false);
                    answerC.setChecked(false);
                    answerD.setChecked(false);
                }
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getQuestionByPostion(true);
            }

        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuestionByPostion(false);
            }
        });
        btnSubmitExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSaveQuizz(viewCurrent[postion_current],postion_current);
                Intent intent=new Intent(QuizActivity.this,FinishExamActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    private void clearQuestion(){
        show.setText("");
        answerA.setText("");
        answerB.setText("");
        answerC.setText("");
        answerD.setText("");
        question.setText("");

    }
    private synchronized void getQuizz()
    {
        SharedPreferences sharedPreferences=this.getSharedPreferences("currentUser",MODE_PRIVATE);
        id=sharedPreferences.getString("_id","");
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<getQuiz> call=requestAPI.getQuizz(id);
        call.enqueue(new Callback<getQuiz>() {
            @Override
            public void onResponse(Call<getQuiz> call, Response<getQuiz> response) {
                if (response.body()==null)return;
                exam=response.body().getExam();
                questions= exam.getQuestions();
                int i=0;

                for (Question item:exam.getQuestions()){
                    questions[i]=item;
                    i++;
                }
                getQuestion();
                Question firstquestion=questions[0];
                thisQuestion=firstquestion;
                final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
                Call<getQuestion> call1=requestAPI.getQuestion(firstquestion.getQuestion_id());
                call1.enqueue(new Callback<getQuestion>() {
                    @Override
                    public void onResponse(Call<getQuestion> call, Response<getQuestion> response) {
                        QuestionDetail questionDetail=response.body().getQuestion();
                        question.setText("Câu hỏi 1 : "+questionDetail.getTitle());
                        answerA.setText(questionDetail.getAnswerA());
                        answerB.setText(questionDetail.getAnswerB());
                        answerC.setText(questionDetail.getAnswerC());
                        answerD.setText(questionDetail.getAnswerD());
                    }

                    @Override
                    public void onFailure(Call<getQuestion> call, Throwable t) {

                    }
                });
                if (firstquestion.getIs_save()==1){
                    switch (firstquestion.getResult()){
                        case "A":answerA.setChecked(true);break;
                        case "B":answerB.setChecked(true);break;
                        case "C":answerC.setChecked(true);break;
                        case "D":answerD.setChecked(true);break;

                    }
                }
                else {
                    answerA.setChecked(false);
                    answerB.setChecked(false);
                    answerC.setChecked(false);
                    answerD.setChecked(false);
                }
            }

            @Override
            public void onFailure(Call<getQuiz> call, Throwable t) {

            }
        });
    }
    private void save( String id,  String result){
        final RequestAPI requestAPI = APIClient.getClient().create(RequestAPI.class);
        Call<ResultSave> call=requestAPI.saveQuizz(id,result);
        final String id_temp = id;
        final String result_temp = result;
        for(int i=0;i<questions.length;i++){
            if(questions[i].getId() == id_temp){
                questions[i].setIs_save(1);
                questions[i].setResult(result_temp);
            }
        }
        call.enqueue(new Callback<ResultSave>() {
            @Override
            public void onResponse(Call<ResultSave> call, Response<ResultSave> response) {
                if (response.body().isStatus())System.out.println("Saved");

            }

            @Override
            public void onFailure(Call<ResultSave> call, Throwable t) {

            }
        });
    }

}
