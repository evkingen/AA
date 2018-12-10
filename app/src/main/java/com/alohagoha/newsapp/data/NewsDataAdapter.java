package com.alohagoha.newsapp.data;

import android.view.ViewGroup;

import com.alohagoha.newsapp.data.model.NewsItemDTO;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataViewHolder> {

    private final List<NewsItemDTO> news = new ArrayList<>();
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public List<NewsItemDTO> getNews() {
        return news;
    }

    @NonNull
    @Override
    public NewsDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return NewsDataViewHolder.create(parent, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsDataViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void replaceItems(@NonNull List<NewsItemDTO> news) {
        this.news.clear();
        this.news.addAll(news);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
