package isd.group_4;

import java.util.Calendar;

public class ConvertTimeForSQL {
    public ConvertTimeForSQL() {}

    // use this function when you want to update the database
    static public String convertCalendarToSQLDate(Calendar calendar) {
        //convert the date to a string format working with SQL ("YYYY-MM-DD")
        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    static public String convertCalendarToSQLDateTime(Calendar calendar) {
        return convertCalendarToSQLDate(calendar) + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND);
    }

    // use this function when getting orderDate from the database
    static public Calendar convertSQLDateToCalendar(String calendarString) {
        //convert the orderDate String to a Calendar object
        String[] dateValues = calendarString.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateValues[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateValues[1]));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateValues[2]));
        return calendar;
    }

    static public Calendar convertSQLDateTimeToCalendar(String calendarString) {
        //convert the orderDate String to a Calendar object
        String[] dateTimeValues = calendarString.split(" ");
        String[] dateValues = dateTimeValues[0].split("-");
        String[] timeValues = dateTimeValues[1].split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(dateValues[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(dateValues[1]));
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateValues[2]));
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeValues[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeValues[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(timeValues[2]));
        return calendar;
    }

}
