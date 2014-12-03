package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Jonas on 03-12-2014.
 */
public class GUI {
    private JFrame frame;
    private JPanel centerGrid = new JPanel(new GridLayout(0,3));
    private JPanel westGrid = new JPanel(new GridLayout(0,1));

    public GUI() {
        makeFrame();
    }

    private void makeCenter(){
        //get some ArrayList with show times for a specific movie
        ArrayList<String> l = new ArrayList<String>();
        l.add("Mandag 16:00");
        l.add("Mandag 18:30");
        l.add("Mandag 21:30");
        l.add("Onsdag 16:00");
        l.add("Onsdag 21:30");
        l.add("Torsdag 18:30");
        l.add("Torsdag 21:30");

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }
    }

    private void makeWest(){
        //get some ArrayList with movie names
        ArrayList<String> l = new ArrayList<String>();
        l.add("Langt filmnanv lolo");
        l.add("kortere");
        l.add("noget med kage");
        l.add("mere kage i rummet");
        l.add("blablablablablablablablablablabla");

        for(String s: l){
            westGrid.add(new JButton(s));
        }
    }

    private void makeFrame(){
        frame = new JFrame("BiografGUI");

        JPanel contentPane = (JPanel) frame.getContentPane();
        contentPane.setBorder(new EmptyBorder(12, 12, 12, 12));

        contentPane.setLayout(new BorderLayout(6, 6));

        //build WEST section of the window
        makeWest();;
        JPanel flow = new JPanel();
                flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        contentPane.add(flow, BorderLayout.WEST);

        //build NORTH section of the window
        JPanel northGrid = new JPanel(new GridLayout(1,0));
                northGrid.add(new JButton("Film"));
                northGrid.add(new JButton("Dag"));

        JPanel northFlow = new JPanel();
                northFlow.add(northGrid);

        contentPane.add(northFlow, BorderLayout.NORTH);

        //build CENTER section of the window
        makeCenter();
        contentPane.add(centerGrid, BorderLayout.CENTER);

        //building is done - arrange the components
        frame.pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);
        frame.setSize(480, 320);

        frame.setVisible(true);



    }
}
