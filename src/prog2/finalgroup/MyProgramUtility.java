package prog2.finalgroup;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class MyProgramUtility {
    public static void main(String[] args) {
        List<Citizen> citizens = readFile("res\\data.csv");

    }

    /**
     * This method converts data.csv into a list of citizen objects
     * @param data
     * @return citizens
     */
    private static List<Citizen> readFile(String data) {
        List<Citizen> citizens = new ArrayList<>();
        try (Scanner fileReader = new Scanner(new File(data))) {
            while (fileReader.hasNextLine()) {
                String[] information = fileReader.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String fullName = information[1] + ", " + information[0];
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
     * This method obtains the average age per district
     * @param citizens
     * @param district
     * @return
     */
    public static double getAverageAge(List<Citizen> citizens, int district) {
        return citizens.stream().filter(citizen -> citizen.getDistrict() == district).mapToInt(Citizen::getAge).average().getAsDouble();
    }

    /**
     * This method obtains the maximum number of districts
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
     * This method counts the numbers of residents in a district
     * @param citizens
     * @param district
     * @return
     */
    public static long numberOfResidentsPerDistrict(List<Citizen> citizens, int district){
        return citizens
            .stream()
            .filter(citizen -> citizen.getResidence() && citizen.getDistrict() == district)
            .count();
    }

    /**
     * This method counts the number of Non-residents in a district
     * @param citizens
     * @param district
     * @return
     */
    public static long numberOfNonResidentsPerDistrict(List<Citizen> citizens, int district){
        return citizens
            .stream()
            .filter(citizen -> citizen.getResidence() != true && citizen.getDistrict() == district)
            .count();
    }

    /**
     * This method returns a list of citizens per district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> getNamePerDistrict(List<Citizen> citizens, int district) {
        return citizens
            .stream()
            .filter(citizen -> citizen.getDistrict() == district)
            .map(Citizen::getFullName)
            .collect(Collectors.toList());
    }

    /**
     * This method sorts the name of residents per district 
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> sortResidentNamesPerDistrict(List<Citizen> citizens, int district) {
        return residentsPerDistrict(citizens, district)
            .stream()
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * This method sorts the name of non-residents in a district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> sortNonResidentNamesPerDistrict(List<Citizen> citizens, int district) {
        return nonResidentsPerDistrict(citizens, district)
            .stream()
            .sorted()
            .collect(Collectors.toList());
    }

    /**
     * This method returns a list of names of residents per district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> residentsPerDistrict(List<Citizen> citizens, int district) {
        return citizens
            .stream()
            .filter(citizen -> citizen.getDistrict() == district && citizen.getResidence())
            .map(Citizen::getFullName)
            .collect(Collectors.toList());
    }

    /**
     * This method returns a list of names of non-residents per district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> nonResidentsPerDistrict(List<Citizen> citizens, int district) {
        return citizens
            .stream()
            .filter(citizen -> citizen.getDistrict() == district && citizen.getResidence() != true)
            .map(Citizen::getFullName)
            .collect(Collectors.toList());
    }
}
