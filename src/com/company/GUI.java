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
    private Controller controller = new Controller();

    public GUI() {makeFrame();}

    private void makeFilmWest(){
        JPanel westGrid = new JPanel(new GridLayout(0,1));

        //get some ArrayList with movie names
        ArrayList<String> l = controller.downloadFilms();

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

        westGrid.setFont(getFont());
        JPanel flow = new JPanel();
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        filmPane.add(flow, BorderLayout.WEST);
    }

    private void getForestillingerTilFilm(String navn){
        //get shows for specific films
        ArrayList<String> l = controller.getForestillingFilm(navn);

        centerGrid.removeAll();

        for (String s : l) {
            JButton btn = new JButton(s);

            //lægger film dag og tid i to separate strings så de
            //kan gives som input til ReservationGUI
            final String filmDag = s.substring(0, s.length() - 6);
            final String filmTid = s.substring(s.length() - 5);
            final String filmNavn = navn;

            btn.setFont(getFont());
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ReservationGUI(controller.getForestilling(filmNavn, filmDag, filmTid));
                }
            });
            centerGrid.add(btn);
        }

        filmPane.add(centerGrid, BorderLayout.CENTER);
        filmPane.revalidate();
        filmPane.repaint();
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

    private void getDag(String dag){
        //get shows for the given day, name and time
        ArrayList<String> l = controller.getForestillingDag(dag);

        centerGrid.removeAll();

        for (String s : l) {
            JButton btn = new JButton(s);

            //lægger film navn og tid i to separate strings så de kan
            //gives som input til ReservationGUI
            final String filmNavn = s.substring(0, s.length() - 6);
            final String filmTid = s.substring(s.length() - 5);
            final String filmDag = dag;

            btn.setFont(getFont());
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ReservationGUI(controller.getForestilling(filmNavn, filmDag, filmTid));
                }
            });
            centerGrid.add(btn);
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private Font getFont() {
        return new Font("Times New Roman", Font.PLAIN, 12 * getScale());
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
        //makeFilmCenter();

        //build dag tab content
        makeDagWest();
        getDag("Mandag");

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
