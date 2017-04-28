package io.github.viv2d2.findride;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Ride object.
 */

public class Ride implements Serializable {
    private String id;
    private String from;
    private String to;
    private String date;
    private String time;
    private ArrayList<Rider> riders;
    private int numRiders;
    private String category;

    /** Ride constructor (empty). */
    public Ride() {
        // empty constructor
    }


    public Ride(String f, String t, String d, String ti, Rider r) {
        this.id = "";
        this.from = f;
        this.to = t;
        this.date = d;
        this.time = ti;
        this.riders = new ArrayList<Rider>();
        this.riders.add(r);
        this.numRiders = r.getNumTotal();

        // set category
        if (this.from.equals("BWI") || this.to.equals("BWI")) {
            this.category = "travel";
        } else if (this.from.equals("Hampdenfest") || this.to.equals("Hampdenfest")) {
            this.category = "special";
        } else {
            this.category = "groceries";
        }
    }


    public void setID(String id) {
        this.id = id;
    }

    /** Getters. */
    public String getID() { return this.id; }
    public String getFrom() { return this.from; }
    public String getTo() { return this.to; }
    public String getDate() { return this.date; }
    public String getTime() { return this.time; }
    public int getNumRiders() { return this.numRiders; }
    public ArrayList<Rider> getRiders() { return this.riders; }
    public String getCategory() { return this.category; }

    /** Returns note corresponding to given rider r. */
    public String getNoteFromRider(String r) {
        for (int i = 0; i < this.riders.size(); ++i) {
            if (this.riders.get(i).getJHED().equals(r)) {
                return this.riders.get(i).getNotes();
            }
        }
        return "";
    }

    /** Sets note n for a specific rider r. */
    public boolean setNoteToRider(String r, String n) {
        for (int i = 0; i < this.riders.size(); ++i) {
            if (this.riders.get(i).getJHED().equals(r)) {
                this.riders.get(i).setNote(n);
                return true;
            }
        }
        return false;
    }

    /** Adds note n to the notes of rider r. */
    public boolean addNoteToRider(String r, String n) {
        for (int i = 0; i < this.riders.size(); ++i) {
            if (this.riders.get(i).getJHED().equals(r)) {
                this.riders.get(i).addNote(n);
                return true;
            }
        }
        return false;
    }

    /** Adds rider to rider array, notes array. */
    public void addRider(Rider r) {
        this.riders.add(r);
        this.numRiders += r.getNumTotal();
    }

    /** Deletes rider from rider array, notes array. */
    public boolean deleteRider(String r) {
        for (int i = 0; i < this.riders.size(); ++i) {
            if (this.riders.get(i).getJHED().equals(r)) {
                this.numRiders -= this.riders.get(i).getNumTotal();
                this.riders.remove(i);
                return true;
            }
        }
        return false;
    }

    /** Whether or not rider is in car. */
    public boolean inCar(String r) {
        for (int i = 0; i < this.riders.size(); ++i) {
            if (this.riders.get(i).getJHED().equals(r)) {
                return true;
            }
        }
        return false;
    }
}
