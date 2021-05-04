package prog2.finalgroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class MyProgram extends JFrame {
    private Scanner kbd = new Scanner(System.in);
    private PrintWriter outputStream;
    private String source = "";
    private String output = "";

    private JButton firstButton, secondButton, thirdButton, exitButton;
    private JLabel text;
    private JFrame frame;
    private JList list1;

    private firstButtonHandler firstHandler;
    private secondButtonHandler secondHandler;
    private thirdButtonHandler thirdHandler;
    private exitButtonHandler exitHandler;

    private static int WIDTH = 600;
    private static int HEIGHT = 600;


    public MyProgram() {

        text = new JLabel("<html>Total No. of Citizens: " +
                "<br/>Number of Males: " +
                "<br/>Number of Females: " +
                "<br/>Number of Residents: " +
                "<br/>Number of Non-Residents: </html>");

        firstButton = new JButton("All Citizens");
        firstHandler = new firstButtonHandler();
        firstButton.addActionListener(firstHandler);

        secondButton = new JButton("add citizen");
        secondHandler = new secondButtonHandler();
        secondButton.addActionListener(secondHandler);

        thirdButton = new JButton("remove citizen");
        thirdHandler = new thirdButtonHandler();
        thirdButton.addActionListener(thirdHandler);

        exitButton = new JButton("Exit");
        exitHandler = new exitButtonHandler();
        exitButton.addActionListener(exitHandler);

        frame = new JFrame();
        setTitle("Citizens");
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(5, 1));
        pane.add(text);
        pane.add(firstButton);
        pane.add(secondButton);
        pane.add(thirdButton);
        pane.add(exitButton);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private class firstButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                new firstWindow();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
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

    private class exitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    private class firstWindow {
        public firstWindow() throws Exception {
            JPanel subjectsPanel = new JPanel();
            JScrollPane scrollPane = new JScrollPane();
            text = new JLabel("List of All Citizens", SwingConstants.CENTER);

            /**
             ArrayList<Course> subjectsList = readData(source);
             // converts the ArrayList subjectList into a regular Array to input inside a JList
            JList<Course> list = new JList<Course>(subjectsList.toArray(new Course[subjectsList.size()]));
            */

            //subjectsPanel.setLayout(new GridLayout(1, 1));
            subjectsPanel.add(text);
            subjectsPanel.add(scrollPane);
            frame.setTitle("List");

            frame.add(subjectsPanel);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }


    public static void main(String[] args) {
        new MyProgram();
        }
    }

