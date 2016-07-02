package com.driff.android.photofeed.login.di;

import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.login.LoginInteractor;
import com.driff.android.photofeed.login.LoginInteractorImpl;
import com.driff.android.photofeed.login.LoginPresenter;
import com.driff.android.photofeed.login.LoginPresenterImpl;
import com.driff.android.photofeed.login.LoginRepository;
import com.driff.android.photofeed.login.LoginRepositoryImpl;
import com.driff.android.photofeed.login.ui.LoginView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by johnj on 29/6/2016.
 */
@Module
public class LoginModule {

    LoginView view;

    public LoginModule(LoginView view) {
        this.view = view;
    }

    @Provides
    @Singleton
    LoginView providesLoginView(){
        return this.view;
    }

    @Provides
    @Singleton
    LoginPresenter providesLoginPresenter(EventBus eventBus, LoginView loginView, LoginInteractor loginInteractor){
        return new LoginPresenterImpl(eventBus, loginView, loginInteractor);
    }

    @Provides
    @Singleton
    LoginInteractor providesLoginInteractor(LoginRepository loginRepository){
        return new LoginInteractorImpl(loginRepository);
    }

    @Provides
    @Singleton
    LoginRepository providesLoginRepository(EventBus eventBus, FirebaseAPI firebaseAPI){
        return new LoginRepositoryImpl(eventBus, firebaseAPI);
    }

}
