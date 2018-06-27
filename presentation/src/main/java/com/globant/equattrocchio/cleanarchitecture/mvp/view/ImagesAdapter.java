package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.data.response.Image;

import java.util.List;

public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ViewHolder> {

    private Context context;
    private List<Image> images;

    public ImagesAdapter(Context context, List<Image> images) {
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_images, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        Glide.with(context)
                .load(images.get(i).getUrl())
                .into(viewHolder.ivImage);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;

        ViewHolder(View v) {
            super(v);
            ivImage = v.findViewById(R.id.image_view_image);
        }
    }
}
