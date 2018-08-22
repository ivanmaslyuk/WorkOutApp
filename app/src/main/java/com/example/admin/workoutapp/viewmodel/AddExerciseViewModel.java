package com.example.admin.workoutapp.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.model.ExerciseRepository;
import com.example.admin.workoutapp.utils.ActionLiveData;

/**
 *
 */
public class AddExerciseViewModel extends AndroidViewModel {

    private ExerciseRepository repository;
    public enum InputStatus { NO_ERROR, MISSING_REQUIRED_FIELDS, ALREADY_EXISTS, SAVED_SUCCESSFULLY }
    private ActionLiveData<InputStatus> saveAction = new ActionLiveData<>();

    public AddExerciseViewModel(Application app) {
        super(app);
        repository = new ExerciseRepository(app);
    }

    public void saveExercise(String name) {
        if (name.isEmpty()) {
            saveAction.setValue(InputStatus.MISSING_REQUIRED_FIELDS);
        }
        else {
            Exercise exercise = new Exercise(name);
            if (repository.addExercise(exercise) == -1) {
                saveAction.setValue(InputStatus.ALREADY_EXISTS);
            } else {
                saveAction.setValue(InputStatus.SAVED_SUCCESSFULLY);
            }
        }
    }

    public ActionLiveData<InputStatus> getSaveAction() {
        return saveAction;
    }

}
