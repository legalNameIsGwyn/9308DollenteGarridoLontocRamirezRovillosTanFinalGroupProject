package prog2.finalgroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.List;

public class MyProgram extends JFrame {

    private Scanner kbd = new Scanner(System.in);
    private PrintWriter outputStream;
    private String source = "res/data.csv";
    private String output = "";

    private JButton firstButton, secondButton, thirdButton, fourthButton, fifthButton, exitButton;
    private JLabel label;
    private JFrame frame;
    private JList list1;

    private firstButtonHandler firstHandler;
    private secondButtonHandler secondHandler;
    private thirdButtonHandler thirdHandler;
    private fourthButtonHandler fourthHandler;
    private fifthButtonHandler fifthHandler;
    private exitButtonHandler exitHandler;

    private static int WIDTH = 400;
    private static int HEIGHT = 400;

    /**
     * Main menu of the program
     */
    public MyProgram() {

        firstButton = new JButton("Population Table");
        firstHandler = new firstButtonHandler();
        firstButton.addActionListener(firstHandler);

        secondButton = new JButton("Statistical Data");
        secondHandler = new secondButtonHandler();
        secondButton.addActionListener(secondHandler);

        thirdButton = new JButton("Show Citizens by district");
        thirdHandler = new thirdButtonHandler();
        thirdButton.addActionListener(thirdHandler);

        fourthButton = new JButton("fourth");
        fourthHandler = new fourthButtonHandler();
        fourthButton.addActionListener(fourthHandler);

        fifthButton = new JButton("fifth");
        fifthHandler = new fifthButtonHandler();
        fifthButton.addActionListener(fifthHandler);

        exitButton = new JButton("Exit");
        exitHandler = new exitButtonHandler();
        exitButton.addActionListener(exitHandler);

        frame = new JFrame();
        setTitle("Citizens");
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(6, 1));
        pane.add(firstButton);
        pane.add(secondButton);
        pane.add(thirdButton);
        pane.add(fourthButton);
        pane.add(fifthButton);
        pane.add(exitButton);
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * Creates a window representing the data in a table
     */
    private class firstButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[][] objArray = MyProgramUtility.toObjectArray(MyProgramUtility.readFile(source));
            String[] colNames = {"Name", "Email", "Address", "Age", "Is Resident", "District", "Gender"};
            JFrame firstWindowJFrame = new JFrame();

