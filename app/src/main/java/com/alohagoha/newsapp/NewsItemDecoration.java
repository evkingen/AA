package com.alohagoha.newsapp;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NewsItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int offset = parent.getContext().getResources().getDimensionPixelSize(R.dimen.spacing_decorator);
        outRect.set(offset, offset, offset, offset);
    }
}
