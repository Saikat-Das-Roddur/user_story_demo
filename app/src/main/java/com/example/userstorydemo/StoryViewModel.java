package com.example.userstorydemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class StoryViewModel extends AndroidViewModel {
    StoryRepository repository;

    public StoryViewModel(@NonNull Application application) {
        super(application);
    }

    public void init(){
        repository = new StoryRepository(getApplication().getBaseContext());
    }

    public MutableLiveData<StoryDetailsResponseDto> getStoryDetailsLiveData(){
        return repository.getStoryDetailsLiveData();
    }

    public void getStoryDetails(HashMap<String,String> hashMap){
        repository.getStoryDetails(hashMap);
    }

    public MutableLiveData<StoryListResponseDto> getStoryListLiveData(){
        return repository.getStoryListLiveData();
    }

    public void getStoryList(String date){
        repository.getStoryList(date);
    }

    public MutableLiveData<ResponseBody> createStoryLiveData(){
        return repository.createStoryLiveData();
    }

    public void createStory(HashMap<String, RequestBody> hashMap){
        repository.createStory(hashMap);
    }

    public MutableLiveData<ResponseBody> updateStoryLiveData(){
        return repository.createStoryLiveData();
    }

    public void updateStory(HashMap<String,String> hashMap){
        repository.updateStory(hashMap);
    }
}
