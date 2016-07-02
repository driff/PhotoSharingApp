package com.driff.android.photofeed.photomap.di;

import com.driff.android.photofeed.PhotoFeedAppModule;
import com.driff.android.photofeed.domain.di.DomainModule;
import com.driff.android.photofeed.lib.di.LibsModule;
import com.driff.android.photofeed.photomap.ui.PhotoMapFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johnj on 1/7/2016.
 */
@Singleton
@Component(modules = {PhotoMapModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoMapComponent {

    void inject(PhotoMapFragment fragment);

}
