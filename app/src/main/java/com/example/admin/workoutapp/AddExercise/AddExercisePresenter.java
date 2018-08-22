package com.example.admin.workoutapp.AddExercise;

import com.example.admin.workoutapp.R;
import com.example.admin.workoutapp.model.AppDatabase;
import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.view.AddExerciseActivity;

public class AddExercisePresenter {
/*
    private AddExerciseActivity view;
    private AppDatabase db;

    AddExercisePresenter(AddExerciseActivity view) {
        this.view = view;
        db = AppDatabase.getAppDatabase(view.getApplicationContext());
    }

    public void attemptSaving(String name, int sets, int reps, int rest, boolean isCardio) {
        if (name.isEmpty() || sets == 0 || reps == 0 || rest == 0) {
            view.showToast(R.string.required_fields_error);
        }
        else {
            Exercise exercise = new Exercise(name, sets, reps, rest, isCardio);
            if (db.exerciseDao().addExercise(exercise) == -1) {
                view.showToast(R.string.exercise_already_exists);
            } else {
                view.showToast(R.string.saving_success);
                view.finish();
            }
        }
    }*/
}
