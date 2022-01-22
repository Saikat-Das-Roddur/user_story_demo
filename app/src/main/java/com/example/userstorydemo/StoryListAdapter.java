package com.example.userstorydemo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userstorydemo.databinding.RowStoryLayoutBinding;

import java.util.List;

public class StoryListAdapter extends RecyclerView.Adapter<StoryListAdapter.ViewHolder> {
    List<StoryListResponseDto.Datum> list;
    Context context;

    public StoryListAdapter(List<StoryListResponseDto.Datum> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public StoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(RowStoryLayoutBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StoryListAdapter.ViewHolder holder, int position) {
        holder.binding.getRoot().setOnClickListener(v -> {
            context.startActivity(new Intent(context, MainActivity.class)
                    .putExtra("story_id", list.get(position).getId()));
        });

        Drawable drawable = context.getResources().getDrawable(R.drawable.circle_thick_border);
        //drawable.setTint(Color.GRAY);
        holder.binding.ivImage.setBackground(drawable);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowStoryLayoutBinding binding;

        public ViewHolder(@NonNull RowStoryLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
