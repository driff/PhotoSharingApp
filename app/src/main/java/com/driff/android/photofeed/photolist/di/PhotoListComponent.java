package com.driff.android.photofeed.photolist.di;

import com.driff.android.photofeed.PhotoFeedAppModule;
import com.driff.android.photofeed.domain.di.DomainModule;
import com.driff.android.photofeed.lib.di.LibsModule;
import com.driff.android.photofeed.main.ui.MainActivity;
import com.driff.android.photofeed.photolist.ui.PhotoListFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johnj on 1/7/2016.
 */
@Singleton
@Component(modules = {PhotoListModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface PhotoListComponent {

    void inject(PhotoListFragment fragment);

}
