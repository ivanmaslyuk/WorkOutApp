package com.example.admin.workoutapp.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.workoutapp.R;
import com.example.admin.workoutapp.model.Exercise;
import com.example.admin.workoutapp.model.ExerciseRepository;
import com.example.admin.workoutapp.view.ExerciseActivity;

import java.util.List;

public class ExerciseAdapter extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    List<Exercise> objects;
    Application application;

    public ExerciseAdapter(Context context, List<Exercise> objects, Application application) {
        this.context = context;
        this.objects = objects;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.application = application;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // используем созданные, но не используемые View
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.exercise_list_item, parent, false);
        }
        view.setTag(position);

        Exercise x = (Exercise) getItem(position);

        // заполняем View
        ((TextView) view.findViewById(R.id.list_item_name)).setText(x.getName());

        TextView status = view.findViewById(R.id.list_item_status);

        List<Integer> records = x.getRecords();
        if (records.size() == 0) {
            status.setText(R.string.no_records);
        }
        else if (records.size() == 1) {
            int currentStatus = records.get(0);
            status.setTextColor(view.getResources().getColor(R.color.colorNeutral));
            status.setText(String.format("%d", currentStatus));
        }
        else {
            int currentStatus = records.get(records.size() - 1);
            int previousStatus = records.get(records.size() - 2);
            if (currentStatus > previousStatus)
                status.setTextColor(view.getResources().getColor(R.color.colorPositive));
            else if (currentStatus < previousStatus)
                status.setTextColor(view.getResources().getColor(R.color.colorNegative));
            else
                status.setTextColor(view.getResources().getColor(R.color.colorNeutral));
            status.setText(String.format("%d", currentStatus));
        }

        view.setOnClickListener(v -> {
            Intent i = new Intent(context, ExerciseActivity.class);
            i.putExtra("exercise_key", x.getName());
            context.startActivity(i);
        });

        // Регистрируем обработчик нажатия на кнопку "добавить результат".
        Button addRecordButton = view.findViewById(R.id.list_item_add_record_button);
        addRecordButton.setOnClickListener(addButton -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(addButton.getContext());

            // инициализируем диалог
            builder.setTitle(R.string.add_new_record_popup_title)
                    .setCancelable(true)
                    .setView(R.layout.add_record);
            AlertDialog dialog = builder.create();

            // показываем диалог
            dialog.show();

            // настраиваем view диалога
            Button saveButton = dialog.findViewById(R.id.add_record_save_button);
            EditText recordInput = dialog.findViewById(R.id.add_record_input);
            // настраиваем сохранение
            saveButton.setOnClickListener(saveBtn -> {
                ExerciseRepository repository = new ExerciseRepository(application);
                Exercise ex = (Exercise) getItem(position);
                String input = recordInput.getText().toString();
                if (!input.isEmpty()) {
                    ex.getRecords().add(Integer.parseInt(input));
                }
                else{
                    Toast.makeText(application, R.string.required_fields_error, Toast.LENGTH_SHORT).show();
                    return;
                }
                repository.updateExercise(ex);
                dialog.cancel();
            });
            // показываем клавиатуру
//            InputMethodManager imm = (InputMethodManager) application.getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.showSoftInput(recordInput, InputMethodManager.SHOW_IMPLICIT);
            recordInput.requestFocus();
            dialog.getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        });

        return view;
    }


}
