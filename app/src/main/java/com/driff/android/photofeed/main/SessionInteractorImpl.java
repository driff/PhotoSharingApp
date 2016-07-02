package com.driff.android.photofeed.main;

/**
 * Created by johnj on 1/7/2016.
 */
public class SessionInteractorImpl implements SessionInteractor {

    MainRepository repository;

    public SessionInteractorImpl(MainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void logout() {
        repository.logout();
    }
}
