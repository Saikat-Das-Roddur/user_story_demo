package com.example.userstorydemo;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoryRepository {
    private StoryApiService service;
    private MutableLiveData<ResponseBody> createStoryLiveData;
    private MutableLiveData<ResponseBody> updateStoryLiveData;
    private MutableLiveData<StoryListResponseDto> storyListLiveData;
    private MutableLiveData<StoryDetailsResponseDto> storyDetailsLiveData;

    public StoryRepository(Context context) {
        service = Constants.getApiService(context).create(StoryApiService.class);
        createStoryLiveData = new MutableLiveData<>();
        updateStoryLiveData = new MutableLiveData<>();
        storyDetailsLiveData = new MutableLiveData<>();
        storyListLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<StoryListResponseDto> getStoryListLiveData() {
        return storyListLiveData;
    }

    public void getStoryList(String date){
        service.getStoryList(date).enqueue(new Callback<StoryListResponseDto>() {
            @Override
            public void onResponse(Call<StoryListResponseDto> call, Response<StoryListResponseDto> response) {
                Constants.sendResponse(storyListLiveData,response, StoryDetailsResponseDto.class);
                
            }

            @Override
            public void onFailure(Call<StoryListResponseDto> call, Throwable t) {
                Log.e("TAG", "onResponse: "+t.getMessage());
                storyListLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<StoryDetailsResponseDto> getStoryDetailsLiveData(){
        return storyDetailsLiveData;
    }

    public void getStoryDetails(HashMap<String,String> hashMap){
        service.getStoryDetails(hashMap).enqueue(new Callback<StoryDetailsResponseDto>() {
            @Override
            public void onResponse(Call<StoryDetailsResponseDto> call, Response<StoryDetailsResponseDto> response) {
                Constants.sendResponse(storyDetailsLiveData,response,StoryDetailsResponseDto.class);
            }

            @Override
            public void onFailure(Call<StoryDetailsResponseDto> call, Throwable t) {
                storyDetailsLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<ResponseBody> createStoryLiveData(){
        return createStoryLiveData;
    }

    public void createStory(HashMap<String, RequestBody> hashMap){
        service.createStory(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Constants.sendResponse(createStoryLiveData,response,Response.class);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                createStoryLiveData.postValue(null);
            }
        });
    }

    public MutableLiveData<ResponseBody> updateStoryLiveData(){
        return updateStoryLiveData;
    }

    public void updateStory(HashMap<String,String> hashMap){
        service.updateStory(hashMap).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Constants.sendResponse(updateStoryLiveData,response,ResponseBody.class);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                updateStoryLiveData.postValue(null);
            }
        });
    }


}
