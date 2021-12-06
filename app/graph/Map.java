package app.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import static app.utils.Functions.*;

public class Map {
    protected final HashMap<Place, ArrayList<Place>> GRAPH = new HashMap<>();
    protected final ArrayList<Source> SourceS = new ArrayList<>();
    private int PlaceSize = 0;

    public void addPlace(Place Place) {
        if (!GRAPH.containsKey(Place)) {
            GRAPH.put(Place, new ArrayList<>());
            PlaceSize++;
        }
    }

    public void addSource(Source Source) {
        if (SourceS.contains(Source))
            return;

        this.SourceS.add(Source);
        for (Place start : GRAPH.keySet()) {
            if (start == Source.getstart()) {
                GRAPH.get(start).add(Source.getend());
            }
        }
    }

    public ArrayList<Source> getendSources(Place start) {
        ArrayList<Source> ends = new ArrayList<>();
        for (Source Source : this.SourceS) {
            if (Source.getstart() == start) {
                ends.add(Source);
            }
        }
        return ends;
    }

    public Source getSource(Place start, Place end) {
        for (Source Source : this.SourceS) {
            if (Source.getstart() == start && Source.getend() == end) {
                return Source;
            }
        }
        return null;
    }

    public Place getPlaceByName(String name) {
        for (Place Place : GRAPH.keySet()) {
            if (Place.getName().toLowerCase().equals(name.toLowerCase())) {
                return Place;
            }
        }
        return null;
    }

    public Set<Place> getPlaces() {
        return this.GRAPH.keySet();
    }

    public int getPlaceSize() {
        return PlaceSize;
    }

    public void printGraph() {
        println("\n          GRAPH: ADJACENCY LIST                ");
        println("              PLACES ON CAMPUS                 \n");
        for (HashMap.Entry<Place, ArrayList<Place>> entry : GRAPH.entrySet()) {
            Place Place = entry.getKey();
            ArrayList<Place> ends = entry.getValue();
            StringBuilder builder = new StringBuilder();
            builder.append("[");
            boolean emptyList = true;
            for (Place destinatnion : ends) {
                if (emptyList)
                    builder.append(destinatnion.getName());
                else
                    builder.append(", " + destinatnion.getName());
                emptyList = false;
            }
            builder.append("]");
            println(Place.getName() + "-->" + builder.toString());
            println("");
        }
    }

    public void listPlaces(Place except) {
        int index = 1;
        for (Place Place : GRAPH.keySet()) {
            if (Place != except) {
                println(index + ". " + Place.getName());
            }
            index++;
        }
    }

}
