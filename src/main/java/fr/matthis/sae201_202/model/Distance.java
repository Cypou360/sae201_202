package fr.matthis.sae201_202.model;

public class Distance {
    private int distance;
    private Sector current;
    private Sector previous;

    public Distance(int distance, Sector current, Sector previous) {
        this.distance = distance;
        this.current = current;
        this.previous = previous;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Sector getCurrent() {
        return current;
    }

    public void setCurrent(Sector current) {
        this.current = current;
    }

    public Sector getPrevious() {
        return previous;
    }

    public void setPrevious(Sector previous) {
        this.previous = previous;
    }

    @Override
    public String toString() {
        return "Distance{" +
                "distance=" + distance +
                ", current=" + current +
                '}';
    }
}
