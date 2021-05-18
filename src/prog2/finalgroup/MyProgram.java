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

    private JButton firstButton, secondButton, thirdButton, exitButton;
    private JLabel text;
    private JFrame frame;
    private JList list1;

    private firstButtonHandler firstHandler;
    private secondButtonHandler secondHandler;
    private thirdButtonHandler thirdHandler;
    private exitButtonHandler exitHandler;

    private static int WIDTH = 400;
    private static int HEIGHT = 400;

    public MyProgram() {
        System.out.println(MyProgramUtility.getMaxDistrict(MyProgramUtility.readFile(source)));

        firstButton = new JButton("All Citizens");
        firstHandler = new firstButtonHandler();
        firstButton.addActionListener(firstHandler);

        secondButton = new JButton("Show totals");
        secondHandler = new secondButtonHandler();
        secondButton.addActionListener(secondHandler);

        thirdButton = new JButton("");
        thirdHandler = new thirdButtonHandler();
        thirdButton.addActionListener(thirdHandler);

        exitButton = new JButton("Exit");
        exitHandler = new exitButtonHandler();
        exitButton.addActionListener(exitHandler);

        frame = new JFrame();
        setTitle("Citizens");
        Container pane = getContentPane();
        pane.setLayout(new GridLayout(5, 1));
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
            JFrame firstWindowJFrame = new JFrame();

            firstWindowJFrame.setSize(WIDTH,HEIGHT);

        }
    }

    private class secondButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JLabel label;
            JFrame secondWindowJFrame = new JFrame();
            JPanel panel = new JPanel();

            List<Citizen> citizenList = MyProgramUtility.readFile(source);

            label = new JLabel("<html>Total Number of Citizens: " + MyProgramUtility.totalPopulation(citizenList)+
                    "<br/>Total Number of Males: " +MyProgramUtility.getTotalMale(citizenList)+
                    "<br/>Total Number of Females: " +MyProgramUtility.getTotalFemale(citizenList)+
                    "<br/>Total Number of Residents: " +MyProgramUtility.numberOfResidents(citizenList)+
                    "<br/>Total Number of Non-Residents: "+MyProgramUtility.numberOfNonResidents(citizenList)+ "</html>");

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

    private class thirdButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
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
