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
    private JPanel centerGrid = new JPanel(new GridLayout(0,3));
    private Controller controller = new Controller();
    private final GUI gui = this;

    public GUI() {makeFrame();}

    //bygger venstre side af filmtabben
    private void makeFilmWest(){
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

        westGrid.setFont(getFont());
        flow.add(westGrid);
        flow.setBorder(new LineBorder(Color.BLACK));

        filmPane.add(flow, BorderLayout.WEST);
    }

    //bygger filmtabbens centergrid baseret på hvilken film der klikkes på
    private void getForestillingerTilFilm(String navn){
        //henter forestillinger til hver film
        ArrayList<String> l = controller.getForestillingFilm(navn);

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

            btn.setFont(getFont());
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

    //henter en given dags forestilling ned og sætter passende knapper
    //ind i dagtabbens centergrid
    private void getDag(String dag){
        //henter dagens forestillinger med filmnavn og visningstid
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

    private void makeSletReservationNorth() {

        JPanel northFlow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JTextField tlfNrInput = new JTextField(8);
        JLabel indtastTlfNr = new JLabel("Indtast Telefonnummer");
        JButton search = new JButton("Søg");
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tlfNr = tlfNrInput.getText();

                controller.sletReservationer(tlfNr);
            }
        });

        northFlow.add(indtastTlfNr);
        northFlow.add(tlfNrInput);
        northFlow.add(search);
        reservationPane.add(northFlow, BorderLayout.NORTH);
    }

    /**
    private ArrayList<Object> searchTlfNr(String tlfNr){
        ArrayList<Billet> billetList = controller.getReservation(tlfNr);

        for(Billet billet : billetList) {
            int forestillingId = billet.getForestil_id();

            String dag = controller.getForestillingsDag(forestillingId);
            String tid = controller.getForestillingsTid(forestillingId);
            String film = controller.getForestillingsNavn(forestillingId);
            Integer række = billet.getRække();
            Integer sæde = billet.getSæde_nr();

            ArrayList<Object> info = new ArrayList<Object>();
            info.add(film);
            info.add(dag);
            info.add(tid);
            info.add(række);
            info.add(sæde);
        }

        return null;
    }


    private void makeSletReservationTable() {

        String[] columnNames = {"Film",
                "Dag",
                "Tid",
                "Række",
                "Sæde",
                "Slet Reservation"};

        Object[][] billet = new Object[1][6];
        billet[0][0] = "hej";
        billet[0][1] = "mandag";
        billet[0][2] = "21:30";
        billet[0][3] = "2";
        billet[0][4] = "3";
        billet[0][5] = new JButton("Slet");

        JTable table = new JTable(billet, columnNames);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        reservationPane.add(scrollPane);
    }
     **/

    private Font getFont() {
        return new Font("Times New Roman", Font.PLAIN, 12 * getScale());
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

    private void makeFrame(){

        //lidt luft i kanterne
        filmPane.setBorder(new EmptyBorder(12*getScale(), 12*getScale(), 12*getScale(), 12*getScale()));
        filmPane.setLayout(new BorderLayout(6*getScale(), 6*getScale()));
        dagPane.setBorder(new EmptyBorder(12*getScale(), 12*getScale(), 12*getScale(), 12*getScale()));
        dagPane.setLayout(new BorderLayout(6*getScale(), 6*getScale()));

        //bygger film tabben
        makeFilmWest();

        //bygger dag tabben
        makeDagWest();

        //bygger sletReservation tabben
        makeSletReservationNorth();
        //makeSletReservationTable();

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
