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
    private JPanel filmPane = new JPanel(new BorderLayout(6*getScale(),6*getScale()));
    private JPanel dagPane = new JPanel(new BorderLayout(6*getScale(),6*getScale()));
    private JPanel centerGrid = new JPanel(new GridLayout(0,3));
    private JTabbedPane tabbedPane = new JTabbedPane();

    Ordere ordere = new Ordere();


    public GUI() {
        makeFrame();
    }

    private void getForestillingerTilFilm(String filmNavn){
        //get shows for specific films
        ArrayList<String[]> l = ordere.downloadForestillingerBestemtFilm(filmNavn);


        centerGrid.removeAll();

        for (String[] strings : l) {
            String s = strings[1] + " " + strings[2];
            JButton j = new JButton(s);
            j.setFont(getFont(8));
            centerGrid.add(j);
        }

        filmPane.add(centerGrid, BorderLayout.CENTER);
        filmPane.revalidate();
        filmPane.repaint();
    }

    private void getDag(String dag){
        //get shows for the given day, name and time
        ArrayList<String[]> l = ordere.downloadForestillingerBestemtDag(dag);

        centerGrid.removeAll();

        for (String[] strings : l) {
            String s = strings[0]+ "  " + strings[2];
            JButton j = new JButton(s);
            j.setFont(getFont(8));
            centerGrid.add(j);
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void makeFilmWest(){
        JPanel westGrid = new JPanel(new GridLayout(0,1));

        //get some ArrayList with movie names
        ArrayList<String> l = ordere.downloadFilms();

        for(final String s: l){
            JButton j = new JButton(s);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getForestillingerTilFilm(s);}
            });
            j.setFont(getFont(10));
            westGrid.add(j);
        }

        JPanel flow = new JPanel();
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        filmPane.add(flow, BorderLayout.WEST);
    }

    private void makeDagWest() {
        JPanel westGrid = new JPanel(new GridLayout(0,1));

        ArrayList<String> dage = new ArrayList<String>();
        dage.add("Mandag");
        dage.add("Tirsdag");
        dage.add("Onsdag");
        dage.add("Torsdag");
        dage.add("Fredag");
        dage.add("Lørdag");
        dage.add("Søndag");

        for(final String s: dage){
            JButton j = new JButton(s);
            j.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    getDag(s);}
            });
            j.setFont(getFont(10));
            westGrid.add(j);
        }

        JPanel flow = new JPanel();
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        dagPane.add(flow, BorderLayout.WEST);
    }

    private Font getFont(int size) {
        Font font = new Font("Times New Roman", Font.PLAIN, size*getScale());
        return font;
    }

    private int getScale() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        return d.width/900;
    }

    private void makeFrame(){

        //some spacing
        filmPane.setBorder(new EmptyBorder(12*getScale(), 12*getScale(), 12*getScale(), 12*getScale()));
        filmPane.setLayout(new BorderLayout(6*getScale(), 6*getScale()));
        dagPane.setBorder(new EmptyBorder(12*getScale(), 12*getScale(), 12*getScale(), 12*getScale()));
        dagPane.setLayout(new BorderLayout(6*getScale(), 6*getScale()));

        //build film tab content
        makeFilmWest();
        //getForestillingerTilFilm("Kagemanden og de syv små dværge");
        //makeFilmCenter();

        //build dag tab content
        makeDagWest();
        //getDag("Mandag");

        tabbedPane.addTab("Film", filmPane);
        tabbedPane.addTab("Dag", dagPane);
        tabbedPane.setFont(getFont(12));

        contentPane.add(tabbedPane);

        //building is done - arrange the components7
        frame.setFont(getFont(12));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(800 * getScale(), 400 * getScale());
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);
    }

    //action listeners
}
