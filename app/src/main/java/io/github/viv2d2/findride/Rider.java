package io.github.viv2d2.findride;

import java.util.ArrayList;

/**
 * Ride object.
 */

public class Rider {
    private String name;
    private int numGuests;
    private String notes;

    /** Ride constructor (empty). */
    public Rider() {
        // empty constructor
    }

    /** Ride constructor. */
    public Rider(String n, int g, String no) {
        this.name = n;
        this.numGuests = g;
        this.notes = no;
    }

    /** Ride constructor (no notes). */
    public Rider(String n, int g) {
        this.name = n;
        this.numGuests = g;
        this.notes = "";
    }

    /** Getters. */
    public String getName() { return this.name; }
    public int getNumGuests() { return this.numGuests; }
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
        this.numGuests++;
    }

    /** Deletes guest. */
    public void deleteGuest() {
        this.numGuests--;
    }
}
