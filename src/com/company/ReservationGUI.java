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


    public ReservationGUI(String navn, String dag, String tid) {

        this.forestillingNavn = navn;
        this.forestillingTid = tid;
        this.forestillingDag = dag;

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

        JPanel seatGrid = new JPanel(new GridLayout(10,10));
        seatGrid.setBorder(new EmptyBorder(12, 12, 12, 12));

        for(int i = 1; i < 11; i++) {
            for (int j = 1; j < 11; j++) {

                final String row = Integer.toString(i);
                final String seat = Integer.toString(j);

                String btnName = row + seat;

                final JButton btn = new JButton(btnName);
                btn.setBackground(Color.GREEN);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Pressed " + row + " " + seat);
                        //makeReservation(forestilling, row, seat);
                        if (btn.getBackground().equals(Color.GREEN)){
                            btn.setBackground(Color.MAGENTA);
                        } else {
                            btn.setBackground(Color.GREEN);
                        }
                    }
                });
                JPanel flow = new JPanel();
                flow.add(btn);
                seatGrid.add(flow);
            }
        }


        reservationPane.add(seatGrid, BorderLayout.CENTER);

    }

    private void toBeReserved(int row, int seat){


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
