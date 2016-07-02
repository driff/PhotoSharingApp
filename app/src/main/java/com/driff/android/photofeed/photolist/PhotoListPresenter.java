package com.driff.android.photofeed.photolist;

import com.driff.android.photofeed.entities.Photo;
import com.driff.android.photofeed.photolist.events.PhotoListEvent;

/**
 * Created by johnj on 1/7/2016.
 */
public interface PhotoListPresenter {

    void onCreate();
    void onDestroy();
    void subscribe();
    void unsubscribe();
    void removePhoto(Photo photo);
    void onEventMainThread(PhotoListEvent event);

}
