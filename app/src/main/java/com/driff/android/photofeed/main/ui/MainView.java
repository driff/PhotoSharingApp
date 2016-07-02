package com.driff.android.photofeed.main.ui;

/**
 * Created by johnj on 30/6/2016.
 */
public interface MainView {

    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);

}
