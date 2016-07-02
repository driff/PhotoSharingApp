package com.driff.android.photofeed.photolist.ui.adapters;

import android.widget.ImageView;

import com.driff.android.photofeed.entities.Photo;

/**
 * Created by johnj on 1/7/2016.
 */
public interface OnItemClickListener {

    void onPlaceClick(Photo photo);
    void onSharedClick(Photo photo, ImageView img);
    void onDeleteClick(Photo photo);

}
