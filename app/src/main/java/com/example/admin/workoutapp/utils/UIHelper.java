package com.example.admin.workoutapp.utils;

import android.widget.EditText;

/**
 * Содержит вспомогательные методы для упрощения работы с пользовательским интегрфейсом.
 */
public class UIHelper {

    /**
     * Считывает числовое значние из текстового поля для ввода.
     * @param input Текстоовое поле ввода, из которго нужно получить числовое значение.
     * @return Числовое значение, полученное из поля.
     */
    public static int getIntFromInput(EditText input){
        return input.getText().toString().isEmpty() ? 0 : Integer.parseInt(input.getText().toString());
    }

}
