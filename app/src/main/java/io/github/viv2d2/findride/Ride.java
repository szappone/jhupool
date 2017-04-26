package io.github.viv2d2.findride;

import java.util.ArrayList;

/**
 * Ride object.
 */

public class Ride {
    private String id;
    private String from;
    private String to;
    private String date;
    private String time;
    private ArrayList<String> riders;
    private ArrayList<ArrayList<String>> notes;
    private String category;

    /** Ride constructor (empty). */
    public Ride() {
        // empty constructor
    }

    /** Ride constructor. */
    public Ride(String f, String t, String d, String ti, String r, String n) {
        this.id = "";
        this.from = f;
        this.to = t;
        this.date = d;
        this.time = ti;
        this.riders = new ArrayList<String>();
        this.riders.add(r);
        this.notes = new ArrayList<ArrayList<String>>();
        ArrayList<String> s = new ArrayList<String>();
        s.add(r);
        s.add(n);
        this.notes.add(s);

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
    public int getNumRiders() { return this.riders.size(); }
    public ArrayList<String> getRiders() { return this.riders; }
    public ArrayList<ArrayList<String>> getNotes() { return this.notes; }
    public String getCategory() { return this.category; }

    /** Returns note corresponding to given rider r. */
    public String getNoteFromRider(String r) {
        for (int i = 0; i < this.notes.size(); ++i) {
            if (this.notes.get(i).get(0).equals(r)) {
                return this.notes.get(i).get(1);
            }
        }
        return "";
    }

    /** Sets note n for a specific rider r. */
    public boolean setNoteToRider(String r, String n) {
        for (int i = 0; i < this.notes.size(); ++i) {
            if (this.notes.get(i).get(0).equals(r)) {
                this.notes.get(i).set(1, n);
                return true;
            }
        }
        return false;
    }

    /** Adds note n to the notes of rider r. */
    public boolean addNoteToRider(String r, String n) {
        for (int i = 0; i < this.notes.size(); ++i) {
            if (this.notes.get(i).get(0).equals(r)) {
                this.notes.get(i).set(1, this.notes.get(i).get(1) + "\n" + n);
                return true;
            }
        }
        return false;
    }

    /** Adds rider to rider array, notes array. */
    public void addRider(String r, String n) {
        this.riders.add(r);
        ArrayList<String> s = new ArrayList<String>();
        s.add(r);
        s.add(n);
        this.notes.add(s);
    }

    /** Deletes rider from rider array, notes array. */
    public boolean deleteRider(String r) {
        for (int i = 0; i < this.riders.size(); ++i) {
            if (this.riders.get(i).equals(r)) {
                this.riders.remove(i);
                this.notes.remove(i);
                return true;
            }
        }
        return false;
    }
}
