package com.example.mealscript.Local;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Utils {
    private List<String> alphabetList = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
            "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z"
    );
    private int YesterdayDate;
    private String YesterdayLetter;
    private String TodayLetter;
    private Calendar calendar;
    private Random random;
    private int TodayDate;
    private SharedPreferences sp;

    public Utils(Context context) {
        this.context = context;
        calendar = Calendar.getInstance();
        TodayDate = calendar.get(Calendar.DAY_OF_WEEK);
        random = new Random();
        sp = this.context.getSharedPreferences("DailyInspriations", Context.MODE_PRIVATE);
        YesterdayDate = sp.getInt("TodayDate", 3);
        YesterdayLetter = sp.getString("TodayLetter", "B");
    }

    private Context context;

    public String getTodayLetter() {
        if (TodayDate != YesterdayDate) {
            SharedPreferences.Editor ed = sp.edit();
            ed.putInt("TodayDate", TodayDate);
            TodayLetter = getRandomLetter();
            ed.putString("TodayLetter", TodayLetter);
            ed.commit();

            return TodayLetter;
        }
        return YesterdayLetter;
    }

    public String getRandomLetter() {
        int randomNumber = random.nextInt(24);
        return alphabetList.stream().filter(e ->
                e.compareToIgnoreCase(YesterdayLetter) != 0
        ).collect(Collectors.toList()).get(randomNumber);
    }

}
