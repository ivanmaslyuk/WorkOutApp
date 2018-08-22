package com.example.admin.workoutapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

public class ExerciseViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private Application app;
    private String param;

    public ExerciseViewModelFactory(Application app, String param) {
        this.app = app;
        this.param = param;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ExerciseViewModel(app, param);
    }
}
