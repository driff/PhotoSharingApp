package com.driff.android.photofeed.photolist.ui;

import com.driff.android.photofeed.entities.Photo;

/**
 * Created by johnj on 1/7/2016.
 */
public interface PhotoListView {

    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPhoto(Photo photo);
    void removePhoto(Photo photo);
    void onPhotosError(String error);

}
