package com.driff.android.photofeed.main;

import android.location.Location;

/**
 * Created by johnj on 30/6/2016.
 */
public interface MainRepository {

    void logout();
    void uploadPhoto(Location location, String path);

}
