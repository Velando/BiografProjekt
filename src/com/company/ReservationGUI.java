package com.company;

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
        JPanel flow = new JPanel();

        JLabel lærred = new JLabel("Lærred her", SwingConstants.CENTER);
        lærred.setFont(new Font("Times New Roman", Font.BOLD, 16));

        JButton reserve = new JButton("Reservér");
        JButton annuller = new JButton("Annullér");
        annuller.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        flow.add(reserve);
        flow.add(annuller);

        border.add(lærred, BorderLayout.CENTER);
        border.add(flow, BorderLayout.EAST);

        reservationPane.add(border, BorderLayout.SOUTH);
    }

    private void buildGrid() {

        JPanel seatGrid = new JPanel(new GridLayout(f.getSalRækker(),f.getSalSæder()));
        seatGrid.setBorder(new EmptyBorder(12, 12, 12, 12));

        for(int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {

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

    private void toBeReserved(int row, int seat){
        ArrayList<Integer> rowAndSeat = new ArrayList<Integer>();

        rowAndSeat.add(row);
        rowAndSeat.add(seat);

        toBeReserved.add(rowAndSeat);
        System.out.println(toBeReserved);
    }

    private void cancelReserved(int row, int seat) {

        ArrayList<Integer> cancelReserved = new ArrayList<Integer>();
        cancelReserved.add(row);
        cancelReserved.add(seat);

        for(ArrayList<Integer> intList : toBeReserved) {
            if(intList.get(0).equals(cancelReserved.get(0))
                    && intList.get(1).equals(cancelReserved.get(1))) {
                toBeReserved.remove(intList);
            }
        }

        System.out.println(toBeReserved);
    }



    public void makeFrame() {

        buildGrid();
        buildNorth();
        buildSouth();

        contentPane.add(reservationPane);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(640, 480);
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);
    }

}
