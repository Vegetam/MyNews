package com.francescomalagrino.mynews.views;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.francescomalagrino.mynews.Controllers.Activities.WebViewActivity;
import com.francescomalagrino.mynews.Models.News;
import com.francescomalagrino.mynews.R;
import com.squareup.picasso.Picasso;


import java.util.List;

public class TopStoryAdapter extends RecyclerView.Adapter<TopStoryAdapter.TopStoryViewHolder> {



    //Member variable
    private List<News> mNewsList;
    private LayoutInflater mInflater;


    // data is passed into the constructor
    public TopStoryAdapter(Context context, List<News> newsList){
        this.mInflater = LayoutInflater.from(context);
        this.mNewsList = newsList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_news, parent, false);
        return new TopStoryViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull TopStoryViewHolder holder, int position) {

        News news = mNewsList.get(position);

        //Set the JSON queried data in the respective section
        holder.newsDescription.setText(news.getTitle());
        holder.newsCategory.setText(news.getSection());
        holder.newsDate.setText(news.getDate());
        String imgUrl = news.getImageUrl();
        if(imgUrl.isEmpty()){
            holder.newsImage.setImageResource(R.mipmap.ic_launcher);
        }else {
            Picasso.get().load(imgUrl).into(holder.newsImage);
        }
    }


    // Set the limit size of the list to match the JSON queried item
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public static class TopStoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView newsImage;
        TextView newsDescription, newsCategory, newsDate;

        TopStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsCategory = itemView.findViewById(R.id.newsCategory);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsImage = itemView.findViewById(R.id.newsImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(view.getContext(), WebViewActivity.class);
            intent.putExtra(WebViewActivity.WebUrl,"http://wwww.google.it");
            view.getContext().startActivities(new Intent[]{intent});

        }
    }
}
