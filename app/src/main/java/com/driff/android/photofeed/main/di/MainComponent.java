package com.driff.android.photofeed.main.di;

import com.driff.android.photofeed.PhotoFeedAppModule;
import com.driff.android.photofeed.domain.di.DomainModule;
import com.driff.android.photofeed.lib.di.LibsModule;
import com.driff.android.photofeed.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johnj on 1/7/2016.
 */
@Singleton
@Component(modules = {MainModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface MainComponent {

    void inject(MainActivity activity);

}
