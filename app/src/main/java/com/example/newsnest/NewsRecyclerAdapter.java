package com.example.newsnest;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kwabenaberko.newsapilib.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsRecyclerAdapter extends RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHoldeer> {
    List<Article>articleList;
    NewsRecyclerAdapter(List<Article> articleList){
            this.articleList=articleList;

        }
    @NonNull
    @Override
    public NewsViewHoldeer onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_recycler_row, parent,  false);
    return new NewsViewHoldeer(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHoldeer holder, int position) {
            Article article= articleList.get(position);
            holder.titleTextView.setText(article.getTitle());
            holder.sourceTextView.setText(article.getSource().getName());
            Picasso.get().load(article.getUrlToImage())
                    .error(R.drawable.no_image_icon)
                    .into(holder.imageView);
                    holder.itemView.setOnClickListener((v->{
                        Intent intent =new Intent(v.getContext(),NewsViewfull.class);
                        intent.putExtra("url",article.getUrl());
                        v.getContext().startActivity(intent);

        }));


    }
        void updateData(List<Article>data){
        articleList.clear();
        articleList.addAll(data);
        }
    @Override
    public int getItemCount() {
        return articleList.size();
    }


    class NewsViewHoldeer extends RecyclerView.ViewHolder{
        TextView titleTextView,sourceTextView;
        ImageView imageView;

        public NewsViewHoldeer(@NonNull View itemView) {
            super(itemView);
            titleTextView=itemView.findViewById(R.id.article_title);
            sourceTextView=itemView.findViewById(R.id.article_source);
            titleTextView=itemView.findViewById(R.id.article_title);
            imageView= itemView.findViewById(R.id.article_image_view);
                   }
    }
}
