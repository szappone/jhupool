package io.github.viv2d2.findride;

import java.util.ArrayList;

/**
 * Ride object.
 */

public class Ride {
    private String from;
    private String to;
    private String date;
    private String time;
    private int numRiders;
    private ArrayList<String> riders;
    private ArrayList<String> notes;

    public Ride() {
        // empty constructor
    }

    public Ride(String f, String t, String d, String ti, int numR, String r, String n) {
        this.from = f;
        this.to = t;
        this.date = d;
        this.time = ti;
        this.numRiders = numR;
        this.riders = new ArrayList<String>();
        this.riders.add(r);
        this.notes = new ArrayList<String>();
        this.notes.add(n);
    }

    public String getFrom() { return this.from; }
    public String getTo() { return this.to; }
    public String getDate() { return this.date; }
    public String getTime() { return this.time; }
    public int getNumRiders() { return this.numRiders; }

}
