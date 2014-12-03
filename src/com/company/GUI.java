package com.company;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Jonas on 03-12-2014.
 */
public class GUI {
    private JFrame frame = new JFrame("Biograf GUI");
    private JPanel contentPane = (JPanel) frame.getContentPane();
    private JPanel filmPane = new JPanel(new BorderLayout(6,6));
    private JPanel dagPane = new JPanel(new BorderLayout(6,6));
    private JPanel centerGrid = new JPanel(new GridLayout(0,3));
    private JTabbedPane tabbedPane = new JTabbedPane();

    Ordere ordere = new Ordere();


    public GUI() {
        makeFrame();
    }

    private void makeFilmCenter(){
        JPanel centerGrid = new JPanel(new GridLayout(0,3));

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

        filmPane.add(centerGrid, BorderLayout.CENTER);
    }

    private void getMandag(){
        //get shows for mandag
        ArrayList<ArrayList<String>> l = ordere.filmTid("Mandag");
        ArrayList<String> list = new ArrayList<String>();

        centerGrid.removeAll();

        ArrayList<String> film = l.get(0);
        ArrayList<String> tid = l.get(1);

        for(int i = 0; i < film.size(); i++) {
            list.add(film.get(i) + " " + tid.get(i));
        }

        for(String s: list) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void getTirsdag(){
        //get shows for tirsdag
        ArrayList<String> l = new ArrayList<String>();

        l.add("filmen her");
        l.add("blablabla");
        l.add("blablablabal");

        centerGrid.removeAll();

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void getOnsdag(){
        //get shows for onsdag
        ArrayList<String> l = new ArrayList<String>();

        l.add("filmen her");
        l.add("blablabla");
        l.add("blablablabal");

        centerGrid.removeAll();

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void getTorsdag(){
        //get shows for torsdag
        ArrayList<String> l = new ArrayList<String>();

        l.add("filmen her");
        l.add("blablabla");
        l.add("blablablabal");

        centerGrid.removeAll();

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void getFredag(){
        //get shows for fredag
        ArrayList<String> l = new ArrayList<String>();

        l.add("filmen her");
        l.add("blablabla");
        l.add("blablablabal");

        centerGrid.removeAll();

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void getLørdag(){
        //get shows for lørdag
        ArrayList<String> l = new ArrayList<String>();

        l.add("filmen her");
        l.add("blablabla");
        l.add("blablablabal");

        centerGrid.removeAll();

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void getSøndag(){
        //get shows for søndag
        ArrayList<String> l = new ArrayList<String>();

        l.add("filmen her");
        l.add("blablabla");
        l.add("blablablabal");

        centerGrid.removeAll();

        for(String s: l) {
            centerGrid.add(new JButton(s));
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void makeFilmWest(){
        JPanel westGrid = new JPanel(new GridLayout(0,1));

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

        JPanel flow = new JPanel();
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        filmPane.add(flow, BorderLayout.WEST);
    }

    private void makeDagWest() {
        JPanel westGrid = new JPanel(new GridLayout(0,1));

        JButton mandag = new JButton("Mandag");
        mandag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getMandag();}
        });
        JButton tirsdag = new JButton("Tirsdag");
        tirsdag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getTirsdag();}
        });
        JButton onsdag = new JButton("Onsdag");
        onsdag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getOnsdag();}
        });
        JButton torsdag = new JButton("Torsdag");
        torsdag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getTorsdag();}
        });
        JButton fredag = new JButton("Fredag");
        fredag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getFredag();}
        });
        JButton lørdag = new JButton("Lørdag");
        lørdag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getLørdag();}
        });
        JButton søndag = new JButton("Søndag");
        søndag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {getSøndag();}
        });

        westGrid.add(mandag);
        westGrid.add(tirsdag);
        westGrid.add(onsdag);
        westGrid.add(torsdag);
        westGrid.add(fredag);
        westGrid.add(lørdag);
        westGrid.add(søndag);

        JPanel flow = new JPanel();
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        dagPane.add(flow, BorderLayout.WEST);
    }


    private int getScale() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        return d.width/1366;
    }

    private void makeFrame(){

        //some spacing
        filmPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        filmPane.setLayout(new BorderLayout(6, 6));
        dagPane.setBorder(new EmptyBorder(12, 12, 12, 12));
        dagPane.setLayout(new BorderLayout(6, 6));

        //build film tab
        makeFilmWest();
        makeFilmCenter();

        //build dag tab
        makeDagWest();
        getMandag();

        tabbedPane.addTab("Film", filmPane);
        tabbedPane.addTab("Dag", dagPane);

        contentPane.add(tabbedPane);

        //building is done - arrange the components7
        Font font = new Font("Times New Roman", Font.PLAIN, 12*getScale());
                frame.setFont(font);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(480, 320);
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);
    }

    //action listeners
}
