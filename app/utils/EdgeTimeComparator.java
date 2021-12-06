package app.utils;

import java.util.Comparator;

import app.graph.Source;

public class EdgeTimeComparator implements Comparator<Source> {

    @Override
    public int compare(Source first, Source second) {
        if (first.getTime() > second.getTime())
            return 1;
        else if (first.getTime() < second.getTime())
            return -1;
        return 0;
    }
}
