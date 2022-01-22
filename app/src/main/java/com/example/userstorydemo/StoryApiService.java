package com.example.userstorydemo;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface StoryApiService {
    @GET("api/user_story/get_story.php")
    Call<StoryListResponseDto> getStoryList(@Query("date") String date);

    @GET("api/user_story/get_story_details.php")
    Call<StoryDetailsResponseDto> getStoryDetails(@QueryMap HashMap<String, String> hashMap);

    @POST("api/user_story/create_story.php")
    @Multipart
    Call<ResponseBody> createStory(@PartMap HashMap<String, RequestBody> hashMap);

    @POST("api/user_story/update_story.php")
    @FormUrlEncoded
    Call<ResponseBody> updateStory(@FieldMap HashMap<String, String> hashMap);


}
