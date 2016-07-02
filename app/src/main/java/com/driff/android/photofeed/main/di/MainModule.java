package com.driff.android.photofeed.main.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.lib.base.ImageStorage;
import com.driff.android.photofeed.main.MainPresenter;
import com.driff.android.photofeed.main.MainPresenterImpl;
import com.driff.android.photofeed.main.MainRepository;
import com.driff.android.photofeed.main.MainRepositoryImpl;
import com.driff.android.photofeed.main.SessionInteractor;
import com.driff.android.photofeed.main.SessionInteractorImpl;
import com.driff.android.photofeed.main.UploadInteractor;
import com.driff.android.photofeed.main.UploadInteractorImpl;
import com.driff.android.photofeed.main.ui.MainView;
import com.driff.android.photofeed.main.ui.adapter.MainSectionsPagerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 1/7/2016.
 */
@Module
public class MainModule {

    private MainView view;
    private String[] titles;
    private Fragment[] fragments;
    private FragmentManager fragmentManager;

    public MainModule(MainView view, String[] titles, Fragment[] fragments, FragmentManager fragmentManager) {
        this.view = view;
        this.titles = titles;
        this.fragments = fragments;
        this.fragmentManager = fragmentManager;
    }

    @Provides
    @Singleton
    MainView providesMainView(){
        return this.view;
    }

    @Provides
    @Singleton
    MainPresenter providesMainPresenter(MainView view, EventBus eventBus, UploadInteractor uploadInteractor, SessionInteractor sessionInteractor){
        return new MainPresenterImpl(view, eventBus, uploadInteractor, sessionInteractor);
    }

    @Provides
    @Singleton
    UploadInteractor providesUploadInteractor(MainRepository repository){
        return new UploadInteractorImpl(repository);
    }

    @Provides
    @Singleton
    SessionInteractor providesSessionInteractor(MainRepository repository){
        return new SessionInteractorImpl(repository);
    }

    @Provides
    @Singleton
    MainRepository providesMainRepository(EventBus eventBus, FirebaseAPI firebaseAPI, ImageStorage imageStorage){
        return new MainRepositoryImpl(eventBus, firebaseAPI, imageStorage);
    }

    @Provides
    @Singleton
    MainSectionsPagerAdapter providesMainSectionsPagerAdapter(FragmentManager fm, String[] titles, Fragment[] fragments){
        return new MainSectionsPagerAdapter(fm, titles, fragments);
    }

    @Provides
    @Singleton
    FragmentManager providesAdapterFragmentManager(){
        return this.fragmentManager;
    }

    @Provides
    @Singleton
    Fragment[] providesFragmentArrayForAdapter(){
        return this.fragments;
    }

    @Provides
    @Singleton
    String[] providesStringArrayForAdapter(){
        return this.titles;
    }

}
