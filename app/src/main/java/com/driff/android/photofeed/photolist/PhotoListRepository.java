package com.driff.android.photofeed.photolist;

import com.driff.android.photofeed.entities.Photo;

/**
 * Created by johnj on 1/7/2016.
 */
public interface PhotoListRepository {

    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);

}
