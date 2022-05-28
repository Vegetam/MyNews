package com.francescomalagrino.mynews.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.francescomalagrino.mynews.Models.News;
import com.francescomalagrino.mynews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleSearchAdapter extends RecyclerView.Adapter<ArticleSearchAdapter.ArticleSearchViewHolder> {


    //Member variable
    private List<News> mNewsList;
    private LayoutInflater mInflater;
    private View.OnClickListener mClickListener;



    public static final String EXTRA_MESSAGE = "test";



    // data is passed into the constructor
    public ArticleSearchAdapter(Context context, List<News> newsList) {
        this.mInflater = LayoutInflater.from(context);
        this.mNewsList = newsList;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public ArticleSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_news, parent, false);
        RecyclerView.ViewHolder holder = new ArticleSearchViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });
        return new ArticleSearchViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ArticleSearchViewHolder holder, int position) {

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

    public News getNewsListPosition(int position) {
        return this.mNewsList.get(position);
    }



    // Set the limit size of the list to match the JSON queried item
    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    // stores and recycles views as they are scrolled off screen
    public static class ArticleSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView newsImage;
        TextView newsDescription, newsCategory, newsDate;
        WebView test;
        private Context context;

        ArticleSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            newsDescription = itemView.findViewById(R.id.newsDescription);
            newsCategory = itemView.findViewById(R.id.newsCategory);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsImage = itemView.findViewById(R.id.newsImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }
    public void setClickListener(View.OnClickListener callback) {
        mClickListener = callback;
    }
}
