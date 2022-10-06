import java.util.Arrays;
import java.util.GregorianCalendar;


/** an instantiable class which defines a teacher
 * getters and settrs are used to mange the atributes
 * this class inherits from the Person class
 *
 */

public class Teacher extends Person{
    // new atributes besides from the Person class
    private String department;


    /** constructor with arguments
     *it adds a new atribute
     * department. the department which the teacher belongs to
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
     * @param department
     */

    public Teacher(String id, String name, String surname, String address, String town, String county, GregorianCalendar dob, String email, String phone,char gender, String department ) {
        super(id, name, surname, address, town, county, dob, email, phone, gender);
setDepartment(department);

    } // end constructor


    /** method which returns the department of the teacher
     *
      * @return
     */

    public String getDepartment() {
        return department;
    }

    /** method which sets the department of the teacher
     * if there is no value, a default value is applied
     *
      * @param department
     */

    public void setDepartment(String department) {
if (!department.equals(""))
        this.department = department;
else
    this.department = "No specified";
    }


    /** method which returns the information of the teacher
     * if uses the same method as the Person class, but adding the information of the department
      * @return
     */

    @Override
    public String toString() {
 String texto = super.toString() +"\nDepartment: " + getDepartment();


      return texto;
    } // end to string
} // end class
