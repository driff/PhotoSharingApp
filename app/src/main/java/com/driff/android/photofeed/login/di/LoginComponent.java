package com.driff.android.photofeed.login.di;

import com.driff.android.photofeed.PhotoFeedAppModule;
import com.driff.android.photofeed.domain.di.DomainModule;
import com.driff.android.photofeed.lib.di.LibsModule;
import com.driff.android.photofeed.login.ui.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by johnj on 29/6/2016.
 */
@Singleton
@Component(modules = {LoginModule.class, DomainModule.class, LibsModule.class, PhotoFeedAppModule.class})
public interface LoginComponent {
    void inject(LoginActivity activity);
}
