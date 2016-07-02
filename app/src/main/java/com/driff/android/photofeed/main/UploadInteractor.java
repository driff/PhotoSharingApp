package com.driff.android.photofeed.main;

import android.location.Location;

/**
 * Created by johnj on 30/6/2016.
 */
public interface UploadInteractor {

    void execute(Location location, String path);

}
