package interfaces;

import model.ActiveResult;
import model.ChapterCreateResult;
import model.FinishResult;
import model.HistorResult;
import model.Result;
import model.ResultChapter;
import model.ResultFinsih;
import model.ResultListUser;
import model.ResultNews;
import model.ResultSave;
import model.RoleCreateResult;
import model.RoleResultList;
import model.TDMUNewsResult;
import model.User;
import model.getQuestion;
import model.getQuiz;
import model.resAdminRegister;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RequestAPI {

    @FormUrlEncoded
    @POST("auth/signup")
    Call<Result> signUp(@Field("first_name") String firstname, @Field("last_name") String lastname, @Field("email") String email, @Field("password") String password,@Field("repeatPassword") String repassword);

    @FormUrlEncoded
    @POST("auth/login")
    Call<Result> login(@Field("email") String email, @Field("password") String password);
    @GET("auth/get-otp")
    Call<Result>getOTP(@Query("email") String email, @Query("type") String type);
    @FormUrlEncoded
    @PUT("auth/new-password")
    Call<Result> resetPassword(@Field("email") String email,@Field("otp") String otp,@Field("password") String password,@Field("repeatPassword") String repassword);
    @GET("admin/user/detail?")
    Call<Result> getUserById(@Query("id") String id);

    @FormUrlEncoded
    @PUT("admin/user/update")
    Call<Result> updateUser(@Field("id") String id,@Field("first_name")String first_name,@Field("last_name") String last_name,@Field("phone_number") String phone_number,@Field("address") String address,@Field("birth_day")String birthday);
    @GET("news")
    Call<ResultNews> getNews();
    @GET("news/news-university?page=10")
    Call<TDMUNewsResult> getTDMUNews();
    @GET("admin/chapter/list?")
    Call<ResultChapter> getListChapter(@Query("sort") String sort);
    @GET("admin/question/getQuizz?")
    Call<getQuiz> getQuizz(@Query("user_id") String user_id);
    @GET("admin/question/getQuestion?")
    Call<getQuestion> getQuestion(@Query("id") String id);
    @FormUrlEncoded
    @POST("admin/question/saveQuizz")
    Call<ResultSave> saveQuizz(@Field("id") String id,@Field("result") String result);
    @FormUrlEncoded
    @POST("admin/question/takeQuizz")
    Call<getQuestion> takeQuizz(@Field("chapter_id") String chapter_id,@Field("user_id") String user_id);
    @FormUrlEncoded
    @POST("admin/question/submit")
    Call<FinishResult> getResultFinish(@Field("user_id") String user_id);
    @GET("admin/question/history?")
    Call<HistorResult> getHistoryExam(@Query("user_id") String user_id);
    @GET("admin/user/list?")
    Call<ResultListUser> getAllUser(@Query("created_by") int role);
    @DELETE("admin/user/delete?")
    Call<Result> deleteUserById(@Query("id") String id);
    @FormUrlEncoded
    @POST("admin/user/create")
    Call<resAdminRegister> AdminCreateUser(@Field("first_name") String firstname, @Field("last_name") String lastname, @Field("email") String email, @Field("password") String password, @Field("role_id") String role_id);
    @FormUrlEncoded
    @POST("auth/active-account")
    Call<ActiveResult> activeUser(@Field("email") String email, @Field("otp") String otp);
    @FormUrlEncoded
    @POST("admin/chapter/create")
    Call<ChapterCreateResult> createChapter(@Field("name") String name);
    @GET("admin/role/list?sort=old")
    Call<RoleResultList> getListRole();
    @FormUrlEncoded
    @POST("admin/role/create")
    Call<RoleCreateResult> createRole(@Field("name") String roleName);


}
