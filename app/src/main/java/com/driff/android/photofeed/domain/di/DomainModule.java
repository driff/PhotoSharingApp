package com.driff.android.photofeed.domain.di;

import android.content.Context;
import android.location.Geocoder;

import com.driff.android.photofeed.PhotoFeedApp;
import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.domain.Utils;
import com.firebase.client.Firebase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 29/6/2016.
 */
@Module
public class DomainModule {

    private String firebaseURL;

    public DomainModule(String firebaseURL) {
        this.firebaseURL = firebaseURL;
    }

    @Provides
    @Singleton
    FirebaseAPI providesFirebaseAPI(Firebase firebase){
        return new FirebaseAPI(firebase);
    }

    @Provides
    @Singleton
    Firebase providesFirebase(){
        return new Firebase(firebaseURL);
    }

    @Provides
    @Singleton
    String providesFirebaseURL(){
        return this.firebaseURL;
    }

    @Provides
    @Singleton
    Utils providesUtils(Geocoder geocoder){
        return new Utils(geocoder);
    }

    @Provides
    @Singleton
    Geocoder providesGeocoder(Context context){
        return new Geocoder(context);
    }

}
