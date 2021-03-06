package com.driff.android.photofeed.login;

import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.domain.FirebaseActionListenerCallback;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.login.events.LoginEvent;
import com.firebase.client.FirebaseError;


/**
 * Created by ykro.
 */
public class LoginRepositoryImpl implements LoginRepository {
    private EventBus eventBus;
    private FirebaseAPI firebase;

    public LoginRepositoryImpl(EventBus eventBus, FirebaseAPI firebase) {
        this.firebase = firebase;
        this.eventBus = eventBus;
    }

    @Override
    public void signUp(final String email, final String password) {
        firebase.signUp(email, password, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(LoginEvent.onSignUpSuccess);
                signIn(email, password);
            }

            @Override
            public void onError(FirebaseError error) {
                post(LoginEvent.onSignUpError, error.getMessage());
            }
        });
    }

    @Override
    public void signIn(String email, String password) {
        if (email != null && password != null) {
            firebase.login(email, password, new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebase.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, null, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    post(LoginEvent.onSignInError, error.getMessage());
                }
            });
        } else {
            firebase.checkForSession(new FirebaseActionListenerCallback() {
                @Override
                public void onSuccess() {
                    String email = firebase.getAuthEmail();
                    post(LoginEvent.onSignInSuccess, null, email);
                }

                @Override
                public void onError(FirebaseError error) {
                    post(LoginEvent.onFailedToRecoverSession);
                }
            });
        }
    }

    private void post(int type) {
        post(type, null);
    }

    private void post(int type, String errorMessage) {
        post(type, errorMessage, null);
    }

    private void post(int type, String errorMessage, String loggedUserEmail) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        loginEvent.setErrorMesage(errorMessage);
        loginEvent.setLoggedUserEmail(loggedUserEmail);
        eventBus.post(loginEvent);
    }

}
