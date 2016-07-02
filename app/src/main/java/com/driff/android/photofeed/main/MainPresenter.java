package com.driff.android.photofeed.main;

import android.location.Location;

import com.driff.android.photofeed.main.events.MainEvent;

/**
 * Created by johnj on 30/6/2016.
 */
public interface MainPresenter {

    void onCreate();
    void onDestroy();

    void logout();
    void uploadPhoto(Location location, String path);
    void onEventMainThread(MainEvent event);

}
