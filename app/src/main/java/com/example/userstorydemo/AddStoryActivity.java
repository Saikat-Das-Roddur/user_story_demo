package com.example.userstorydemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.example.userstorydemo.databinding.ActivityAddStoryBinding;
import com.example.userstorydemo.databinding.ActivityMainBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AddStoryActivity extends AppCompatActivity {
    private ActivityAddStoryBinding binding;
    private StoryViewModel viewModel;
    private List<StoryListResponseDto.Datum> list = new ArrayList<>();
    private HashMap<String, String> hashMap = new HashMap<>();
    private StoryListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddStoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        adapter = new StoryListAdapter(list, this);
        binding.rvStory.setAdapter(adapter);
        binding.rvStory.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        binding.btAddStory.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .maxResultSize(1024, 1024)
                    .start();
        });


        viewModel = new ViewModelProvider(this).get(StoryViewModel.class);
        viewModel.init();
        viewModel.createStoryLiveData().observe(this, response -> {
            if (response == null) {
                return;
            } else {
                Toast.makeText(this, "Hahaha", Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getStoryListLiveData().observe(this, storyListResponseDto -> {
            list.clear();
            if (storyListResponseDto == null) {
                return;
            }
            if (storyListResponseDto.getStatusCode() == 200) {
                list.addAll(storyListResponseDto.getData());
                adapter.notifyDataSetChanged();
            }
        });

        Log.e("TAG", "onCreate: " + new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

        //viewModel.getStoryList(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            createFileToImage(data.getData());
        }
    }

    private void createFileToImage(Uri uri) {
        HashMap<String, RequestBody> hashMap = new HashMap<>();

        RequestBody storeIdRB = RequestBody.create(MediaType.parse("text/plain"), 1 + "");
        RequestBody durationRB = RequestBody.create(MediaType.parse("text/plain"), 1 + "");

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);

        String endDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(calendar.getTime());

        Log.e("TAG", "createFileToImage: "+endDate );

        RequestBody endDateRB = RequestBody.create(MediaType.parse("text/plain"), endDate);

        hashMap.put("store_id", storeIdRB);
        hashMap.put("story_end", endDateRB);
        hashMap.put("duration", durationRB);


        File f = new File(getCacheDir(), "product_image_" + System.currentTimeMillis());

        try {
            f.createNewFile();
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();

            FileOutputStream fos = new FileOutputStream(f);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpg"), f);

            hashMap.put("image\"; filename=\"product_image_" + System.currentTimeMillis() + ".jpg\"", requestFile);

            viewModel.createStory(hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getStoryList(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
    }
}