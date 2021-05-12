package prog2.finalgroup;

import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class MyProgramUtility {
    public static void main(String[] args) {
        List<Citizen> citizens = readFile("res\\data.csv");
        /**
         * Code below to be deleted. For testing purposes only.
         */
        int maxDistrict = getMaxDistrict(citizens);
        for (int x = 1; x <= maxDistrict; x++){
            printAgePerDistrict(citizens, x);
        }
        for (int x = 1; x <= maxDistrict; x++) {
            printNamesPerDistrict(citizens, x);
        }

    }
     private static List<Citizen> readFile(String data){
        List<Citizen> citizens = new ArrayList<>();
        try(Scanner fileReader = new Scanner(new File(data))){
            while (fileReader.hasNextLine()){
                String[] information = fileReader.nextLine().split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                String fullName = information[1] + ", " + information[0];
                String email = information[2];
                String address = information[3];
                int age = Integer.parseInt(information[4]);
                Boolean resident = false;
                if (information[5].compareToIgnoreCase("Resident") == 0){
                    resident = true;
                }
                int districtNumber = Integer.parseInt(information[6]);
                char gender = 'x';
                if (information[7].compareToIgnoreCase("Male") == 0){
                    gender = 'M';
                } else {
                    gender = 'F';
                }
                Citizen details = new Citizen(fullName,email,address,age,districtNumber,resident,gender);
                citizens.add(details);
            }
        } catch( Exception exception){
            exception.printStackTrace();
        }
        return citizens;
     }

     public static double getAverageAge(List<Citizen> citizens, int district){
        return citizens.stream().filter(citizen -> citizen.getDistrict() == district).mapToInt(Citizen::getAge).average().getAsDouble();
     }

     public static int getMaxDistrict(List<Citizen> citizens){
        return citizens.stream().mapToInt(Citizen::getDistrict).max().orElseThrow();
     }
     public static List<String> getNamePerDistrict(List<Citizen> citizens, int district){
        List<String> citizenNames = citizens.stream().filter(citizen -> citizen.getDistrict() == district).map(Citizen::getFullName).collect(Collectors.toList());
        return citizenNames;
     }
     public static List<String> sortNamesPerDistrict(List<Citizen> citizens, int district){
        return getNamePerDistrict(citizens, district).stream().sorted().collect(Collectors.toList());
     }

    /**
     * Methods below are for testing purposes only. To be deleted.
     * Not included in UML diagram
     */
     public static void printAgePerDistrict(List<Citizen> citizens, int districtNo){
         System.out.printf("%-5s%-2d%-4s%.2f%n","District ",districtNo, ":", getAverageAge(citizens,districtNo));
     }
     public static void printNamesPerDistrict(List<Citizen> citizens, int district){
         System.out.println("District: "+district);
         for (String c :sortNamesPerDistrict(citizens, district)){
             System.out.println(c);
         }
         System.out.println();
     }
}
