package com.driff.android.photofeed.photomap.di;

import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.domain.Utils;
import com.driff.android.photofeed.entities.Photo;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.lib.base.ImageLoader;
import com.driff.android.photofeed.photomap.PhotoMapInteractor;
import com.driff.android.photofeed.photomap.PhotoMapInteractorImpl;
import com.driff.android.photofeed.photomap.PhotoMapPresenter;
import com.driff.android.photofeed.photomap.PhotoMapPresenterImpl;
import com.driff.android.photofeed.photomap.PhotoMapRepository;
import com.driff.android.photofeed.photomap.PhotoMapRepositoryImpl;
import com.driff.android.photofeed.photomap.ui.PhotoMapView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 1/7/2016.
 */
@Module
public class PhotoMapModule {

    private PhotoMapView view;

    public PhotoMapModule(PhotoMapView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    PhotoMapView providesPhotoMapView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoMapPresenter providesPhotoMapPresenter(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor){
        return new PhotoMapPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    PhotoMapInteractor providesPhotoMapInteractor(PhotoMapRepository repository){
        return new PhotoMapInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoMapRepository providesPhotoMapRepository(EventBus eventBus, FirebaseAPI firebaseAPI){
        return new PhotoMapRepositoryImpl(eventBus, firebaseAPI);
    }

}
