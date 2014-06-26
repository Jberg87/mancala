package nl.sogyo.mancala.v2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by jvdberg on 23/05/2014.
 */
public class Spel {
    private ArrayList<KleinVak> speelbareVakken = new ArrayList<KleinVak>();
    private ArrayList<GrootVak> kalahas = new ArrayList<GrootVak>();
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private Speler spelerA, spelerB;
    private JFrame frame = new JFrame();
    private JLabel panelKalahaA, panelKalahaB;

    public void zetSpelersOp() {
        spelerA = new Speler();
        spelerB = new Speler();
        spelerA.setTegenspeler(spelerB);
        spelerB.setTegenspeler(spelerA);
        spelerA.krijgBeurt();
    }

    public void maakSpeelBord() {
        creeerVakken(spelerA);
        creeerVakken(spelerB);
        geefVakkenVolgendVak();
        maakBordUi();
    }

    private void creeerVakken(Speler speler) {

        for (int i = 0; i < 6; i++) {
            speelbareVakken.add(new KleinVak(4, speler));
            buttons.add(new JButton());
        }
        kalahas.add(new GrootVak(speler));
    }

    public void maakBordUi() {
        panelKalahaA = new JLabel("0  ");
        panelKalahaB = new JLabel("  0");
        Font kalahaFont = new Font("arial", Font.BOLD,48);
        panelKalahaA.setFont(kalahaFont);
        panelKalahaB.setFont(kalahaFont);
        frame.getContentPane().add(BorderLayout.EAST, panelKalahaA);
        frame.getContentPane().add(BorderLayout.WEST, panelKalahaB);


        JPanel kleineVakkenA = new JPanel();
        for (int i = 0; i < 6; i++) {
            buttons.get(i).setText(String.valueOf(speelbareVakken.get(i).getStenen()));
            kleineVakkenA.add(buttons.get(i));
        }
        frame.getContentPane().add(BorderLayout.SOUTH, kleineVakkenA);
        Font kleinVakFont = new Font("arial", Font.BOLD,36);
        kleineVakkenA.setFont(kleinVakFont);


        JPanel kleineVakkenB = new JPanel();
        for (int i = 11; i > 5; i--) {
            buttons.get(i).setText(String.valueOf(speelbareVakken.get(i).getStenen()));
            kleineVakkenB.add(buttons.get(i));
        }
        frame.getContentPane().add(BorderLayout.NORTH, kleineVakkenB);
        kleineVakkenB.setFont(kleinVakFont);


        frame.setSize(500,150);
        frame.setVisible(true);
    }

    public void geefVakkenVolgendVak() {
        for (int i = 0; i < 12; i++) {
            if (i < 5) {
                speelbareVakken.get(i).setVolgendVak(speelbareVakken.get(i + 1));
            } else if (i == 5) {
                speelbareVakken.get(i).setVolgendVak(kalahas.get(0));
                kalahas.get(0).setVolgendVak(speelbareVakken.get(6));
            } else if (i > 5 && i < 11) {
                speelbareVakken.get(i).setVolgendVak(speelbareVakken.get(i + 1));
            } else {
                speelbareVakken.get(i).setVolgendVak(kalahas.get(1));
                kalahas.get(1).setVolgendVak(speelbareVakken.get(0));
            }
        }
    }
    
    public void makeActionListeners() {
        buttons.get(0).addActionListener(new Vakje1Listener());
        buttons.get(1).addActionListener(new Vakje2Listener());
        buttons.get(2).addActionListener(new Vakje3Listener());
        buttons.get(3).addActionListener(new Vakje4Listener());
        buttons.get(4).addActionListener(new Vakje5Listener());
        buttons.get(5).addActionListener(new Vakje6Listener());
        buttons.get(6).addActionListener(new Vakje7Listener());
        buttons.get(7).addActionListener(new Vakje8Listener());
        buttons.get(8).addActionListener(new Vakje9Listener());
        buttons.get(9).addActionListener(new Vakje10Listener());
        buttons.get(10).addActionListener(new Vakje11Listener());
        buttons.get(11).addActionListener(new Vakje12Listener());

    }

    private void updateStenenInVakken() {
        for (int i = 0; i < 12; i++) {
            buttons.get(i).setText(String.valueOf(speelbareVakken.get(i).getStenen()));
        }
        panelKalahaA.setText(String.valueOf(kalahas.get(0).getStenen()));
        panelKalahaB.setText(String.valueOf(kalahas.get(1).getStenen()));
    }

    public void speelVak(int vakNummer) {
        speelbareVakken.get(vakNummer).zet();
        updateStenenInVakken();
        frame.repaint();
        checkWinnaar();
    }

    private void checkWinnaar() {
        if (spelerA.isAanDeBeurt() && (speelbareVakken.get(0).getStenen() + speelbareVakken.get(1).getStenen() + speelbareVakken.get(2).getStenen() + speelbareVakken.get(3).getStenen() + speelbareVakken.get(4).getStenen() + speelbareVakken.get(5).getStenen() == 0 )) {
            beeindigSpel();
        } else if (spelerB.isAanDeBeurt() && (speelbareVakken.get(6).getStenen() + speelbareVakken.get(7).getStenen() + speelbareVakken.get(8).getStenen() + speelbareVakken.get(9).getStenen() + speelbareVakken.get(10).getStenen() + speelbareVakken.get(11).getStenen() == 0 )) {
            beeindigSpel();
        }
    }

    private void beeindigSpel() {
        leegSpeelbareVakken();
        updateStenenInVakken();
        frame.repaint();
        bepaalWinnaar();
    }

    private void leegSpeelbareVakken() {
        for (KleinVak vak: speelbareVakken) {
            vak.geefAanKalaha(vak.getStenen());
            vak.maakLeeg();
        }
    }

    private void bepaalWinnaar() {
        if (kalahas.get(0).getStenen() > kalahas.get(1).getStenen()) {
            System.out.println("Speler A is de winnaar!");
        } else if (kalahas.get(1).getStenen() > kalahas.get(0).getStenen()) {
            System.out.println("Speler B is de winnaar!");
        } else {
            System.out.println("Een gelijkspel!");
        }
    }

    private class Vakje1Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(0);

        }
    }

    private class Vakje2Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(1);
        }
    }

    private class Vakje3Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(2);
        }
    }

    private class Vakje4Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(3);
        }
    }

    private class Vakje5Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(4);
        }
    }

    private class Vakje6Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(5);
        }
    }

    private class Vakje7Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(6);
        }
    }

    private class Vakje8Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(7);
        }
    }

    private class Vakje9Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(8);
        }
    }

    private class Vakje10Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(9);
        }
    }

    private class Vakje11Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(10);
        }
    }

    private class Vakje12Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            speelVak(11);
        }
    }

}
