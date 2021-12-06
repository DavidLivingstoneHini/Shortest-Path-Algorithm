package app.algorithms;

import java.util.ArrayList;
import java.util.HashMap;

import app.graph.Map;
import static app.utils.Functions.*;
import app.graph.Source;
import app.graph.Place;

public class Algo2 {
    // Track all unvisited Places in the graph.
    private static ArrayList<Place> UNVISITED = new ArrayList<>();

    // A map of each Place and the min cost/distance to each.
    private static HashMap<Place, Integer> DISTANCE_MAP = new HashMap<>();

    // A map of a Place and the previous Place to reach it. Used later to.
    private static HashMap<Place, Place> PREVIOUS_Place = new HashMap<>();

    public static void findShortestPath(Map graph, Place start, Place end) {

        if (start == end) {
            print(start.getName());
            return;
        }

        // Set the cost to reach each Place to infinity.
        for (Place Place : graph.getPlaces()) {
            DISTANCE_MAP.put(Place, Integer.MAX_VALUE);
            PREVIOUS_Place.put(Place, null);
            UNVISITED.add(Place);
        }

        // Set the cost to reach the start Place to zero.
        DISTANCE_MAP.put(start, 0);

        // Find the Place with least distance to reach.
        Place minPlace = findVertextWithMinDist();
        while (UNVISITED.size() > 0 && minPlace != null) {
            // Find the Place with least distance to reach.
            minPlace = findVertextWithMinDist();

            // Mark this Place as visited.
            UNVISITED.remove(minPlace);

            // Explore all the neighbours of this Place.
            ArrayList<Source> Sources = graph.getendSources(minPlace);
            for (Source Source : Sources) {
                // Checking for cyles: i.e., if we've not already visited this Place.
                if (UNVISITED.contains(Source.getend())) {

                    // Calculate alternative cost
                    int alt = DISTANCE_MAP.get(minPlace) + Source.getDistance();

                    if (alt < DISTANCE_MAP.get(Source.getend())) { // If the alternative cost is smaller than
                                                                   // the
                                                                   // current cost.
                        // Update the min cost to reach this Place.
                        DISTANCE_MAP.put(Source.getend(), alt);

                        // Udate the previous Place to reach this current Place.
                        PREVIOUS_Place.put(Source.getend(), minPlace);
                    }
                }
            }
        }
        printShortestPath(start, end);
        printDistances(end);
    }

    private static void printDistances(Place end) {
        println("Total Distance: " + String.format("%.3f", DISTANCE_MAP.get(end) / 6F) + "km \n");
    }

    public static String getTotalDistance(Place end) {
        return String.format("%.3f", DISTANCE_MAP.get(end) / 1000f) + "km";
    }

    public static String getTime(Place end) {
        return String.format("%.3f", DISTANCE_MAP.get(end) / 80f) + "min.";
    }

    public static String getShortestPath(Place start, Place end) {
        ArrayList<Place> path = new ArrayList<>();

        while (PREVIOUS_Place.get(end) != null) {
            path.add(end);
            end = PREVIOUS_Place.get(end);
        }
        path.add(start);

        StringBuilder builder = new StringBuilder();
        for (int i = path.size() - 1; i >= 0; i--) {
            Place Place = path.get(i);
            builder.append(Place.getName());
            builder.append(" --> ");
        }
        return builder.toString();
    }

    private static void printShortestPath(Place start, Place end) {
        // Reconstruct the path to the end using the the previous Places.
        println("\nThe Shortest path from '" + start.getName() + "' to '" + end.getName());
        ArrayList<Place> path = new ArrayList<>();
        print(start.getName());
        while (PREVIOUS_Place.get(end) != null) {
            path.add(end);
            end = PREVIOUS_Place.get(end);
        }

        for (int i = path.size() - 1; i >= 0; i--) {
            Place Place = path.get(i);
            print(" --> " + Place.getName());
        }
        println("");
    }

    private static Place findVertextWithMinDist() {
        Place minPlace = null;
        long minDistance = Long.MAX_VALUE;
        for (HashMap.Entry<Place, Integer> entry : DISTANCE_MAP.entrySet()) {
            Place Place = entry.getKey();
            int distance = entry.getValue();
            if (UNVISITED.contains(Place) && distance < minDistance) {
                minDistance = distance;
                minPlace = Place;
            }
        }
        return minPlace;
    }
}
