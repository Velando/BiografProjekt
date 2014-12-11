package com.company;

import com.mysql.jdbc.StringUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Jonas on 07-12-2014.
 */
public class ReservationGUI {

    private JFrame frame = new JFrame("Reservation");
    private JPanel contentPane = (JPanel) frame.getContentPane();
    private JPanel reservationPane = new JPanel(new BorderLayout(6,6));
    private String forestillingNavn;
    private String forestillingTid;
    private String forestillingDag;
    private ArrayList<ArrayList<Integer>> toBeReserved = new ArrayList<ArrayList<Integer>>();
    private Forestilling f;
    private GUI gui;


    public ReservationGUI(Forestilling f, GUI parent) {

        forestillingNavn = f.getFilmNavn();
        forestillingTid = f.getTid();
        forestillingDag = f.getDag();
        this.f = f;
        this.gui = parent;
        makeFrame();
    }

    //diverse labels i toppen af vinduet
    private void buildNorth() {

        String dagTid = forestillingDag + " " + forestillingTid;
        String navn = forestillingNavn;

        JLabel forestillingNavn = new JLabel(navn + " " + dagTid, SwingConstants.CENTER);
        //JLabel forestillingDagTid = new JLabel(dagTid, SwingConstants.RIGHT);

        forestillingNavn.setFont(new Font("Times New Roman", Font.BOLD, 18));

        reservationPane.add(forestillingNavn, BorderLayout.NORTH);
        //reservationPane.add(forestillingDagTid, BorderLayout.NORTH);
    }


    private void buildSouth() {

        JPanel border = new JPanel(new BorderLayout(4,4));
        JPanel flow = new JPanel();

        JButton reserve = new JButton("Reservér");
        //reservér knappen laver et inputDialog vindue når den bliver
        //trykket på, der beder brugeren om et telefonnummer som input.
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //tjekker at der er blevet valgt sæder til reservation
                if(toBeReserved.size() == 0) {
                    JOptionPane.showMessageDialog(frame, "Vælg pladser der skal reserveres!");
                    return;
                }

                String dialogInput = (String)JOptionPane.showInputDialog(
                        frame,
                        "Indtast telefonnummer og bekræft reservationen",
                        "Fuldfør Reservation",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Indtast telfonnummer");

                //Tjekker input for at bestå af netop 8 tal (dansk telefonnummer)
                if(dialogInput != null) {
                    if (dialogInput.length() != 8 || !isNumeric(dialogInput)) {
                        JOptionPane.showMessageDialog(frame, "Telefonnummeret skal bestå af præcist 8 tal");
                    } else {
                        //input er tjekket, bekræft reservationer
                        makeReservation(dialogInput);

                        //frame.dispose();
                    }
                }
            }
        });
        JButton annuller = new JButton("Annullér");
        annuller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        flow.add(reserve);
        flow.add(annuller);

        border.add(flow, BorderLayout.EAST);

        reservationPane.add(border, BorderLayout.SOUTH);
    }

    //fylder et gridlayout der har størrelse svarende til forestillingens
    //sal med røde eller grønne knapper alt efter om et givent sæde er
    //reserveret eller ej. Hvert sæde lytter efter om det bliver klikket på,
    //og skifter farve til magenta hvis dette sker, og tilføjer række- og
    //sædenummer til listen toBeReserved. Hvis en magenta knap trykkes på,
    //skifter den farve til grøn og fjerner sig selv fra toBeReserved
    private void buildGrid() {

        JPanel seatGrid = new JPanel(new GridLayout(f.getSalRækker(),f.getSalSæder()));
        seatGrid.setBorder(new EmptyBorder(12, 12, 12, 12));

        for(int i = 1; i <= f.getSalRækker(); i++) {
            for (int j = 1; j <= f.getSalSæder(); j++) {

                final int rowInt = i;
                final int seatInt = j;

                String btnName = Integer.toString(rowInt)+ Integer.toString(seatInt);

                final JButton btn = new JButton(btnName);

                if(f.getResSæder()[i-1][j-1]) {
                    btn.setBackground(Color.RED);
                } else {
                    btn.setBackground(Color.GREEN);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (btn.getBackground().equals(Color.GREEN)){
                                btn.setBackground(Color.MAGENTA);
                                toBeReserved(rowInt, seatInt);
                            } else {
                                btn.setBackground(Color.GREEN);
                                cancelReserved(rowInt, seatInt);
                            }
                        }
                    });
                }
                JPanel flow = new JPanel();
                flow.add(btn);
                seatGrid.add(flow);
            }
        }

        reservationPane.add(seatGrid, BorderLayout.CENTER);
    }


    //holder styr på hvilke sæder der er markeret i reservationsgui'et
    //ved at tage række- og sædenummer og lægge dem i en ArrayList<Integer>
    //og tilføjer denne liste til listen toBeReturned, som holder alle
    //markerede sæder.
    private void toBeReserved(int row, int seat){
        ArrayList<Integer> rowAndSeat = new ArrayList<Integer>();

        rowAndSeat.add(row);
        rowAndSeat.add(seat);

        toBeReserved.add(rowAndSeat);
        System.out.println(toBeReserved);
    }

    //annullerer et markeret sæde, så det ikke længere er i
    //toBeReserved listen.
    //Der itereres over den nuværende toBeReserved liste
    //indtil der bliver fundet en ArrayList<Integer> med samme
    //række- og sædenummer, som det sæde vi ønsker at fjerne, som så
    //fjernes og loopet stoppes
    private void cancelReserved(int row, int seat) {

        ArrayList<Integer> cancelReserved = new ArrayList<Integer>();
        cancelReserved.add(row);
        cancelReserved.add(seat);

        for(ArrayList<Integer> intList : toBeReserved) {
            if(intList.get(0).equals(cancelReserved.get(0))
                    && intList.get(1).equals(cancelReserved.get(1))) {
                toBeReserved.remove(intList);
                break;
            }
        }

        System.out.println(toBeReserved);
    }

    //bruges til at tjekke om den indkommende tlf. nr. kun
    //består af tal
    private boolean isNumeric(String str)
    {
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }

    //Klargører den nødvendige information til reservation af billetter
    public void makeReservation(String telefonNr) {
        System.out.println(telefonNr);
        System.out.println(toBeReserved);
        System.out.println(f.getForstil_id());

        //udkommenteret metode køres som argument i den anden (returnerer res_id som string, lidt rodet). Der gemmes
        //reserveres derfor dobbelt hvis begge kaldes.
        //gui.getController().getDb().sqlCommandInsertInto(telefonNr, f.getForstil_id(), toBeReserved);
        gui.getController().newReservation(f.getForstil_id(), Integer.parseInt(gui.getController().getDb().sqlCommandInsertInto(telefonNr, f.getForstil_id(), toBeReserved)), Integer.parseInt(telefonNr), toBeReserved);
    }


    //endelig opbygning af vinduet
    public void makeFrame() {

        buildGrid();
        buildNorth();
        buildSouth();

        contentPane.add(reservationPane);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(640, 480);
        //centrerer vinduet i forhold til skærmopløsningen
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);
    }

}
