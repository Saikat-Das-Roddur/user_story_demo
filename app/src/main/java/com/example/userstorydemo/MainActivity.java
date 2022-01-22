package com.example.userstorydemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import jp.shts.android.storiesprogressview.StoriesProgressView;

public class MainActivity extends AppCompatActivity implements StoriesProgressView.StoriesListener {

//    private ActivityMainBinding binding;
//    private StoryViewsBinding storyViewsBinding;
    private StoryViewModel viewModel;
    private StoriesProgressView storiesProgressView;
    private ImageView image;
    private final int[] resources = new int[]{
            R.drawable.ic_group_182,
            R.drawable.ic_user,
            R.drawable.ic_photo_black_48dp,
            R.drawable.ic_photo_camera_black_48dp,
            R.drawable.ic_user,
            R.drawable.ic_group_182,
    };
    long pressTime = 0L;
    long limit = 500L;

    // on below line we are creating variables for
    // our progress bar view and image view .

    // on below line we are creating a counter
    // for keeping count of our stories.
    private int counter = 0;
    private List<String> storyList = new ArrayList<>();
    private List<StoryDetailsResponseDto.Datum> storyDetails = new ArrayList<>();
    private HashMap<String,String> hashMap = new HashMap<>();

    private View.OnTouchListener onTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // inside on touch method we are
            // getting action on below line.
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:

                    // on action down when we press our screen
                    // the story will pause for specific time.
                    pressTime = System.currentTimeMillis();

                    // on below line we are pausing our indicator.
                    storiesProgressView.pause();
                    //storyViewsBinding.spvStories.pause();
                    return false;
                case MotionEvent.ACTION_UP:

                    // in action up case when user do not touches
                    // screen this method will skip to next image.
                    long now = System.currentTimeMillis();

                    // on below line we are resuming our progress bar for status.
                    storiesProgressView.resume();
                    //storyViewsBinding.spvStories.resume();

                    // on below line we are returning if the limit < now - presstime
                    return limit < now - pressTime;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        storyViewsBinding = StoryViewsBinding.bind(binding.getRoot());

        setContentView(R.layout.activity_main);
        storiesProgressView = findViewById(R.id.spvStories);
        image = findViewById(R.id.ivLayout);


        hashMap.put("story_id",getIntent().getStringExtra("story_id"));
        hashMap.put("date",new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));

        viewModel = new ViewModelProvider(this).get(StoryViewModel.class);
        viewModel.init();
        viewModel.getStoryDetailsLiveData().observe(this, storyResponseDto -> {
            storyList.clear();
            if (storyResponseDto == null) {
                //Log.e("TAG", "onCreate: "+storyList.size());
                return;
            }
            if (storyResponseDto.getData() != null) {

                storyDetails = storyResponseDto.getData();
                //Log.e("TAG", "onCreate: "+storyList.size());
                for (int i = 0; i < storyResponseDto.getData().size(); i++) {
                    storyList.add(BuildConfig.BASE_URL+"images/"+storyResponseDto.getData().get(i).getImage());
                }
                hashMap.clear();

                hashMap.put("id",storyDetails.get(counter).getId());
                hashMap.put("user_id",5+"");
                hashMap.put("date",new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date()));
                storiesProgressView.setStoriesCount(storyList.size()); //storyList.size();
                //storyViewsBinding.spvStories.setStoriesCount(resources.length); //storyList.size();
                storiesProgressView.setStoryDuration(3000L);
                //storyViewsBinding.spvStories.setStoryDuration(3000L);

                // on below line we are calling a method for set
                // on story listener and passing context to it.
                storiesProgressView.setStoriesListener(this);
                //storyViewsBinding.spvStories.setStoriesListener(this);

                // below line is use to start stories progress bar.
                storiesProgressView.startStories(counter);
                //storyViewsBinding.spvStories.startStories(counter);

                Glide.with(this)
                        .load(storyList.get(counter))
                        //storyList.get(counter)
                        .into(image);

                View viewReverse = findViewById(R.id.viewReverse);

                viewReverse.setOnClickListener(v -> {
                    storiesProgressView.reverse();
                });
                viewReverse.setOnTouchListener(onTouchListener);

                View viewSkip = findViewById(R.id.viewSkip);
                viewSkip.setOnClickListener(v -> {
                    storiesProgressView.skip();
                });
                viewSkip.setOnTouchListener(onTouchListener);


            }
        });

//        viewModel.updateStoryLiveData().observe(this, responseBody -> {
//            if (responseBody!= null){
//                f
//            }
//        });

//        storiesProgressView = findViewById(R.id.spvStories);
//        image = findViewById(R.id.ivLayout);
//        storiesProgressView.setStoriesCount(resources.length); //storyList.size();
//        //storyViewsBinding.spvStories.setStoriesCount(resources.length); //storyList.size();
//        storiesProgressView.setStoryDuration(3000L);
//        //storyViewsBinding.spvStories.setStoryDuration(3000L);
//
//        // on below line we are calling a method for set
//        // on story listener and passing context to it.
//        storiesProgressView.setStoriesListener(this);
//        //storyViewsBinding.spvStories.setStoriesListener(this);
//
//        // below line is use to start stories progress bar.
//        storiesProgressView.startStories(counter);
//        //storyViewsBinding.spvStories.startStories(counter);
//
//        Glide.with(this)
//                .load(resources[counter])
//                //storyList.get(counter)
//                .into(image);
//
//        View viewReverse = findViewById(R.id.viewReverse);
//
//        viewReverse.setOnClickListener(v -> {
//            storiesProgressView.reverse();
//        });
//        viewReverse.setOnTouchListener(onTouchListener);
//
//        View viewSkip = findViewById(R.id.viewSkip);
//        viewSkip.setOnClickListener(v -> {
//            storiesProgressView.skip();
//        });
//        viewSkip.setOnTouchListener(onTouchListener);




        //viewModel.getStoryDetails(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.getStoryDetails(hashMap);
    }

    @Override
    public void onNext() {
        Log.e("TAG", "onNext: "+counter );
        hashMap.put("id",storyDetails.get(counter).getId());
        viewModel.updateStory(hashMap);
        Glide.with(this)
                .load(storyList.get(++counter))
                .into(image);

       // Log.e("TAG", "onNext-->: "+counter );


    }

    @Override
    public void onPrev() {
        Log.e("TAG", "onPrev: "+counter );
        if ((counter - 1) < 0) return;

        Glide.with(this)
                .load(storyList.get(--counter))
                .into(image);

    }

    @Override
    public void onComplete() {
        Log.e("TAG", "onComplete: "+counter );
        hashMap.put("id",storyDetails.get(counter).getId());
        viewModel.updateStory(hashMap);
        finish();

    }

    @Override
    protected void onDestroy() {
        // in on destroy method we are destroying
        // our stories progress view.
        storiesProgressView.destroy();
        super.onDestroy();
    }
}