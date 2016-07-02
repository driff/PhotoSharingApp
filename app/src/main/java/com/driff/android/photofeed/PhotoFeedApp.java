package com.driff.android.photofeed;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.driff.android.photofeed.domain.di.DomainModule;
import com.driff.android.photofeed.lib.di.LibsModule;
import com.driff.android.photofeed.login.di.DaggerLoginComponent;
import com.driff.android.photofeed.login.di.LoginComponent;
import com.driff.android.photofeed.login.di.LoginModule;
import com.driff.android.photofeed.login.ui.LoginView;
import com.driff.android.photofeed.main.di.DaggerMainComponent;
import com.driff.android.photofeed.main.di.MainComponent;
import com.driff.android.photofeed.main.di.MainModule;
import com.driff.android.photofeed.main.ui.MainView;
import com.driff.android.photofeed.photolist.di.DaggerPhotoListComponent;
import com.driff.android.photofeed.photolist.di.PhotoListComponent;
import com.driff.android.photofeed.photolist.di.PhotoListModule;
import com.driff.android.photofeed.photolist.ui.PhotoListFragment;
import com.driff.android.photofeed.photolist.ui.PhotoListView;
import com.driff.android.photofeed.photolist.ui.adapters.OnItemClickListener;
import com.driff.android.photofeed.photomap.di.DaggerPhotoMapComponent;
import com.driff.android.photofeed.photomap.di.PhotoMapComponent;
import com.driff.android.photofeed.photomap.di.PhotoMapModule;
import com.driff.android.photofeed.photomap.ui.PhotoMapFragment;
import com.driff.android.photofeed.photomap.ui.PhotoMapView;
import com.firebase.client.Firebase;

/**
 * Created by johnj on 29/6/2016.
 */
public class PhotoFeedApp extends Application{

    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UserPrefs";
    private final static String FIREBASE_URL = "https://driff-photo-share.firebaseio.com/";

    private DomainModule domainModule;
    private PhotoFeedAppModule photoFeedAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        photoFeedAppModule = new PhotoFeedAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view){
        return DaggerLoginComponent.builder().photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule).libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view)).build();
    }

    public MainComponent getMainComponent(MainView view, String[] titles, Fragment[] fragments, FragmentManager fragmentManager){
        return DaggerMainComponent.builder().photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule).libsModule(new LibsModule(null))
                .mainModule(new MainModule(view, titles, fragments, fragmentManager)).build();
    }

    public PhotoListComponent getPhotoListComponent(PhotoListFragment fragment, PhotoListView view, OnItemClickListener clickListener){
        return DaggerPhotoListComponent.builder().photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule).libsModule(new LibsModule(fragment))
                .photoListModule(new PhotoListModule(view, clickListener)).build();
    }

    public PhotoMapComponent getPhotoMapComponent(PhotoMapFragment fragment, PhotoMapView view) {
        return DaggerPhotoMapComponent.builder().photoFeedAppModule(photoFeedAppModule)
                .domainModule(domainModule).libsModule(new LibsModule(fragment))
                .photoMapModule(new PhotoMapModule(view)).build();
    }
}
