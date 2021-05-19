package prog2.finalgroup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;
import java.util.List;

/**
 * MyProgram class generates the GUI for the program
 */
public class MyProgram extends JFrame {

    private Scanner kbd = new Scanner(System.in);
    private PrintWriter outputStream;
    private String source = "res/data.csv";
    private String output = "";

    private JButton firstButton, secondButton, thirdButton, fourthButton, fifthButton, sixthButton, exitButton;
    private JLabel label;
    private JFrame frame;
    private JList list1;

    private firstButtonHandler firstHandler;
    private secondButtonHandler secondHandler;
    private thirdButtonHandler thirdHandler;
    private fourthButtonHandler fourthHandler;
    private fifthButtonHandler fifthHandler;
    private sixthButtonHandler sixthHandler;
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

        thirdButton = new JButton("Sort citizens by age");
        thirdHandler = new thirdButtonHandler();
        thirdButton.addActionListener(thirdHandler);

        fourthButton = new JButton("Sort Residents");
        fourthHandler = new fourthButtonHandler();
        fourthButton.addActionListener(fourthHandler);

        fifthButton = new JButton("Search");
        fifthHandler = new fifthButtonHandler();
        fifthButton.addActionListener(fifthHandler);

        sixthButton = new JButton("Sort Non-residents");
        sixthHandler = new sixthButtonHandler();
        sixthButton.addActionListener(sixthHandler);

        exitButton = new JButton("Exit");
        exitHandler = new exitButtonHandler();
        exitButton.addActionListener(exitHandler);

        frame = new JFrame();
        setTitle("Citizens");
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(4, 1));
        pane.add(firstButton);
        pane.add(secondButton);
        pane.add(thirdButton);
        pane.add(fifthButton);
        pane.add(fourthButton);
        pane.add(sixthButton);
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
            Object[][] objArray = MyProgramUtility.toObjectArray(MyProgramUtility.sortCitizensName(MyProgramUtility.readFile(source)));
            String[] colNames = {"Name", "Email", "Address", "Age", "Residency", "District", "Gender"};
            JFrame firstWindowJFrame = new JFrame("Sorted Data");

            JTable table = new JTable(objArray, colNames) {
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

            label = new JLabel("<html>Total Number of Citizens: " + MyProgramUtility.totalPopulation(citizenList) +
                    "<br/>" +
                    "<br/>Total Number of Males: " + MyProgramUtility.getTotalMale(citizenList) +
                    "<br/>" +
                    "<br/>Total Number of Females: " + MyProgramUtility.getTotalFemale(citizenList) +
                    "<br/>" +
                    "<br/>Total Number of Residents: " + MyProgramUtility.numberOfResidents(citizenList) +
                    "<br/>" +
                    "<br/>Total Number of Non-Residents: " + MyProgramUtility.numberOfNonResidents(citizenList) +
                    "<br/>" +
                    "<br/>Number of Districts: " + MyProgramUtility.getMaxDistrict(citizenList) +
                    "<br/>" +
                    "<br/>Average age of Citizens: " + MyProgramUtility.getAverageAge(citizenList) +
                    "</html>");

            setTitle("Citizens");
            Container pane = getContentPane();
            pane.setLayout(new GridLayout(1, 1));
            panel.add(label);

            secondWindowJFrame.setTitle("Total Population");
            secondWindowJFrame.add(panel);
            secondWindowJFrame.pack();
            secondWindowJFrame.setVisible(true);
            secondWindowJFrame.setSize(WIDTH, HEIGHT);
            secondWindowJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    /**
     * Sorts the age of the citizens from youngest to oldest
     */
    private class thirdButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[][] objArray = MyProgramUtility.toAgeSorting(MyProgramUtility.sortAge(MyProgramUtility.readFile(source)));
            String[] colNames = {"Name", "Gender", "Age", "Email"};
            JFrame firstWindowJFrame = new JFrame("Sorted Data for Non residents");

            JTable table = new JTable(objArray, colNames) {
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
     * Displays the the data for residents per district
     */
    private class fourthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[][] objArray = MyProgramUtility.toResidentOrNot(MyProgramUtility.residents(MyProgramUtility.readFile(source)));
            String[] colNames = {"Residents", "Address", "District"};
            JFrame firstWindowJFrame = new JFrame("Sorted Data for residents");

            JTable table = new JTable(objArray, colNames) {
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
     * Displays a search window where the user can search for a specific resident
     */
    private class fifthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[][] objArray = MyProgramUtility.toObjectArray(MyProgramUtility.readFile(source));
            String[] colNames = {"Name", "Email", "Address", "Age", "Is Resident", "District", "Gender"};
            JTable table = new JTable(objArray, colNames) {
                public boolean editCellAt(int row, int column, java.util.EventObject e) {
                    return false;
                }
            };
            JTextField filterField = MyProgramUtility.RowFilterUtil.createRowFilter(table);
            JPanel jp = new JPanel();
            jp.add(filterField);
            frame = new JFrame("Search for Citizen");
            frame.add(jp, BorderLayout.NORTH);
            JScrollPane pane = new JScrollPane(table);
            pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            frame.add(pane, BorderLayout.CENTER);
            frame.setVisible(true);
            frame.setSize(1000, 700);

        }
    }
    /**
     * Displays the the data for non -residents per district
     */
    private class sixthButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Object[][] objArray = MyProgramUtility.toResidentOrNot(MyProgramUtility.nonResidents(MyProgramUtility.readFile(source)));
            String[] colNames = {"Non-Residents", "Address", "District"};
            JFrame firstWindowJFrame = new JFrame("Sorted Data for Non residents");

            JTable table = new JTable(objArray, colNames) {
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
     * exits the program
     */
    private class exitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * Run the program
     * @param args
     */
    public static void main(String[] args) {
        new MyProgram();
    }
}

