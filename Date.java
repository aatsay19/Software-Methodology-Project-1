package songlibrary;

import java.util.Calendar;

public class Date implements Comparable<Date> {
    
    private int year;
    private int month;
    private int day;
    
    
    // Take "mm/dd/yyyy" and create a Date object
    public Date(String date) {
        String[] dateTokens = date.split("/");
        month = Integer.parseInt(dateTokens[0]);
        day = Integer.parseInt(dateTokens[1]);
        year = Integer.parseInt(dateTokens[2]);
    }
    
    // Create an object with today's date (see Calendar class)
    public Date() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
    }
    
    // ...
    
    public boolean isValid() {
        Calendar calendar = Calendar.getInstance();
        if (month < Constants.JANUARY || month > Constants.DECEMBER || year < Constants.THE_EIGHTIES || year > calendar.get(Calendar.YEAR) || day < Constants.MINIMUM_DAY_NUMBER || day > Constants.DAYS_IN_MONTH_2) {
            return false;
        }
        if (month == Constants.APRIL || month == Constants.JUNE || month == Constants.SEPTEMBER || month == Constants.NOVEMBER) {
            if (day > Constants.DAYS_IN_MONTH_1)
                return false;
        }
        if (month == Constants.FEBRUARY) {
            if (isLeapYear(year)) {
                if (day > Constants.LEAP_YEAR_FEBRUARY_DAYS)
                    return false;
            }
            else {
                if (day > Constants.NON_LEAP_YEAR_FEBRUARY_DAYS)
                    return false;
            }
        }
        if (year == calendar.get(Calendar.YEAR)) {
            if (month > calendar.get(Calendar.MONTH))
                return false;
            if (month == calendar.get(Calendar.MONTH)) {
                if (day > calendar.get(Calendar.DATE))
                    return false;
            }
        }
        return true;
    }
    
    @Override
    public int compareTo(Date date) {
        
    }
    
    private boolean isLeapYear(int year) {
        if (year % Constants.QUADRENNIAL == 0) {
            if (year % Constants.CENTENNIAL == 0) {
                if (year % Constants.QUARTERCENTENNIAL == 0) {
                    return true;
                }
            }
            return true;
        }
        return false;
    }
    
    public String toString() {
        String dateAsString = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        return dateAsString;
    }
    
}