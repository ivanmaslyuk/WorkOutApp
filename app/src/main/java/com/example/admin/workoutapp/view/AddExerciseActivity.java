package com.example.admin.workoutapp.view;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.workoutapp.R;
import com.example.admin.workoutapp.utils.UIHelper;
import com.example.admin.workoutapp.viewmodel.AddExerciseViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddExerciseActivity extends AppCompatActivity {

    @BindView(R.id.saveButton)
    Button saveButton;
    @BindView(R.id.exerciseNameInput)
    EditText nameInput;

    AddExerciseViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        ButterKnife.bind(this);
        vm = ViewModelProviders.of(this).get(AddExerciseViewModel.class);

        vm.getSaveAction().observe(this, this::stateChanged);

        saveButton.setOnClickListener(v -> saveButtonClicked());

    }

    private void showToast(int message) {
        Toast.makeText(getApplicationContext(), getString(message), Toast.LENGTH_SHORT).show();
    }

    public void saveButtonClicked() {
        String name = nameInput.getText().toString().trim();

        vm.saveExercise(name);
    }

    private void stateChanged(AddExerciseViewModel.InputStatus status) {
        switch (status) {
            case SAVED_SUCCESSFULLY:
                showToast(R.string.saving_success);
                finish();
                break;
            case ALREADY_EXISTS:
                showToast(R.string.exercise_already_exists);
                break;
            case MISSING_REQUIRED_FIELDS:
                showToast(R.string.required_fields_error);
                break;
        }
    }
}
