package com.driff.android.photofeed.login;

/**
 * Created by ykro.
 */
public interface LoginInteractor {
    void doSignIn(String email, String password);
    void doSignUp(String email, String password);
}
