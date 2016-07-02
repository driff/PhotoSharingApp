package com.driff.android.photofeed.lib.base;

/**
 * Created by johnj on 17/6/2016.
 */
public interface EventBus{

    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);

}
