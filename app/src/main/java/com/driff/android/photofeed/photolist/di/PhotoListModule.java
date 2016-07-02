package com.driff.android.photofeed.photolist.di;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.domain.Utils;
import com.driff.android.photofeed.entities.Photo;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.lib.base.ImageLoader;
import com.driff.android.photofeed.photolist.PhotoListInteractor;
import com.driff.android.photofeed.photolist.PhotoListInteractorImpl;
import com.driff.android.photofeed.photolist.PhotoListPresenter;
import com.driff.android.photofeed.photolist.PhotoListPresenterImpl;
import com.driff.android.photofeed.photolist.PhotoListRepository;
import com.driff.android.photofeed.photolist.PhotoListRepositoryImpl;
import com.driff.android.photofeed.photolist.ui.PhotoListView;
import com.driff.android.photofeed.photolist.ui.adapters.OnItemClickListener;
import com.driff.android.photofeed.photolist.ui.adapters.PhotoListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 1/7/2016.
 */
@Module
public class PhotoListModule {

    private PhotoListView view;
    OnItemClickListener onItemClickListener;

    public PhotoListModule(PhotoListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    PhotoListView providesPhotoListView(){
        return this.view;
    }

    @Provides
    @Singleton
    PhotoListPresenter providesPhotoListPresenter(EventBus eventBus, PhotoListView view, PhotoListInteractor interactor){
        return new PhotoListPresenterImpl(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    PhotoListInteractor providesPhotoListInteractor(PhotoListRepository repository){
        return new PhotoListInteractorImpl(repository);
    }

    @Provides
    @Singleton
    PhotoListRepository providesPhotoListRepository(EventBus eventBus, FirebaseAPI firebaseAPI){
        return new PhotoListRepositoryImpl(eventBus, firebaseAPI);
    }

    @Provides
    @Singleton
    PhotoListAdapter providesPhotoListAdapter(Utils utils, List<Photo> photoList, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new PhotoListAdapter(utils, photoList, imageLoader, onItemClickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.onItemClickListener;
    }

    @Provides
    @Singleton
    List<Photo> providesPhotoList(){
        return new ArrayList<Photo>();
    }

}
