import java.util.GregorianCalendar;

/** An instatiable class which defines the student
 * getters and setters are used to manage the atributes
 * this class inherits farou
 */
public class Student extends Person{


    /** constructor with arguments
     * i uses the same atributes as the Person class, so it uses the same getters and setters
     * @param id
     * @param name
     * @param surname
     * @param address
     * @param town
     * @param county
     * @param dob
     * @param email
     * @param phone
     * @param gender
     */

    public Student(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone, char gender) {
        super(id, name, surname, address, town, county, dob, email, phone, gender);
    }// end constructor

} // end class
