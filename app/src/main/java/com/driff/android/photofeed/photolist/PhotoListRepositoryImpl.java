package com.driff.android.photofeed.photolist;

import com.driff.android.photofeed.domain.FirebaseAPI;
import com.driff.android.photofeed.domain.FirebaseActionListenerCallback;
import com.driff.android.photofeed.domain.FirebaseEventListenerCallback;
import com.driff.android.photofeed.entities.Photo;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.photolist.events.PhotoListEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by johnj on 1/7/2016.
 */
public class PhotoListRepositoryImpl implements PhotoListRepository{

    EventBus eventBus;
    FirebaseAPI firebaseAPI;

    public PhotoListRepositoryImpl(EventBus eventBus, FirebaseAPI firebaseAPI) {
        this.eventBus = eventBus;
        this.firebaseAPI = firebaseAPI;
    }

    @Override
    public void subscribe() {
        firebaseAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {

            }
            @Override
            public void onError(FirebaseError error) {
                if(error != null){
                    post(PhotoListEvent.READ_EVENT, error.getMessage());
                }else{
                    post(PhotoListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());
                String email = firebaseAPI.getAuthEmail();
                boolean publishedByMe= photo.getEmail().equals(email);
                photo.setPublishedByMe(publishedByMe);
                post(PhotoListEvent.READ_EVENT, photo);
            }

            @Override
            public void onChildRemoved(DataSnapshot snapshot) {
                Photo photo = snapshot.getValue(Photo.class);
                photo.setId(snapshot.getKey());
                post(PhotoListEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onCancelled(FirebaseError error) {
                post(PhotoListEvent.READ_EVENT, error.getMessage());
            }
        });
    }

    private void post(int type, Photo photo){
        post(type, null, photo);
    }
    private void post(int type, String error){
        post(type, error ,null);
    }

    private void post(int readEvent, String error, Photo photo) {
        PhotoListEvent event = new PhotoListEvent();
        event.setType(readEvent);
        event.setError(error);
        event.setPhoto(photo);
        eventBus.post(event);
    }

    @Override
    public void unsubscribe() {
        firebaseAPI.unsubscribe();
    }

    @Override
    public void removePhoto(final Photo photo) {
        firebaseAPI.remove(photo, new FirebaseActionListenerCallback() {
            @Override
            public void onSuccess() {
                post(PhotoListEvent.DELETE_EVENT, photo);
            }

            @Override
            public void onError(FirebaseError error) {
                post(PhotoListEvent.DELETE_EVENT, error.getMessage());
            }
        });
    }
}
