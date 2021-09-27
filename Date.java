package songlibrary;

import java.util.Calendar;

/**
 * This class defines the Date abstract data type with year, month, and day.
 * @author Aatif Sayed, Pranav Tailor
 */
public class Date implements Comparable<Date> {
    
    private int year;
    private int month;
    private int day;
    
    /**
     * This is a parameterized constructor that takes a String in the form of "mm/dd/yyyy" and creates an instance of Date.
     * @param date a date in a form of "mm/dd/yyyy".
     */
    public Date(String date) {
        String[] dateTokens = date.split("/");
        month = Integer.parseInt(dateTokens[0]);
        day = Integer.parseInt(dateTokens[1]);
        year = Integer.parseInt(dateTokens[2]);
    }
    
    /**
     * This constructor returns today's date.
     */
    public Date() {
        Calendar calendar = Calendar.getInstance();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DATE);
        year = calendar.get(Calendar.YEAR);
    } 
    
    /**
     * This method checks whether an instance of Date is a valid date or not.
     * A Date is considered valid if it corresponds to a valid calendar date, is not before 1980, and is not a future date.
     * @return true if Date is a valid date, false otherwise.
     */
    public boolean isValid() {
        Calendar calendar = Calendar.getInstance();
        if (month < Constants.JANUARY || month > Constants.DECEMBER || year < Constants.THE_EIGHTIES || 
                year > calendar.get(Calendar.YEAR) || day < Constants.MINIMUM_DAY_NUMBER || day > Constants.DAYS_IN_MONTH_2)
            return false;
        if (month == Constants.APRIL || month == Constants.JUNE || month == Constants.SEPTEMBER || month == Constants.NOVEMBER) {
            if (day > Constants.DAYS_IN_MONTH_1)
                return false;
        }
        if (!(isValidFebruary()))
            return false;
        if (isFutureDate())
            return false;
        return true;
    }
    
    /**
     * This method overrides the compareTo() method and compares 2 dates.
     * @return integer representing the difference in the number of days between 2 dates.
     */
    @Override
    public int compareTo(Date date) {
        int yearDifference = 0, monthDifference = 0, dayDifference = 0;
        yearDifference = Constants.DAYS_IN_NON_LEAP_YEAR * (year - date.year);
        monthDifference = Constants.DAYS_IN_MONTH_1 * (month - date.month);
        dayDifference = day - date.day;
        return yearDifference + monthDifference + dayDifference;
    }
    
    /**
     * This method checks whether a given year is a leap year or not.
     * @param year the year that is going to be checked.
     * @return true if specified year is a leap year, false otherwise.
     */
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
    
    /**
     * This is a private helper method that checks if a Date instance is past today's date or not.
     * @return true if Date is in the future, false otherwise.
     */
    private boolean isFutureDate() {
        Calendar calendar = Calendar.getInstance();
        if (year == calendar.get(Calendar.YEAR)) {
            if (month > calendar.get(Calendar.MONTH))
                return true;
            else if (month == calendar.get(Calendar.MONTH)) {
                if (day > calendar.get(Calendar.DATE))
                    return true;
            }
        }
        return false;
    }
    
    /**
     * This is a private helper method that checks if a Date instance with the month as February is valid or not.
     * @return true if Date is a valid February date, false otherwise.
     */
    private boolean isValidFebruary() {
        if (month == Constants.FEBRUARY) {
            if (isLeapYear(year)) {
                if (day < Constants.MINIMUM_DAY_NUMBER || day > Constants.LEAP_YEAR_FEBRUARY_DAYS)
                    return false;
            }
            else {
                if (day < Constants.MINIMUM_DAY_NUMBER || day > Constants.NON_LEAP_YEAR_FEBRUARY_DAYS)
                    return false;
            }
        }
        return true;
    }
    
    /**
     * Returns a textual representation of the Date object in "mm/dd/yyyy" format.
     */
    @Override
    public String toString() {
        String dateAsString = Integer.toString(month) + "/" + Integer.toString(day) + "/" + Integer.toString(year);
        return dateAsString;
    }
    
    /**
     * Test-bed main for the Date class.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        // Test case #1: A date with a valid month and day that has a year prior to 1980 should be invalid
        Date date = new Date("1/1/1979");
        boolean expectedResult = false;
        boolean result = date.isValid();
        System.out.print("Test case #1: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #2: A date with a valid day and year, but a month above 12
        date = new Date("13/15/2000");
        result = date.isValid();
        System.out.print("Test case #2: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #3: A date with a valid day and year, but a month below 1
        date = new Date("0/15/1999");
        result = date.isValid();
        System.out.print("Test case #3: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #4: A date with a 31-day month and valid year, but invalid day
        date = new Date("12/32/1998");
        result = date.isValid();
        System.out.print("Test case #4: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #5: A date with a 30-day month and valid year, but invalid day
        date = new Date("6/31/1996");
        result = date.isValid();
        System.out.print("Test case #5: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #6: A date with a valid month and year, but a day below 1
        date = new Date("6/-1/1997");
        result = date.isValid();
        System.out.print("Test case #6: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #7: A date with month as 2 (i.e. February) and valid non-leap year, but day as 29
        date = new Date("2/29/2017");
        result = date.isValid();
        System.out.print("Test case #7: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #8: A date with month as 2 (i.e. February) and valid leap-year, but day as 30
        date = new Date("2/30/2020");
        result = date.isValid();
        System.out.print("Test case #8: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
        
        // Test case #9: A date with a valid month and day, but a year in the future
        date = new Date("1/1/2100");
        result = date.isValid();
        System.out.print("Test case #9: ");
        if (result == expectedResult)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
    }
}