public class Citizen implements Comparable {
    private String fullName, email, address;
    private int age, district;
    private boolean resident;
    private char gender;

    /**
     * default constructor for Citizen class
     */
    public Citizen(){
        fullName = "";
        email = "";
        address = "";
        age = 0;
        district = 0;
        resident = false;
        gender = 'M';
    }

    /**
     * Will set the fullName, email, address, age, district, resident and gender of a Citizen
     * @param fullName full name of citizen
     * @param email email address of citizen
     * @param address address of citizen
     * @param age age of citizen
     * @param district district of citizen
     * @param resident residential status of citizen
     * @param gender gender of citizen
     */
    public Citizen(String fullName, String email, String address, int age, int district, boolean resident, char gender){
        this.fullName  = fullName;
        this.email = email;
        this.address = address;
        this.age = age;
        this.district = district;
        this.resident = resident;
        this.gender = gender;
    }

    // start of setter methods

    /**
     * Sets given string fullName as Citizene fullname
     * @param fullName full name of citizen
     */
    public void setFullName(String fullName) { this.fullName = fullName; }

    /**
     * Sets given string email as Citizen email
     * @param email email address of citizen
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Sets given string address as Citizen address
     * @param address address of citizen
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Sets given int age as Citizen age
     * @param age age of citizen
     */
    public void setAge(int age) { this.age = age; }

    /**
     * Sets given int district as Citizen district
     * @param district district of citizen
     */
    public void setDistrict(int district) { this.district = district; }

    /**
     * Sets given boolean resident as Citizen resident
     * @param resident residential status of citizen
     */
    public void setResident(boolean resident) { this.resident = resident; }

    /**
     * Sets given char gender as Citizen gender
     * @param gender gender of citizen
     */
    public void setGender(char gender) { this.gender = gender; }

    // end of setter methods
    // start of getter methods

    /**
     * Returns Citizen fullName
     * @return fullName
     */
    public String getFullName() { return fullName; }

    /**
     * Returns Citizen email
     * @return email
     */
    public String getEmail() { return email; }

    /**
     * Returns Citizen address
     * @return address
     */
    public String getAddress() { return address; }

    /**
     * Returns Citizen age
     * @return age
     */
    public int getAge() { return age; }

    /**
     * Returns Citizen district
     * @return district
     */
    public int getDistrict() { return district; }

    /**
     * Returns residential status of citizen
     * @return resident
     */
    public boolean getResidence() { return resident; }

    /**
     * Returns gender of citizen
     * @return gender
     */
    public char getGender() { return gender; }

    //end of getter methods

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
