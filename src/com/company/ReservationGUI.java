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
    private String forestillingName;
    private String forestillingTid;

    public ReservationGUI() {

        //this.forestillingName = forestilling;
        //this.forestillingTid = tid;

        makeFrame();
    }

    private void buildNorth() {

        JLabel forestilling = new JLabel("Forestilling: blablakage   Tid: 00:00", SwingConstants.CENTER);
        forestilling.setFont(new Font("Times New Roman", Font.BOLD, 18));

        reservationPane.add(forestilling, BorderLayout.NORTH);
    }

    private void buildSouth() {
        JLabel lærred = new JLabel("Lærred her", SwingConstants.CENTER);
        lærred.setFont(new Font("Times New Roman", Font.BOLD, 16));
        reservationPane.add(lærred, BorderLayout.SOUTH);
    }

    private void buildGrid() {

        JPanel seatGrid = new JPanel(new GridLayout(10,10));
        seatGrid.setBorder(new EmptyBorder(12, 12, 12, 12));

        Ordere o = new Ordere();

        ArrayList<Boolean> seats = o.downloadSeatsForforestilling(1);

        int i = 1;
        int j = 1;

        for (Boolean sæde : seats) {
            if(sæde == null){
                for (int j_o = j ;j_o < 11; j_o++) {
                    final int row = i;
                    final int seat = j_o;

                    String btnName = Integer.toString(i) + " " + Integer.toString(j_o);
                    final JButton btn = new JButton(btnName);
                    btn.setBackground(Color.GREEN);
                    btn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("Pressed " + Integer.toString(row) + " " + Integer.toString(seat));
                            btn.setBackground(Color.BLUE);
                            //makeReservation(forestilling, tlf_nr, row, seat);
                        }
                    });
                    JPanel flow = new JPanel();
                    flow.add(btn);
                    seatGrid.add(flow);
                }
                i++;
                j = 1;
            }else if (sæde == false){
                final int row = i;
                final int seat = j;

                String btnName = Integer.toString(i) + " " + Integer.toString(j);
                final JButton btn = new JButton(btnName);
                btn.setBackground(Color.GREEN);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Pressed " + Integer.toString(row) + " " + Integer.toString(seat));
                        btn.setBackground(Color.BLUE);
                        //makeReservation(forestilling, tlf_nr, row, seat);
                    }
                });
                JPanel flow = new JPanel();
                flow.add(btn);
                seatGrid.add(flow);
                j++;
            }else{
                final int row = i;
                final int seat = j;

                String btnName = Integer.toString(i) + " " + Integer.toString(j);
                final JButton btn = new JButton(btnName);
                btn.setBackground(Color.RED);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Pressed " + Integer.toString(row) + " " + Integer.toString(seat));
                        //makeReservation(forestilling, tlf_nr, row, seat);
                    }
                });
                JPanel flow = new JPanel();
                flow.add(btn);
                seatGrid.add(flow);
                j++;
            }
        }

        while (i < 11){
            for (int j_o = j ;j_o < 11; j_o++) {
                final int row = i;
                final int seat = j_o;

                String btnName = Integer.toString(i) + " " + Integer.toString(j_o);
                final JButton btn = new JButton(btnName);
                btn.setBackground(Color.GREEN);
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        System.out.println("Pressed " + Integer.toString(row) + " " + Integer.toString(seat));
                        btn.setBackground(Color.BLUE);
                        //makeReservation(forestilling, tlf_nr, row, seat);
                    }
                });
                JPanel flow = new JPanel();
                flow.add(btn);
                seatGrid.add(flow);
            }
            i++;
            j = 1;
        }


        reservationPane.add(seatGrid, BorderLayout.CENTER);

    }



    public void makeFrame() {

        buildGrid();
        buildNorth();
        buildSouth();

        contentPane.add(reservationPane);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(640, 480);
        frame.setLocation(d.width/2 - frame.getWidth()/2, d.height/2 - frame.getHeight()/2);

        frame.setVisible(true);
    }

}
