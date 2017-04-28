package io.github.viv2d2.findride;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ride object.
 */

public class Rider implements Serializable {
    private String jhed;
    private String facebook;
    private int numGuests;
    private int numTotal;
    private String notes;

    /** Ride constructor (empty). */
    public Rider() {
        // empty constructor
    }

    /** Ride constructor. */
    public Rider(String j, String f, int t, String no) {
        this.jhed = j;
        this.facebook = f;
        this.numTotal = t;
        this.numGuests = t - 1;
        this.notes = no;
    }

    /** Ride constructor (no notes). */
    public Rider(String j, String f, int g) {
        this.jhed = j;
        this.facebook = f;
        this.numGuests = g;
        this.notes = "";
    }

    /** Getters. */
    public String getJHED() { return this.jhed; }
    public String getFacebook() { return this.facebook; }
    public int getNumGuests() { return this.numGuests; }
    public int getNumTotal() { return this.numTotal; }
    public String getNotes() { return this.notes; }

    /** Sets note n for a specific rider r. */
    public void setNote(String n) {
        this.notes = n;
    }

    /** Adds note n to the notes of rider r. */
    public void addNote(String n) {
        this.notes += n;
    }

    /** Adds guest. */
    public void addGuest() {
        this.numTotal++;
        this.numGuests++;
    }

    /** Deletes guest. */
    public void deleteGuest() {
        this.numTotal--;
        this.numGuests--;
    }
}
