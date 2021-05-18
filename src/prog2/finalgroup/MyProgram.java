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
    private fourthButtonHandler fourthButton;
    private fifthButtonHandler fifthButton;
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
        fifthHandler = new thirdButtonHandler();
        fifthButton.addActionListener(tfifthHandler);

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
            String[] colNames = {"Name", "Email", "Address", "Age", "District", "Residency", "Gender"};
            JFrame firstWindowJFrame = new JFrame();

            JTable table = new JTable(objArray, colNames);
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
            JFrame thirdWindowJFrame = new JFrame();
            JPanel panel = new JPanel();
            JTextField text = new JTextField();

            List<Citizen> citizenList = MyProgramUtility.readFile(source);

            label = new JLabel("Citizens sorted by District", SwingConstants.CENTER);
            JScrollPane scroll = new JScrollPane(label, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            setTitle("Citizens");
            Container pane = getContentPane();
            panel.setLayout(new GridLayout(1, 1));
            panel.add(label);
            panel.add(scroll);

            thirdWindowJFrame.setTitle("Per District");
            thirdWindowJFrame.add(panel);
            thirdWindowJFrame.pack();
            thirdWindowJFrame.setVisible(true);
            thirdWindowJFrame.setSize(WIDTH, HEIGHT);
            thirdWindowJFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        }
    }

    private class exitButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }



//    private class firstWindow {
//        private JButton name, age, district, resident, gender, clear;
//        private JLabel sort, filter;
//
//        private nameButtonHandler nameHandler;
//        private ageButtonHandler ageHandler;
//        private districtButtonHandler districtHandler;
//        private residentButtonHandler residentHandler;
//        private genderButtonHandler genderHandler;
//        private clearButtonHandler clearHandler;
//
//        public firstWindow() throws Exception {
//
//            JPanel panel = new JPanel();
//            //JScrollPane scrollPane = new JScrollPane();
//            sort = new JLabel("Sort By: ", SwingConstants.LEFT);
//            filter = new JLabel("Filter Out:", SwingConstants.LEFT);
//
//            name = new JButton("Name");
//            nameHandler = new nameButtonHandler();
//            name.addActionListener(nameHandler);
//
//            age = new JButton("Age");
//            ageHandler = new ageButtonHandler();
//            age.addActionListener(ageHandler);
//
//            district = new JButton("District");
//            districtHandler = new districtButtonHandler();
//            district.addActionListener(districtHandler);
//
//            resident = new JButton("Resident");
//            residentHandler = new residentButtonHandler();
//            resident.addActionListener(residentHandler);
//
//            gender = new JButton("Gender");
//            genderHandler = new genderButtonHandler();
//            gender.addActionListener(genderHandler);
//
//            clear = new JButton("Clear");
//            clearHandler = new clearButtonHandler();
//            clear.addActionListener(clearHandler);
//
//
//            panel.setLayout(new GridLayout(2, 1));
//            panel.add(sort);
//            panel.add(name);
//            panel.add(age);
//            panel.add(district);
//            panel.add(filter);
//            panel.add(resident);
//            panel.add(gender);
//            panel.add(clear);
//            frame.setTitle("All Citizens");
//
//            frame.add(panel);
//            frame.pack();
//            frame.setVisible(true);
//            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        }
//
//        private class nameButtonHandler implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
//
//        private class ageButtonHandler implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
//
//        private class districtButtonHandler implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
//
//        private class residentButtonHandler implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
//
//        private class genderButtonHandler implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
//
//        private class clearButtonHandler implements ActionListener {
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        }
//    }


    public static void main(String[] args) {
        new MyProgram();
        }
    }

/**JPanel panel = new JPanel();
 JScrollPane scrollPane = new JScrollPane();

 /**
 ArrayList<Course> subjectsList = readData(source);
 // converts the ArrayList subjectList into a regular Array to input inside a JList
 JList<Course> list = new JList<Course>(subjectsList.toArray(new Course[subjectsList.size()]));


 //subjectsPanel.setLayout(new GridLayout(1, 1));
 subjectsPanel.add(text);
 subjectsPanel.add(scrollPane);
 frame.setTitle("List");

 frame.add(subjectsPanel);
 frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
 frame.pack();
 frame.setVisible(true);
 */
