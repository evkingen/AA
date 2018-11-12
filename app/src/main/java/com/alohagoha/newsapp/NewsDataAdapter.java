package com.alohagoha.newsapp;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alohagoha.newsapp.data.NewsItem;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsDataAdapter extends RecyclerView.Adapter<NewsDataAdapter.ViewHolder> {

    private List<NewsItem> news;
    private OnItemClickListener listener;

    NewsDataAdapter(List<NewsItem> news, OnItemClickListener listener) {
        this.news = news;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView category;
        private final TextView title;
        private final TextView previewText;
        private final TextView datePublished;
        private final ImageView headerImage;

        public ViewHolder(View view) {
            super(view);
            view.setOnClickListener(this);
            category = view.findViewById(R.id.category);
            title = view.findViewById(R.id.title);
            previewText = view.findViewById(R.id.text_preview);
            datePublished = view.findViewById(R.id.date_publish);
            headerImage = view.findViewById(R.id.image_head);
        }

        public void bind(NewsItem item) {
            category.setText(item.getCategory().getName());
            title.setText(item.getTitle());
            previewText.setText(item.getPreviewText());
            datePublished.setText(DateUtils.getRelativeDateTimeString(datePublished.getContext(),
                    item.getPublishDate().getTime(), DateUtils.MINUTE_IN_MILLIS,
                    DateUtils.WEEK_IN_MILLIS, 0));
            Glide.with(headerImage.getContext()).load(item.getImageUrl()).into(headerImage);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (listener != null && position != RecyclerView.NO_POSITION) {
                listener.onItemClick(news.get(position));
            }
        }
    }
}
