package app.graph;

import java.util.ArrayList;

public class Source implements Comparable<Source> {
    private Place start;
    private Place end;
    private long time;
    private int distance;
    private ArrayList<String> landMarks = new ArrayList<>();

    public Source(Place start, Place end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = -1;
    }

    public Source(Place start, Place end, int distance, long time) {
        this.start = start;
        this.end = end;
        this.distance = distance;
        this.time = time;
    }

    public Place getend() {
        return end;
    }

    public ArrayList<String> getLandMarksPlace() {
        return landMarks;
    }

    public Place getstart() {
        return start;
    }

    public int getDistance() {
        return distance;
    }

    public long getTime() {
        return time;
    }

    @Override
    public String toString() {
        return start.getName() + " --> " + end.getName() + " " + getDistance();
    }

    @Override
    public int compareTo(Source other) {
        if (getDistance() > other.getDistance())
            return 1;
        else if (getDistance() < other.getDistance())
            return -1;
        return 0;
    }

}
