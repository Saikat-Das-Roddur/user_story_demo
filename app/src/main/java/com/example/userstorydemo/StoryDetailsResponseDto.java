package com.example.userstorydemo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoryDetailsResponseDto {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("story_id")
        @Expose
        private String storyId;
        @SerializedName("store_id")
        @Expose
        private String storeId;
        @SerializedName("story_end")
        @Expose
        private String storyEnd;
        @SerializedName("user_ids")
        @Expose
        private List<String> userIds = null;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("views")
        @Expose
        private String views;
        @SerializedName("duration")
        @Expose
        private String duration;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoryId() {
            return storyId;
        }

        public void setStoryId(String storyId) {
            this.storyId = storyId;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoryEnd() {
            return storyEnd;
        }

        public void setStoryEnd(String storyEnd) {
            this.storyEnd = storyEnd;
        }

        public List<String> getUserIds() {
            return userIds;
        }

        public void setUserIds(List<String> userIds) {
            this.userIds = userIds;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getViews() {
            return views;
        }

        public void setViews(String views) {
            this.views = views;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }

}
