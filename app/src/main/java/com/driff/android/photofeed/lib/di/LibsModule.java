package com.driff.android.photofeed.lib.di;


import android.content.Context;
import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.cloudinary.Cloudinary;
import com.driff.android.photofeed.domain.Utils;
import com.driff.android.photofeed.lib.CloudinaryImageStorage;
import com.driff.android.photofeed.lib.GlideImageLoader;
import com.driff.android.photofeed.lib.GreenRobotEventBus;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.lib.base.ImageLoader;
import com.driff.android.photofeed.lib.base.ImageStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 18/6/2016.
 */
@Module
public class LibsModule {

    private Fragment fragment;
    public LibsModule(Fragment fragment){
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager){
        return new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment){
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment(){
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus){
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus(){
        return org.greenrobot.eventbus.EventBus.getDefault();
    }

    @Provides
    @Singleton
    ImageStorage providesImageStorage(Cloudinary cloudinary){
        return new CloudinaryImageStorage(cloudinary);
    }

    @Provides
    @Singleton
    Cloudinary providesCloudinary(Context context){
        return new Cloudinary(com.cloudinary.android.Utils.cloudinaryUrlFromContext(context));
    }

}
