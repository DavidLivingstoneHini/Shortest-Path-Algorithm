package app.graph;

public class Place {
    private String name;

    public Place(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Place) {
            Place other = (Place) obj;
            return other.getName().equals(getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
