package app;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.algorithms.Algo2;
import app.algorithms.CPM_519;
import app.algorithms.Vogels;
import app.graph.Source;
import app.graph.Graph;
import app.graph.Place;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();
        Place A = new Place("Great Hall");
        Place B = new Place("Commonwealth Hall");
        Place C = new Place("Balme Library");
        Place D = new Place("JQB");
        Place E = new Place("Main Gate");
        Place F = new Place("Banking Square");
        Place G = new Place("CC");
        Place H = new Place("Sarbah Field");
        Place I = new Place("Night Market");
        Place J = new Place("Diaspora");

        // Places added to the map
        graph.addPlace(A);
        graph.addPlace(B);
        graph.addPlace(C);
        graph.addPlace(D);
        graph.addPlace(E);
        graph.addPlace(F);
        graph.addPlace(G);
        graph.addPlace(H);
        graph.addPlace(I);
        graph.addPlace(J);

        graph.addSource(new Source(A, B, 190));
        graph.addSource(new Source(B, C, 750));
        graph.addSource(new Source(C, D, 900));

        graph.addSource(new Source(E, D, 600));
        graph.addSource(new Source(D, E, 600));
        graph.addSource(new Source(E, F, 1400));
        graph.addSource(new Source(F, E, 1400));

        graph.addSource(new Source(B, H, 1100));
        graph.addSource(new Source(H, B, 1100));
        graph.addSource(new Source(B, G, 700));
        graph.addSource(new Source(G, B, 700));
        graph.addSource(new Source(C, G, 800));
        graph.addSource(new Source(G, C, 800));

        graph.addSource(new Source(G, F, 1000));
        graph.addSource(new Source(F, G, 1000));
        graph.addSource(new Source(G, H, 600));
        graph.addSource(new Source(H, G, 600));

        graph.addSource(new Source(H, I, 1100));
        graph.addSource(new Source(I, H, 1100));
        graph.addSource(new Source(F, I, 1900));
        graph.addSource(new Source(I, F, 190));

        graph.addSource(new Source(I, J, 800));
        graph.addSource(new Source(J, I, 800));

        // asking the user for current location
        System.out.println("Choose Starting Point:");
        graph.listPlaces(null);

        // The User interface
        String places[] = { "Great Hall", "Commonwealth Hall", "Balme Library", "JQB", "Main Gate", "Banking Square",
                "CC", "Sarbah Field", "Night Market", "Diaspora" };

        JFrame frame = new JFrame();// creating instance of JFrame

        JComboBox startCombo = new JComboBox(places);
        startCombo.setBounds(500, 50, 200, 20);
        frame.add(startCombo);// adding button in JFrame

        JComboBox endCombo = new JComboBox(places);
        endCombo.setBounds(500, 100, 200, 20);
        frame.add(endCombo);// adding button in JFrame

        JLabel info = new JLabel();
        info.setText("Best Route: ");
        info.setBounds(500, 200, 150, 40);
        frame.add(info);

        frame.getContentPane().setBackground(Color.pink);

        JLabel shortestPathDisplay = new JLabel();
        shortestPathDisplay.setBounds(500, 220, 400, 40);
        frame.add(shortestPathDisplay);

        JLabel distanceDisplay = new JLabel();
        distanceDisplay.setBounds(500, 240, 400, 40);
        frame.add(distanceDisplay);

        JLabel timeDisplay = new JLabel();
        timeDisplay.setBounds(500, 260, 400, 40);
        frame.add(timeDisplay);

        JButton button = new JButton("Get Direction");
        button.setBounds(500, 150, 150, 40);
        frame.add(button);
        button.setBackground(Color.black);
        button.setForeground(Color.white);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String theOrigin = startCombo.getSelectedItem().toString();
                String theEnd = endCombo.getSelectedItem().toString();

                Place start = graph.getPlaceByName(theOrigin);
                Place end = graph.getPlaceByName(theEnd);

                Algo2.findShortestPath(graph, start, end);
                String path = Algo2.getShortestPath(start, end);
                shortestPathDisplay.setText(path);

                distanceDisplay.setText("Estimated Distance of Travel: " + Algo2.getTotalDistance(end));
                String name = "D";
                timeDisplay.setText("Estimated Travel Time: " + Algo2.getTime(end));
            }
        });

        // Display
        frame.setSize(1200, 800);
        frame.setLayout(null);
        frame.setVisible(true);

    }
}