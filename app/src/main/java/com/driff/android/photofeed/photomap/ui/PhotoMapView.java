package com.driff.android.photofeed.photomap.ui;

import com.driff.android.photofeed.entities.Photo;

/**
 * Created by johnj on 1/7/2016.
 */
public interface PhotoMapView {

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);

}
