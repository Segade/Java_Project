import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.io.Serializable;

/**
This is an abstract class which Teacher and Student inherit from.
 * with getters and mutators to manage the atributes
 *this class implements Serializable  to allow to save data in a file
* Author: Ivan Segade Carou
 */

  public abstract class Person implements Serializable {
    private String id;
// I declare the atributes
    private String name;
    private String surname;
    private String address;
    private String town;
    private String county;
    private GregorianCalendar dob;
    private String email;
    private String phone;
    private char  gender;
    private boolean active;

    /** constructor with arguments
     * Inicialise the atributes using the the setters
     *id
     *name
     * surname
     * address
     * town
     * county
     * Date Of Birth
     * gender
     * phone
     * email
     * active
     */




    /**
     * constructor with no arguments
     * I set the atributes with a default value with the setter
     */

    public Person() {
     setId("");
     setName("");
     setSurname("");
     setAddress("");
     setTown("");
     setCounty("");
     setPhone(phone);
     setEmail(email);
     setDob(null);
     setActive(false);
    }

    /**
    *constructor with arguments
 *name. the forename of the person
 * surname. the surname of the person
 * address. The address of the person.
 * town. The town of the person
 * county. The county of the person
 * Date Of Birth. The date of birth of the person
 * gender. the geneder of the person
 * phone. the phone of the person
 * email. the email of the person

 */


    public Person(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone, char gender) {
        setId(id);
        setName(name);
        setSurname(surname);
        setAddress(address);
        setTown(town);
        setCounty(county);
        setDob(dob);
        setEmail(email);
        setPhone(phone);
        setActive(true);
        setGender(gender);
    } // end constructor


    /**
     * method which return the ID of the person
     */

    public String getId() {
        return id;
    }

    /**
     * method which resets the id of the person
     * if there is no value, a default value is applied
     */

    public void setId(String id) {
        if (!id.equals(""))
            this.id = id;
        else
            this.id = "No specified";
    }

    /**
     * method returns the name of the person
     * @return
     */

    public String getName() {
        return name;
    }

    /**
     * method which sets the name of the person
     * if there is no value, a default value is applied
     * @param name
     */

    public void setName(String name) {
        if (!name.equals(""))
            this.name = name;
        else
            this.name= "no specified";
    }


    /**
     * method returns the surname of the person
      * @return
     */

    public String getSurname() {
        return surname;
    }

    /**
     * method which sets the surname of the person
     * if there is no value, a default value is apllied
     * @param surname
     */

    public void setSurname(String surname) {
        if (!surname.equals(""))
            this.surname = surname;
        else
            this.surname = "No specified";
    }

    /**
     * method which returns the address of the person
     * @return
     */

    public String getAddress() {
        return address;
    }

    /**
     * method which sets the address of the person
     *if there is no value, a default value is apllied
     * @param address
     */

    public void setAddress(String address) {
        if (!address.equals(""))
            this.address = address;
        else
            this.address = "No specified";
    }

    /**
     * method which returns the town of the person
     * @return
     */

    public String getTown() {
        return town;
    }

    /**
     * method which sets the town of the person
     * if there is no vlaue, a default value is applied
     * @param town
     */

    public void setTown(String town) {
        if (!town.equals(""))
            this.town = town;
        else
            this.town = "No specified";
    }

    /**
     * method which returns the county of the person
     * @return
     */

    public String getCounty() {
        return county;
    }

    /**
     * method which set the county of the person
     * if there is no value, a default value is applied
     * @param county
     */

    public void setCounty(String county) {
        if (!county.equals(""))
            this.county = county;
        else
            this.county = "No specified";
    }

    /**
     * method which returns the Date of Birth of the person
     * @return
     */

    public GregorianCalendar getDob() {
        return dob;
    }

    /**
     * method which sets the date of Birth of the person
     * @param dob
     */

    public void setDob(GregorianCalendar dob) {
        this.dob = dob;
    }

    /**
     * method which returns the email of the person
     * @return
     */

    public String getEmail() {
        return email;
    }

    /**
     * method which sets the email of the person
     * if there is no value, a default value is apllied
     * @param email
     */

    public void setEmail(String email) {
        if (!email.equals(""))
            this.email = email;
        else
            this.email = "No specified";
    }

    /**
     * method which returns the phone of the person
     * @return
     */

    /**
     * method which sets the phone of the person
     * @return
     */

    public String getPhone() {
        return phone;
    }

    /**
     * method which sets the phone of the person
     * if there is no value, a default value is appliedp
     * @param phone
     */

    public void setPhone(String phone) {
        if (!phone.equals(""))
            this.phone = phone;
        else
            this.phone = "No specified";
    }

    /**
     * method which returns if this person is active in the system or not
     * @return
     */

    public boolean isActive() {
        return active;
    }

    /**
     * method which sets the active field
     * @param active
     */

    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * method which returns the gender of the person
     * @return
     */

    public char getGender() {
        return gender;
    }

    /**
     * method which sets the gender of the person
     * @param gender
     */

    public void setGender(char gender) {

        this.gender = gender;
    }


    /**
     * method which displays the information of the person
     * @return
     */

    @Override
    public String toString() {
        String texto = "Personal details\nID: " + getId() +
                ", Name: " + getName() + ", Surname: " + getSurname() +
", Gender: ";
        if (getGender() == 'M')
            texto += "Male";
        else
            texto += "Female";

        Date dobDate = getDob().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("dd-MM-yyyy");
        texto +=  ", Date of Birth: " + formatDate.format(dobDate);


        texto += "\nContact details \nAddress: " + getAddress()+
                "\nEmail: " + getEmail() + ", Phone: " + getPhone();

        return texto;
    } // to string
} //      end class
