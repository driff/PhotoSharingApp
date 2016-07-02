package com.driff.android.photofeed.domain;

import com.firebase.client.FirebaseError;

/**
 * Created by johnj on 29/6/2016.
 */
public interface FirebaseActionListenerCallback {

    void onSuccess();
    void onError(FirebaseError error);

}
