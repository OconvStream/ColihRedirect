package app.notofficial.jw.colihredirect.firebase;

import java.io.Serializable;

public class Schedule implements Serializable {

    private int id, date;
    private String area, number, time;

    public Schedule() {
    }

    public Schedule(int id, int date, String area, String number, String time) {
        this.id = id;
        this.date = date;
        this.area = area;
        this.number = number;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getDate() {
        return date;
    }

    public String getArea() {
        return area;
    }

    public String getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }
}
