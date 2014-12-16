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
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JPanel filmPane = new JPanel(new BorderLayout(6*getScale(),6*getScale()));
    private JPanel dagPane = new JPanel(new BorderLayout(6*getScale(),6*getScale()));
    private JPanel reservationPane = new JPanel(new BorderLayout(6*getScale(), 6*getScale()));
    private JPanel reservationGrid = new JPanel(new GridLayout(0,2));
    private JPanel centerGrid = new JPanel(new GridLayout(0,3));
    private JPanel northLabel = new JPanel();
    private final GUI gui = this;
    private Controller controller = new Controller();

    public GUI() {makeFrame();}

    //bygger venstre side af filmtabben
    private void buildFilmWest(){
        JPanel flow = new JPanel();
        JPanel westGrid = new JPanel(new GridLayout(0,1));

        //henter arraylist med filmnavne
        ArrayList<String> l = controller.getFilms();

        //opretter en knap til hver film i film listen og lægger dem i
        //et grid med en enkelt kolonne
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

        westGrid.setFont(getFont(10));
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        filmPane.add(flow, BorderLayout.WEST);
    }

    //bygger filmtabbens centergrid baseret på hvilken film der klikkes på
    private void getForestillingerTilFilm(String navn){
        //henter forestillinger til hver film
        ArrayList<String> l = controller.getForestillingFilm(navn);

        //viser navnet på filmen der er sorteret efter
        northLabel.removeAll();
        JLabel filmLabel = new JLabel("Forestillinger for " + navn, SwingConstants.CENTER);
        filmLabel.setFont(getFont(14 * getScale()));
        northLabel.add(filmLabel);
        filmPane.add(northLabel, BorderLayout.NORTH);

        //først rydes det nuværende grid så der kan fyldes nyt i
        centerGrid.removeAll();

        for (String s : l) {
            JButton btn = new JButton(s);

            //lægger filmdag og -tid i to separate strings så de
            //kan gives som input til ReservationGUI. Den oprindelige string
            //er i formatet dag + " " + tid, og da tiden er i formatet TT:MM,
            //kan vi dele strengen ved de 5 sidste karaktere
            final String filmDag = s.substring(0, s.length() - 6);
            final String filmTid = s.substring(s.length() - 5);
            final String filmNavn = navn;

            btn.setFont(getFont(10));
            //hvis en knap klikkes, oprettes et nyt ReservationGUI for den givne forestilling
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ReservationGUI(controller.getForestilling(filmNavn, filmDag, filmTid), gui);
                }
            });
            centerGrid.add(btn);
        }

        filmPane.add(centerGrid, BorderLayout.CENTER);
        filmPane.revalidate();
        filmPane.repaint();
    }


    //fylder dagtabbens venstre side af vinduet med knapper svarende
    //til hver dag i ugen. Hver knap opdaterer centergriddet med passende
    //forestilling hvis der klikkes på dem
    private void buildDagWest() {
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

    //henter en given dags forestilling ned og sætter passende knapper
    //ind i dagtabbens centergrid
    private void getDag(String dag){
        //henter dagens forestillinger med filmnavn og visningstid
        ArrayList<String> l = controller.getForestillingDag(dag);

        //viser dagen der er sorteret efter
        northLabel.removeAll();
        JLabel dagLabel = new JLabel("Forestillinger for " + dag, SwingConstants.CENTER);
        dagLabel.setFont(getFont(14*getScale()));
        northLabel.add(dagLabel);
        dagPane.add(northLabel, BorderLayout.NORTH);

        //først rydes det nuværende grid så der kan fyldes nyt i
        centerGrid.removeAll();

        for (String s : l) {
            JButton btn = new JButton(s);

            //lægger film navn og tid i to separate strings så de kan
            //gives som input til ReservationGUI
            final String filmNavn = s.substring(0, s.length() - 6);
            final String filmTid = s.substring(s.length() - 5);
            final String filmDag = dag;

            btn.setFont(getFont(10));
            //hvis en knap klikkes, oprettes et nyt ReservationGUI for den givne forestilling
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new ReservationGUI(controller.getForestilling(filmNavn, filmDag, filmTid), gui);
                }
            });
            centerGrid.add(btn);
        }

        dagPane.add(centerGrid, BorderLayout.CENTER);
        dagPane.revalidate();
        dagPane.repaint();
    }

    private void buildSletReservationNorth() {

        JPanel northGrid = new JPanel(new GridLayout(2,1));

        JPanel northFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JTextField tlfNrInput = new JTextField(8);
        JLabel indtastTlfNr = new JLabel("Indtast Telefonnummer");
        JButton search = new JButton("Søg");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tlfNr = tlfNrInput.getText();
                if (tlfNr == null || tlfNr.length() != 8 || !isNumeric(tlfNr)) {
                    JOptionPane.showMessageDialog(frame, "Telefonnummeret skal bestå af præcist 8 tal");
                } else {
                    buildSletReservationTable(tlfNr);
                }
            }
        });

        JPanel northLabels = new JPanel(new GridLayout(1,2));
        JPanel northGridLabels_1 = new JPanel(new GridLayout(1,1));
        JPanel northGridLabels_2 = new JPanel(new GridLayout(1,5));

        ArrayList<String> columnNames = new ArrayList<String>();
        columnNames.add("Film");
        columnNames.add("Dag");
        columnNames.add("Tid");
        columnNames.add("Række");
        columnNames.add("Sæde");
        columnNames.add("Slet");

        for(int i = 0; i < 6; i++) {

            if (i == 0) {
                northGridLabels_1.add(new JLabel(columnNames.get(i), SwingConstants.CENTER));
            } else {
                northGridLabels_2.add(new JLabel(columnNames.get(i), SwingConstants.CENTER));
            }
        }

        northLabels.add(northGridLabels_1);
        northLabels.add(northGridLabels_2);

        JButton sletAlle = new JButton("Slet Alle");
        sletAlle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tlfNr = tlfNrInput.getText();
                if (tlfNr == null || tlfNr.length() != 8 || !isNumeric(tlfNr)) {
                    JOptionPane.showMessageDialog(frame, "Indtast et telefonnummer");
                } else {

                    if (JOptionPane.showConfirmDialog(null, "Slet alle?", "Bekræft",
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        controller.sletReservationer(tlfNr);
                        buildSletReservationTable(tlfNr);
                    }
                    //ellers gøres intet og dialogen lukkes
                }
            }
        });

        northFlow.add(indtastTlfNr);
        northFlow.add(tlfNrInput);
        northFlow.add(search);
        northFlow.add(sletAlle);
        northGrid.add(northFlow);
        northGrid.add(northLabels);
        reservationPane.add(northGrid, BorderLayout.NORTH);
    }

    private ArrayList<ArrayList<String>> searchTlfNr(String tlfNr){

        ArrayList<Billet> billetList = controller.getReservation(tlfNr);
        ArrayList<ArrayList<String>> toBeReturned = new ArrayList<ArrayList<String>>();

        for(Billet billet : billetList) {
            int forestillingId = billet.getForestil_id();

            String dag = controller.getForestillingsDag(forestillingId);
            String tid = controller.getForestillingsTid(forestillingId);
            String film = controller.getForestillingsNavn(forestillingId);
            String række = Integer.toString(billet.getRække());
            String sæde = Integer.toString(billet.getSæde_nr());

            ArrayList<String> data = new ArrayList<String>();

            data.add(film);
            data.add(dag);
            data.add(tid);
            data.add(række);
            data.add(sæde);

            toBeReturned.add(data);
        }

        return toBeReturned;
    }


    private void buildSletReservationTable(final String tlfNr) {

        reservationGrid.removeAll();
        ArrayList<ArrayList<String>> billet = searchTlfNr(tlfNr);
        JPanel reservationGrid_1 = new JPanel(new GridLayout(0,1));
        JPanel reservationGrid_2 = new JPanel(new GridLayout(0,5));

        for(final ArrayList<String> list : billet) {

            JLabel film = new JLabel(list.get(0), SwingConstants.CENTER);
            film.setBorder(new LineBorder(Color.BLACK));
            reservationGrid_1.add(film);

            for(int i = 1; i < 5; i++) {

                JLabel data = new JLabel(list.get(i), SwingConstants.CENTER);
                reservationGrid_2.add(data);
            }

            JButton slet = new JButton("Slet");
            slet.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    int fore_id = controller.getForestilling(list.get(0), list.get(1), list.get(2)).getForstil_id();

                    controller.sletReservation(fore_id, list.get(3), list.get(4));

                    buildSletReservationTable(tlfNr);
                }
            });

            reservationGrid_2.add(slet);
        }

        reservationGrid.add(reservationGrid_1);
        reservationGrid.add(reservationGrid_2);
        reservationGrid.revalidate();
        reservationGrid.repaint();
        reservationPane.add(reservationGrid, BorderLayout.CENTER);
        reservationPane.revalidate();
        reservationPane.repaint();
    }

    private Font getFont(int size) {
        Font font = new Font("Times New Roman", Font.PLAIN, size*getScale());
        return font;
    }

    //skaleringsfaktor til skriftstørrelse så der tages højde
    //for brugerens skærmopløsning
    private int getScale() {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();

        return d.width/900;
    }

    public Controller getController() {
        return controller;
    }

    //bruges til at tjekke om den indkommende tlf. nr. kun
    //består af tal
    public boolean isNumeric(String str){
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    private void makeFrame(){

        //lidt luft i kanterne
        filmPane.setBorder(new EmptyBorder(12*getScale(), 12*getScale(), 12*getScale(), 12*getScale()));
        //filmPane.setLayout(new BorderLayout(6*getScale(), 6*getScale()));
        dagPane.setBorder(new EmptyBorder(12*getScale(), 12*getScale(), 12*getScale(), 12*getScale()));
        //dagPane.setLayout(new BorderLayout(6*getScale(), 6*getScale()));

        //bygger film tabben
        buildFilmWest();

        //bygger dag tabben
        buildDagWest();

        //bygger sletReservation tabben
        buildSletReservationNorth();
        //buildSletReservationTable();

        //samler det hele
        tabbedPane.addTab("Film", filmPane);
        tabbedPane.addTab("Dag", dagPane);
        tabbedPane.addTab("Slet reservation", reservationPane);
        tabbedPane.setFont(getFont(12));

        contentPane.add(tabbedPane);

        //opbygning færdig
        frame.setFont(getFont(12));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        //centrerer vinduet
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(800 * getScale(), 400 * getScale());
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);
    }
}
