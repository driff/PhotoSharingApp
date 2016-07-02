package com.driff.android.photofeed.lib.base;

import java.io.File;

/**
 * Created by johnj on 29/6/2016.
 */
public interface ImageStorage {

    String getImageUrl(String id);
    void upload(File file, String id, ImageStorageFinishedListener listener);

}
