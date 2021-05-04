package prog2.finalgroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyProgram2 extends JFrame {
    private static Scanner filereader;
    private Scanner kbd = new Scanner(System.in);
    private PrintWriter outputStream;
    private String source = "";
    private String output = "";

    private JButton firstButton, secondButton, thirdButton, fourthButton, fifthButton, clearButton;
    private JLabel text1, text2, text3;
    private JFrame frame;


    private firstButtonHandler firstHandler;
    private secondButtonHandler secondHandler;
    private thirdButtonHandler thirdHandler;
    private fourthButtonHandler fourthHandler;
    private fifthButtonHandler fifthHandler;
    private clearButtonHandler clearHandler;

    private static int WIDTH = 400;
    private static int HEIGHT = 300;


    public MyProgram2() {
        //text1 = new JLabel("Citizen List", SwingConstants.CENTER);
        text2 = new JLabel("Sort By:", SwingConstants.LEFT);
        text3 = new JLabel("Filter Out:", SwingConstants.LEFT);

        firstButton = new JButton("Name");
        firstHandler = new firstButtonHandler();
        firstButton.addActionListener(firstHandler);

        secondButton = new JButton("Age");
        secondHandler = new secondButtonHandler();
        secondButton.addActionListener(secondHandler);

        thirdButton = new JButton("District");
        thirdHandler = new thirdButtonHandler();
        thirdButton.addActionListener(thirdHandler);

        fourthButton = new JButton("Resident");
        fourthHandler = new fourthButtonHandler();
        fourthButton.addActionListener(fourthHandler);

        fifthButton = new JButton("Gender");
        fifthHandler = new fifthButtonHandler();
        fifthButton.addActionListener(fifthHandler);

        clearButton = new JButton("Clear");
        clearHandler = new clearButtonHandler();
        clearButton.addActionListener(clearHandler);

        frame = new JFrame();
        setTitle("Citizens");
        Container pane = getContentPane();
        //pane.add(text1);
        pane.setLayout(new GridLayout(2, 1));
        pane.add(text2);
        pane.add(firstButton);
        pane.add(secondButton);
        pane.add(thirdButton);
        pane.add(text3);
        pane.add(fourthButton);
        pane.add(fifthButton);
        pane.add(clearButton);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class firstButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class secondButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class thirdButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class fourthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class fifthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    private class clearButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    public static void main(String[] args) {
        new MyProgram2();
    }
}
