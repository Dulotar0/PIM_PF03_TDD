package com.pim.jrgs2526;

import java.time.Year;
import java.time.YearMonth;

public class MyDate {

    public static final String ERR_INVALID_YEAR = "Year value not valid";
    public static final String ERR_INVALID_MONTH = "Month value not valid";
    public static final String ERR_INVALID_DAY = "Day value not valid";
    public static final String ERR_INVALID_DATE = "Invalid date";

    public enum Months {
        JANUARY(1),
        FEBRUARY(2),
        MARCH(3),
        APRIL(4),
        MAY(5),
        JUNE(6),
        JULY(7),
        AUGUST(8),
        SEPTEMBER(9),
        OCTOBER(10),
        NOVEMBER(11),
        DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    private int day;
    private Months month;
    private int year;

    public MyDate(){

    }
    public MyDate(int day, Months month, int year) {
        int mes = month.monthNumber;
        int diasMes = YearMonth.of(this.year,mes).lengthOfMonth();

        boolean esBisiesto = (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
        if (month == Months.FEBRUARY && !esBisiesto && day > 28) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }

        if (day < 1 || day > diasMes) {
            throw new IllegalArgumentException(ERR_INVALID_DATE);
        }

        this.day = day;
        this.month = month;
        this.year = year;
    }
    public void setDay(int day){
        int mes = month.monthNumber;
        int diasMes = YearMonth.of(this.year,mes).lengthOfMonth();
        if(day > diasMes || day <= 0){
            throw new IllegalArgumentException(ERR_INVALID_DAY);
        }
        this.day = day;
    }

    public void setMonth(Months month){
        int mes = month.monthNumber;
        int diasMes = YearMonth.of(this.year,mes).lengthOfMonth();
        if(this.day > diasMes){
            throw new IllegalArgumentException(ERR_INVALID_MONTH);
        }
        this.month = month;
    }

    public void setYear(int year){
        int mes = month.monthNumber;
        int diasMes = YearMonth.of(year,mes).lengthOfMonth();

        if (day < 1 || day > diasMes) {
            throw new IllegalArgumentException(ERR_INVALID_YEAR);
        }
        this.year = year;
    }
}