            JTable table = new JTable(objArray, colNames){
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            JScrollPane scrollPane = new JScrollPane(table);

            firstWindowJFrame.add(scrollPane);
            firstWindowJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            firstWindowJFrame.pack();
            firstWindowJFrame.setVisible(true);
        }
    }

    /**
     * Shows basic statistical information of the entire population
     */
    private class secondButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame secondWindowJFrame = new JFrame();
            JPanel panel = new JPanel();

            List<Citizen> citizenList = MyProgramUtility.readFile(source);

            label = new JLabel("<html>Total Number of Citizens: " + MyProgramUtility.totalPopulation(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Males: " +MyProgramUtility.getTotalMale(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Females: " +MyProgramUtility.getTotalFemale(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Residents: " +MyProgramUtility.numberOfResidents(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Non-Residents: "+MyProgramUtility.numberOfNonResidents(citizenList)+
                    "<br/>"+
                    "<br/>Number of Districts: "+ MyProgramUtility.getMaxDistrict(citizenList) +
                    "<br/>"+
                    "<br/>Average age of Citizens: "+ MyProgramUtility.getAverageAge(citizenList)+
                    "</html>");

            setTitle("Citizens");
            Container pane = getContentPane();
            pane.setLayout(new GridLayout(1, 1));
            panel.add(label);

            secondWindowJFrame.setTitle("Total Population");
            secondWindowJFrame.add(panel);
            secondWindowJFrame.pack();
            secondWindowJFrame.setVisible(true);
            secondWindowJFrame.setSize(WIDTH,HEIGHT);
            secondWindowJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    /**
     * Shows residents categorized by district
     */
    private class thirdButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            List<Citizen> citizens = MyProgramUtility.readFile(source);
            JFrame thirdWindowFrame = new JFrame("Citizens Sorted By District");
            thirdWindowFrame.setSize(1000, 450);
            label = new JLabel();
            label.setText("<html>District 1" + MyProgramUtility.sortNamesInDistrict(citizens, 1) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 2" + MyProgramUtility.sortNamesInDistrict(citizens, 2) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 3" + MyProgramUtility.sortNamesInDistrict(citizens, 3) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 4" + MyProgramUtility.sortNamesInDistrict(citizens, 4) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 5" + MyProgramUtility.sortNamesInDistrict(citizens, 5) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 6" + MyProgramUtility.sortNamesInDistrict(citizens, 6) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 7" + MyProgramUtility.sortNamesInDistrict(citizens, 7) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 8" + MyProgramUtility.sortNamesInDistrict(citizens, 8) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 9" + MyProgramUtility.sortNamesInDistrict(citizens, 9) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 10" + MyProgramUtility.sortNamesInDistrict(citizens, 10) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 11" + MyProgramUtility.sortNamesInDistrict(citizens, 11) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 12" + MyProgramUtility.sortNamesInDistrict(citizens, 12) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 13" + MyProgramUtility.sortNamesInDistrict(citizens, 13) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 14" + MyProgramUtility.sortNamesInDistrict(citizens, 14) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 15" + MyProgramUtility.sortNamesInDistrict(citizens, 15) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 16" + MyProgramUtility.sortNamesInDistrict(citizens, 16) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 17" + MyProgramUtility.sortNamesInDistrict(citizens, 17) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 18" + MyProgramUtility.sortNamesInDistrict(citizens, 18) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 19" + MyProgramUtility.sortNamesInDistrict(citizens, 19) +
                    "<br/>" +
                    "<br/>" +
                    "<br/>District 20" + MyProgramUtility.sortNamesInDistrict(citizens, 20) +
                    "</html>");
            label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            JScrollPane scroll = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            thirdWindowFrame.add(scroll);
            thirdWindowFrame.setVisible(true);

        }
    }

    private class fourthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame secondWindowJFrame = new JFrame();
            JPanel panel = new JPanel();

            List<Citizen> citizenList = MyProgramUtility.readFile(source);

            label = new JLabel("<html>Total Number of Citizens: " + MyProgramUtility.totalPopulation(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Males: " +MyProgramUtility.getTotalMale(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Females: " +MyProgramUtility.getTotalFemale(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Residents: " +MyProgramUtility.numberOfResidents(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Non-Residents: "+MyProgramUtility.numberOfNonResidents(citizenList)+
                    "<br/>"+
                    "<br/>Number of Districts: "+ MyProgramUtility.getMaxDistrict(citizenList) +
                    "<br/>"+
                    "<br/>Average age of Citizens: "+ MyProgramUtility.getAverageAge(citizenList)+
                    "</html>");

            setTitle("Citizens");
            Container pane = getContentPane();
            pane.setLayout(new GridLayout(1, 1));
            panel.add(label);

            secondWindowJFrame.setTitle("Total Population");
            secondWindowJFrame.add(panel);
            secondWindowJFrame.pack();
            secondWindowJFrame.setVisible(true);
            secondWindowJFrame.setSize(WIDTH,HEIGHT);
            secondWindowJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class fifthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFrame secondWindowJFrame = new JFrame();
            JPanel panel = new JPanel();

            List<Citizen> citizenList = MyProgramUtility.readFile(source);

            label = new JLabel("<html>Total Number of Citizens: " + MyProgramUtility.totalPopulation(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Males: " +MyProgramUtility.getTotalMale(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Females: " +MyProgramUtility.getTotalFemale(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Residents: " +MyProgramUtility.numberOfResidents(citizenList)+
                    "<br/>"+
                    "<br/>Total Number of Non-Residents: "+MyProgramUtility.numberOfNonResidents(citizenList)+
                    "<br/>"+
                    "<br/>Number of Districts: "+ MyProgramUtility.getMaxDistrict(citizenList) +
                    "<br/>"+
                    "<br/>Average age of Citizens: "+ MyProgramUtility.getAverageAge(citizenList)+
                    "</html>");

            setTitle("Citizens");
            Container pane = getContentPane();
            pane.setLayout(new GridLayout(1, 1));
            panel.add(label);

            secondWindowJFrame.setTitle("Total Population");
            secondWindowJFrame.add(panel);
            secondWindowJFrame.pack();
            secondWindowJFrame.setVisible(true);
            secondWindowJFrame.setSize(WIDTH,HEIGHT);
            secondWindowJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class exitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }


    public static void main(String[] args) { new MyProgram(); }
    }
