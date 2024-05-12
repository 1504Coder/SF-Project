package project3;
/**
 * @author Elijah Philip
 * This class takes in a string format of the date of inspection or a numerical format of the date by the user and converts it to a form that can be used to compare dates and output a human-readable form.
 */

import java.util.stream.IntStream;

public class Date implements Comparable<Date> {

    private String date;
    private int month;
    private int day;
    private int year;
    private static final int[] days_31 = {1, 3, 5, 7, 8, 10, 12};
    private static final int[] days_30 = {4, 6, 9, 11};
    private static final int[] leapYears = {2000, 2004, 2008, 2012, 2016, 2020, 2024};


    /**
     * Date constructs a new Date object with the specified month, day, and year.
     *
     * @param date takes in a String in the format MM/DD/YY or MM/DD/YYYY.
     * @throws IllegalArgumentException if the String format isn't MM/DD/YY, MM/DD/YYYY, or if data=null.
     */
    Date(String date) throws IllegalArgumentException {
        if (date == null) {
            throw new IllegalArgumentException("A date is needed.");
        }

        if ((date.trim()).length() != 8 && (date.trim()).length() != 10) {
            throw new IllegalArgumentException("Incorrect format for the date. Should be written as MM/DD/YYYY, or MM/DD/YY");
        }

        if (date.charAt(2) != '/' || date.charAt(5) != '/') {
            throw new IllegalArgumentException("Incorrect format for the date. Should be written as MM/DD/YYYY, or MM/DD/YY");
        }
        this.date = date;
    }

    /**
     * @param month takes in an integer value between 1 and 12.
     * @param day   takes in an integer value between 1 and 31.
     * @param year  takes in an integer value between 200 and 2024.
     * @throws IllegalArgumentException if any of the params are out of bounds.
     */
    Date(int month, int day, int year) throws IllegalArgumentException {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("The month must be in the range of 1 and 12.");
        }
        if (year < 2000 || year > 2025) {
            throw new IllegalArgumentException("The year must be in the range of 2000 and 2025.");
        }
        if (day < 1) {
            throw new IllegalArgumentException("The day must be greater than 1.");
        }
        if (day > 31 && IntStream.of(days_31).anyMatch(x -> x == month)) {
            throw new IllegalArgumentException("This month must be in the range of 1 to 31 days.");
        }
        if (day > 30 && IntStream.of(days_30).anyMatch(x -> x == month)) {
            throw new IllegalArgumentException("This month must be in the range of 1 to 30 days.");
        }
        if (day > 29 && month == 2 && IntStream.of(leapYears).anyMatch(x -> x == year)) {
            throw new IllegalArgumentException("This month is on a leap year, and must be in the range of 1 to 29 days.");
        }
        if (day > 28 && month == 2 && IntStream.of(leapYears).noneMatch(x -> x == year)) {
            throw new IllegalArgumentException("This month isn't on a leap year, and must be in the range of 1 to 28 days.");
        }
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
/**
 * Checks in order if the year is greater. If not checks for if the month is greater or, not, and so on. If none is greater or less,they are the same date.
 * @return 0 if they are the same date, 1 if this data is greater than the given date, or -1 if this date is less than the given date.
 **/
    public int compareTo(Date d) {
        if (date != null && d.date != null) {
            return Integer.compare(date.compareTo(d.date), 0);
        } else if (year != d.year) {
            if (year > d.year) {
                return 1;
            } else {
                return -1;
            }
        } else if (month != d.month) {
            if (month > d.month) {
                return 1;
            } else {
                return -1;
            }
        } else if (day != d.day) {
            if (day > d.day) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }

    /**
     * @return the string format of the date.
     */

    public String toString() {
        if (date != null) {
            if (date.length() == 8) {
                return date.substring(0, 6) + "20" + date.substring(6, 8);
            } else {
                return date;
            }
        } else {
            String sMonth = Integer.toString(month);
            String sDay = Integer.toString(day);
            if (month < 10) {
                sMonth = "0" + sMonth;
            }
            if (day < 10) {
                sDay = "0" + sDay;
            }
            return sMonth + "/" + sDay + "/" + year;
        }

    }
}
