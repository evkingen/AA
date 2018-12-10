package com.alohagoha.newsapp.data;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alohagoha.newsapp.R;
import com.alohagoha.newsapp.data.model.NewsItemDTO;
import com.bumptech.glide.Glide;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public final class NewsDataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private TextView category;
    private TextView title;
    private TextView previewText;
    private TextView datePublished;
    private ImageView headerImage;
    private NewsDataAdapter.OnItemClickListener listener;

    public static NewsDataViewHolder create(@NonNull ViewGroup parent, NewsDataAdapter.OnItemClickListener listener) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new NewsDataViewHolder(v, listener);
    }

    private NewsDataViewHolder(@NonNull View view, NewsDataAdapter.OnItemClickListener listener) {
        super(view);
        this.listener = listener;
        view.setOnClickListener(this);
        findViewsById(view);
    }

    public void bind(NewsItemDTO item) {
        category.setText(item.getCategory());
        title.setText(item.getTitle());
        previewText.setText(item.getPreviewText());
        datePublished.setText(DateUtils.getRelativeDateTimeString(datePublished.getContext(),
                item.getPublishDate().getTime(), DateUtils.MINUTE_IN_MILLIS,
                DateUtils.WEEK_IN_MILLIS, 0));
        if (!item.getMultimedias().isEmpty())
            Glide.with(headerImage.getContext()).load(item.getMultimedias().get(0).getImageUrl()).into(headerImage);
    }

    @Override
    public void onClick(View view) {
        int position = getAdapterPosition();
        if (listener != null && position != RecyclerView.NO_POSITION) {
            listener.onItemClick(position);
        }
    }

    private void findViewsById(@NonNull View itemView) {
        category = itemView.findViewById(R.id.category);
        title = itemView.findViewById(R.id.title);
        previewText = itemView.findViewById(R.id.text_preview);
        datePublished = itemView.findViewById(R.id.date_publish);
        headerImage = itemView.findViewById(R.id.image_head);
    }
}