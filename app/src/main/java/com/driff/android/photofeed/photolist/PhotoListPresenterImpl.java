package com.driff.android.photofeed.photolist;

import com.driff.android.photofeed.entities.Photo;
import com.driff.android.photofeed.lib.base.EventBus;
import com.driff.android.photofeed.photolist.events.PhotoListEvent;
import com.driff.android.photofeed.photolist.ui.PhotoListView;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by johnj on 1/7/2016.
 */
public class PhotoListPresenterImpl implements PhotoListPresenter{

    private static final String EMTY_LIST = "List is Empty";
    private EventBus eventBus;
    private PhotoListView view;
    private PhotoListInteractor interactor;

    public PhotoListPresenterImpl(EventBus eventBus, PhotoListView view, PhotoListInteractor interactor) {
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
        if(view != null){
            view.hideList();
            view.showProgress();
        }
        interactor.subscribe();
    }

    @Override
    public void unsubscribe() {
        interactor.unsubscribe();
    }

    @Override
    public void removePhoto(Photo photo) {
        interactor.removePhoto(photo);
    }

    @Override
    @Subscribe
    public void onEventMainThread(PhotoListEvent event) {
        if(view != null){
            view.hideProgress();
            view.showList();
        }
        String error = event.getError();
        if(error != null){
            if(error.isEmpty()){
                view.onPhotosError(EMTY_LIST);
            }else{
                view.onPhotosError(error);
            }
        }else{
            if(event.getType() == PhotoListEvent.READ_EVENT){
                view.addPhoto(event.getPhoto());
            } else if(event.getType() == PhotoListEvent.DELETE_EVENT){
                view.removePhoto(event.getPhoto());
            }
        }
    }
}
