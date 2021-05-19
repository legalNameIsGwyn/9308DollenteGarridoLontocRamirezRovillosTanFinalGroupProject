package prog2.finalgroup;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 * The MyProgramUtility class converts the input file into a list of Citizen objects and other methods that processes the data
 */
public class MyProgramUtility {
    /**
     * This method converts data.csv into a list of citizen objects
     *
     * @param data
     * @return citizens
     */
    public static List<Citizen> readFile(String data) {
        List<Citizen> citizens = new ArrayList<>();
        try (Scanner fileReader = new Scanner(new File(data))) {
            while (fileReader.hasNextLine()) {
                String[] information = fileReader.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String fullName = information[0] + " " + information[1];
                String email = information[2];
                String address = information[3];
                int age = Integer.parseInt(information[4]);
                Boolean resident = false;
                if (information[5].compareToIgnoreCase("Resident") == 0) {
                    resident = true;
                }
                int districtNumber = Integer.parseInt(information[6]);
                char gender = 'x';
                if (information[7].compareToIgnoreCase("Male") == 0) {
                    gender = 'M';
                } else {
                    gender = 'F';
                }
                Citizen details = new Citizen(fullName, email, address, age, districtNumber, resident, gender);
                citizens.add(details);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return citizens;
    }

    /**
     * Converts arraylist into object array
     *
     * @param citizens
     * @return objArray
     */
    public static Object[][] toObjectArray(List<Citizen> citizens) {
        Object[][] objArray = new Object[citizens.size()][1];
        for (int i = 0; i < citizens.size(); i++) {
            Object[] temp = {citizens.get(i).getFullName(), citizens.get(i).getEmail(), citizens.get(i).getAddress(), citizens.get(i).getAge(), citizens.get(i).getResidence(), citizens.get(i).getDistrict(), citizens.get(i).getGender()};
            objArray[i] = temp;
        }
        return objArray;
    }
    /**
     * Converts arraylist into object array
     *
     * @param citizens
     * @return objArray
     */
    public static Object[][] toResidentOrNot(List<Citizen> citizens) {
        Object[][] objArray = new Object[citizens.size()][1];
        for (int i = 0; i < citizens.size(); i++) {
            Object[] temp = {citizens.get(i).getFullName(), citizens.get(i).getAddress(), citizens.get(i).getDistrict()};
            objArray[i] = temp;
        }
        return objArray;
    }

    /**
     * Converts arraylist into object array
     *
     * @param citizens
     * @return objArray
     */
    public static Object[][] toAgeSorting(List<Citizen> citizens) {
        Object[][] objArray = new Object[citizens.size()][1];
        for (int i = 0; i < citizens.size(); i++) {
            Object[] temp = {citizens.get(i).getFullName(), citizens.get(i).getGender(), citizens.get(i).getAge(), citizens.get(i).getEmail()};
            objArray[i] = temp;
        }
        return objArray;
    }

    /**
     * This method returns total population
     *
     * @param citizens
     * @return
     */
    public static long totalPopulation(List<Citizen> citizens) {
        return citizens
                .stream()
                .count();
    }

    /**
     * This method obtains the average age per district
     *
     * @param citizens
     * @param
     * @return
     */
    public static double getAverageAge(List<Citizen> citizens) {
        return citizens
                .stream()
                .mapToInt(Citizen::getAge)
                .average()
                .getAsDouble();
    }

    /**
     * This method obtains the maximum number of districts
     *
     * @param citizens
     * @return
     */
    public static int getMaxDistrict(List<Citizen> citizens) {
        return citizens
                .stream()
                .mapToInt(Citizen::getDistrict)
                .max()
                .orElseThrow();
    }

    /**
     * This method counts the total number of residents
     *
     * @param citizens
     * @return
     */
    public static long numberOfResidents(List<Citizen> citizens) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getResidence())
                .count();
    }

    /**
     * This method counts the toal number of non residents
     *
     * @param citizens
     * @return
     */
    public static long numberOfNonResidents(List<Citizen> citizens) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getResidence() != true)
                .count();
    }

    /**
     * This method sorts the name of the citizens. It also sorts the district
     * @param citizens
     * @return
     */
    public static List<Citizen> sortCitizensName(List<Citizen> citizens) {
        return citizens
                .stream()
                .sorted()
                .sorted((c1, c2) -> {
                    double res = c2.getDistrict() - c1.getDistrict();
                    if (res > 0) {
                        return -1;
                    } else if (res < 0) {
                        return 1;
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }
    public static List<Citizen> sortAge(List<Citizen> citizens) {
        return sortCitizensName(citizens)
                .stream()
                .sorted((c1, c2) -> {
                    double res = c2.getAge() - c1.getAge();
                    if (res < 0) {
                        return 1;
                    } else if (res > 0) {
                        return -1;
                    }
                    return 0;
                })
                .collect(Collectors.toList());
    }

    /**
     * This method returns a list of residents
     * @param citizens
     * @return
     */
    public static List<Citizen> residents(List<Citizen> citizens) {
        return sortCitizensName(citizens)
                .stream()
                .filter(citizen -> citizen.getResidence())
                .collect(Collectors.toList());
    }

    /**
     * This method returns a list of Non-residents
     * @param citizens
     * @return
     */
    public static List<Citizen> nonResidents(List<Citizen> citizens) {
        return sortCitizensName(citizens)
                .stream()
                .filter(citizen -> citizen.getResidence() != true)
                .collect(Collectors.toList());
    }

    /**
     * This method returns the total number of males
     *
     * @param citizens
     * @return
     */
    public static long getTotalMale(List<Citizen> citizens) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getGender() == 'M')
                .mapToInt(Citizen::getGender)
                .count();
    }

    /**
     * This method returns the total number of females
     *
     * @param citizens
     * @return
     */
    public static long getTotalFemale(List<Citizen> citizens) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getGender() == 'F')
                .mapToInt(Citizen::getGender)
                .count();
    }

    /**
     * This method allows the user to search for a specific person
     */
    public static class RowFilterUtil {
        public static JTextField createRowFilter(JTable table) {
            RowSorter<? extends TableModel> rs = table.getRowSorter();
            if (rs == null) {
                table.setAutoCreateRowSorter(true);
                rs = table.getRowSorter();
            }

            TableRowSorter<? extends TableModel> rowSorter =
                    (rs instanceof TableRowSorter) ? (TableRowSorter<? extends TableModel>) rs : null;

            if (rowSorter == null) {
                throw new RuntimeException("Cannot find appropriate rowSorter: " + rs);
            }

            final JTextField tf = new JTextField(15);

            tf.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    update(e);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    update(e);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    update(e);
                }

                private void update(DocumentEvent e) {
                    String text = tf.getText();
                    if (text.trim().length() == 0) {
                        rowSorter.setRowFilter(null);
                    } else {
                        rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            });
            return tf;
        }
    }
}
