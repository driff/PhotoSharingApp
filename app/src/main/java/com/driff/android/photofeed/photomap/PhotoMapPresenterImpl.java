package com.driff.android.photofeed.photomap;

import com.driff.android.photofeed.entities.Photo;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.photomap.events.PhotoMapEvent;
import com.driff.android.photofeed.photomap.ui.PhotoMapView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by johnj on 1/7/2016.
 */
public class PhotoMapPresenterImpl implements PhotoMapPresenter{

    private static final String EMTY_LIST = "List is Empty";
    private EventBus eventBus;
    private PhotoMapView view;
    private PhotoMapInteractor interactor;

    public PhotoMapPresenterImpl(EventBus eventBus, PhotoMapView view, PhotoMapInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        eventBus.unregister(this);
    }

    @Override
    public void subscribe() {
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();
    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoMapEvent event) {
        if(view != null){
            if(event.getError() != null){
                    view.onPhotosError(event.getError());
            }else {
                if (event.getType() == PhotoMapEvent.READ_EVENT) {
                    view.addPhoto(event.getPhoto());
                } else if (event.getType() == PhotoMapEvent.DELETE_EVENT) {
                    view.removePhoto(event.getPhoto());
                }
            }
        }
    }
}
