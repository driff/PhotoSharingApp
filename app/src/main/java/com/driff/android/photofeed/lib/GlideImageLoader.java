package com.driff.android.photofeed.lib;

import android.widget.ImageView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.driff.android.photofeed.lib.base.ImageLoader;

/**
 * Created by johnj on 17/6/2016.
 */
public class GlideImageLoader implements ImageLoader {

    private RequestManager glideRequestManager;
    private RequestListener onFinishedLoadingListener;

    public GlideImageLoader(RequestManager glideRequestManager) {
        this.glideRequestManager = glideRequestManager;
    }

    @Override
    public void load(ImageView imgView, String URL) {
        if (onFinishedLoadingListener != null) {
            glideRequestManager.load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
                    .listener(onFinishedLoadingListener).override(600, 400).into(imgView);
        }else{
            glideRequestManager.load(URL)
                    .diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop()
                    .override(700, 700).into(imgView);
        }
    }
}
