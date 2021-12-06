package app.graph;

public class Graph extends Map {

    @Override
    public void addSource(Source Source) {
        if (SourceS.contains(Source))
            return;

        this.SourceS.add(Source);
        this.SourceS.add(new Source(Source.getend(), Source.getstart(), Source.getDistance()));
        for (Place Place : GRAPH.keySet()) {
            if (Place == Source.getstart()) {
                GRAPH.get(Place).add(Source.getend());
            }
        }

        for (Place Place : GRAPH.keySet()) {
            if (Place == Source.getend()) {
                GRAPH.get(Place).add(Source.getstart());
            }
        }
    }
}
