package testgroup.service.parseReplacement;

public class Day {
    private int day;
    private int month;
    private int year;

    public Day(int day, int monthInt, int year) {
        this.day = day;
        this.month = monthInt;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
