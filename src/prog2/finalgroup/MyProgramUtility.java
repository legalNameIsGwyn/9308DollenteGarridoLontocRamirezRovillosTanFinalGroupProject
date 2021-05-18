package prog2.finalgroup;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

/**
 * The MyProgramUtility class converts the input file into a list of Citizen objects and other methods that processes the data
 */
public class MyProgramUtility {
    /**
     * This method converts data.csv into a list of citizen objects
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
                char gender;
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
     * @param citizens
     * @return objArray
     */
    public static Object[][] toObjectArray(List<Citizen> citizens){
        Object[][] objArray = new Object[citizens.size()][1];
        for (int i = 0; i < citizens.size(); i++){
            Object[] temp = {citizens.get(i).getFullName(), citizens.get(i).getEmail(), citizens.get(i).getAddress(), citizens.get(i).getAge(), citizens.get(i).getResidence(), citizens.get(i).getDistrict(), citizens.get(i).getGender()};
            objArray[i] = temp;
        }
        return objArray;
    }

    /**
     * This method returns the average age of all citizens
     * @param citizens
     * @return
     */
    public static double getAverageAge(List<Citizen> citizens) { //,int district) {
        return citizens
                .stream()
                .mapToInt(Citizen::getAge)
                .average()
                .getAsDouble();
    }

    /**
     * This method obtains the average age per district
     * @param citizens
     * @param district
     * @return
     */
    public static double getAverageAgePerDistrict(List<Citizen> citizens, int district) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district)
                .mapToInt(Citizen::getAge)
                .average()
                .getAsDouble();
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
     * This method returns total population
     * @param citizens
     * @return
     */
    public static long totalPopulation(List<Citizen> citizens){
        return citizens
                .stream()
                .count();
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
     * This method counts the total number of residents
     * @param citizens
     * @return
     */
    public static long numberOfResidents(List<Citizen> citizens){
        return citizens
                .stream()
                .filter(citizen -> citizen.getResidence())
                .count();
    }

    /**
     * This method counts the total number of non-residents
     * @param citizens
     * @return
     */
    public static long numberOfNonResidents(List<Citizen> citizens){
        return citizens
                .stream()
                .filter(citizen -> citizen.getResidence() != true)
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
     * This methods returns all the names of citizens
     * @param citizens
     * @return
     */
    public static List<String> getAllNames(List<Citizen> citizens) {
        return citizens
                .stream()
                .map(Citizen::getFullName)
                .collect(Collectors.toList());
    }

    /**
     * This method returns all of the ages of citizens
     * @param citizens
     * @return
     */
    public static List<Integer> getAllAge(List<Citizen> citizens) {
        return citizens
                .stream()
                .map(Citizen::getAge)
                .collect(Collectors.toList());
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
     * This method returns a list of emails of citizens living in a district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> getEmailPerDistrict(List<Citizen> citizens, int district) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district)
                .map(Citizen::getEmail)
                .collect(Collectors.toList());
    }

    /**
     * This method returns a list of emails of residents in a district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> getResidentEmail(List<Citizen> citizens, int district) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district && citizen.getResidence())
                .map(Citizen::getEmail)
                .collect(Collectors.toList());
    }

    /**
     * This method returns a list of emails of non-residents in a district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> getNonResidentEmail(List<Citizen> citizens, int district) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district && citizen.getResidence() != true)
                .map(Citizen::getEmail)
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

    /**
     * This method returns the list of addresses of the residents in the district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> getAddressOfResident (List<Citizen> citizens, int district){
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district && citizen.getResidence())
                .map(Citizen::getAddress)
                .collect(Collectors.toList());
    }

    /**
     * This method returns the list of addresses of non-residents in the district
     * @param citizens
     * @param district
     * @return
     */
    public static List<String> getAddressOfNonResident (List<Citizen> citizens, int district){
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district && citizen.getResidence() != true)
                .map(Citizen::getAddress)
                .collect(Collectors.toList());
    }
    /**
     * This method returns the number of males per district
     * @param citizens
     * @param district
     * @return
     */
    public static long getMalePerDistrict(List<Citizen> citizens, int district) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district)
                .filter(citizen -> citizen.getGender() == 'M')
                .mapToInt(Citizen::getGender)
                .count();
    }

    /**
     * This method returns the number of Females per district
     * @param citizens
     * @param district
     * @return
     */
    public static long getFemalePerDistrict(List<Citizen> citizens, int district) {
        return citizens
                .stream()
                .filter(citizen -> citizen.getDistrict() == district)
                .filter(citizen -> citizen.getGender() == 'F')
                .mapToInt(Citizen::getGender)
                .count();
    }

    /**
     * This method returns the total number of females
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
     * This method returns the total number of males
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
}
