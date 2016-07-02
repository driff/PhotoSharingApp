package com.driff.android.photofeed.photomap;

import com.driff.android.photofeed.photomap.events.PhotoMapEvent;

/**
 * Created by johnj on 1/7/2016.
 */
public interface PhotoMapPresenter {

    void onCreate();
    void onDestroy();
    void subscribe();
    void unsubscribe();
    void onEventMainThread(PhotoMapEvent event);

}
