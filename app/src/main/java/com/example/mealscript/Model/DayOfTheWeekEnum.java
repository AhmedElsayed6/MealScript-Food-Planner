package com.example.mealscript.Model;
public enum DayOfTheWeekEnum {

    SATURDAY(1),
    SUNDAY(2),
    MONDAY(3),
    TUESDAY(4),
    WEDNESDAY(5),
    THURSDAY(6),
    FRIDAY(7);
    private final int dayNumber;

    DayOfTheWeekEnum(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public static int getDayNumberByName(String dayName) {
        try {
            return DayOfTheWeekEnum.valueOf(dayName.toUpperCase()).getDayNumber();
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }
}