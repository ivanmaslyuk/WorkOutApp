package com.example.admin.workoutapp.utils;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

// First extend the MutableLiveData class
public class ActionLiveData<T> extends MutableLiveData<T> {

    private static final String TAG = "SingleLiveEvent";

    private AtomicBoolean mPending = new AtomicBoolean(false);

    @MainThread
    @Override
    public void observe(LifecycleOwner owner, Observer<T> observer) {

        // Being strict about the observer numbers is up to you
        // I thought it made sense to only allow one to handle the event
        if (hasObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.");
        }

        super.observe(owner, new Observer<T>() {
            @Override
            public void onChanged(@Nullable T data) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(data);
                }
            }
        });
    }

    @MainThread
    public void setValue(@Nullable T data) {
        mPending.set(true);
        super.setValue(data);
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    public void call() { setValue(null); }
}