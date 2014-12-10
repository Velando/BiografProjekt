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


    public ReservationGUI(Forestilling f) {

        forestillingNavn = f.getFilmNavn();
        forestillingTid = f.getTid();
        forestillingDag = f.getDag();
        this.f = f;
        makeFrame();
    }

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
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        JPanel flow = new JPanel();

        //final JTextField tlfInput = new JTextField(8);
        JLabel tlfNr = new JLabel("Indtast Telefonnummer");

        flow.add(tlfNr);
        //box.add(tlfInput);

        JButton reserve = new JButton("Reservér");
        //reservér knappen indleder reservationen af alle markerede
        //sæder til det indtastede telefonnummer. Telefonnummeret
        //tjekkes for at bestå af 8 og kun 8 tal
        reserve.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //String tlfNr = tlfInput.getText();

                if(toBeReserved.equals("")) {
                    JOptionPane.showMessageDialog(frame, "Vælg pladser der skal reserveres!");
                    return;
                }

                String dialogResult = (String)JOptionPane.showInputDialog(
                        frame,
                        "Indtast telefonnummer og bekræft reservationen",
                        "Bekræft reservation",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        "Indtast telfonnummer");

                if(dialogResult != null) {
                    if (dialogResult.length() != 8 || !isNumeric(dialogResult)) {
                        JOptionPane.showMessageDialog(frame, "Telefonnummeret skal bestå af præcist 8 tal");
                    } else {
                        //input er tjekket, bekræft reservationer
                        makeReservation(dialogResult);
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

        flow.add(box);
        flow.add(reserve);
        flow.add(annuller);

        border.add(flow, BorderLayout.EAST);

        reservationPane.add(border, BorderLayout.SOUTH);
    }

    //fylder et gridlayout der har størrelse svarende til forestillingens
    //sal med røde eller grøne knapper alt efter om et givent sæde er
    //reserveret eller ej
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

    //annullere et markeret sæde, så det ikke længere er i
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

    private void makeReservation(String telefonNr) {
        System.out.println(telefonNr);
        System.out.println(toBeReserved);
        System.out.println(f.getFilm_id());
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
