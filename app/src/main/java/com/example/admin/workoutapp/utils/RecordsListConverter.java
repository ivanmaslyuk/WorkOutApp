package com.example.admin.workoutapp.utils;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class RecordsListConverter {

    @TypeConverter
    public static String fromRecords(List<Integer> records) {
        return TextUtils.join(";", records);
    }

    @TypeConverter
    public static List<Integer> toRecords(String raw) {
        if (raw.isEmpty()) {
            return new ArrayList<>();
        }
        List<Integer> values = new ArrayList<>();
        for (String val: raw.split(";")) {
            values.add(Integer.parseInt(val));
        }
        return values;
    }
}
