import java.io.Serializable;

/** an instantiable class which defines a course
 * the getters an setters manage the atributes to avoid to corrupt them
 * this class implements Serializable  to allow to save the data in a file
 * Author: Ivan Segade Carou
  */


public class Course implements Serializable {
// I declare the atributes
    private String id;
private String name;
private double price;
private double pay;
    private Teacher teacher;
    private Student student[];

    /** Initialise the atributes using the setters
     * courseID. the ID of the course
     * name. the name of the course
     * price. the price which students will pay for the class
     * pay. the revenue which the teacher will be got pay
     * @param id
     * @param name
     * @param price
     * @param pay
     * @param teacher
     */


    // constructor
    public Course(String id, String name, double price, double pay, Teacher teacher) {
        setId(id);
setName(name);
        setPrice(price);
setPay(pay);
        this.teacher = teacher;
    } // end constructor

    /**
     * method which returns the ID of the course
     *
     * @return
     */

    public String getId() {
        return id;
    }

    /**
     * method which set the ID of the course
     * if there is no value, a default value is applied
     * @param id
     */

    public void setId(String id) {
        if (!id.equals(""))
        this.id = id;
        else
            this.id = "No specified";
    }

    /** method which returns the name of the course
     *
     * @return
     */

    public String getName() {
        return name;
    }

    /** method which sets the name of the course
     *if there is no value, a default value is applied
     *
     * @param name
     */

    public void setName(String name) {
if (!name.equals(""))
        this.name = name;
else
    this.name = "No specified";
    }

    /** method which returns the price of the class
     *
     * @return
     */

    public double getPrice() {
        return price;
    }

    /** method which sets the price of the course
     * if there is no value, a default value is applied
     * @param price
     */

    public void setPrice(double price) {
        if (price<0)
            this.price = 0;
        else
        this.price = price;
    }

    /** method which returns the pay of the course
     *
     * @return
     */

    public double getPay() {
        return pay;
    }

    /** method which sets the pay of the course
     *if there is no value, a default value is applied
     * @param pay
     */
    public void setPay(double pay) {
if (pay<0)
    this.pay = 0;
else
        this.pay = pay;
    }

    /** method which returns the teacher of the course
     *
     * @return
     */

    public Teacher getTeacher() {
        return teacher;
    }

    /** method which sets the teacher of the course
     *
     * @param teacher
     */

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /** method which returns the student of the course
     *
     * @return
     */


    public Student[] getStudent() {
        return student ;
    }

    /** method which sets the student of the course
     *
     * @param student
     */

    public void setStudent(Student student[]) {
        this.student = student;
    }

    /** method which displays the information of the course
     *
     * @return
     */

    @Override
    public String toString() {
 String texto = "Course Details \nID: " + getId() + ", Name: " + getName() +
         "\nPrice: " + getPrice() + ", Payment: " + getPay()+
          "\nTeacher: " + getTeacher() + "\nStudents details:\n";


 if (student != null){
     for (int x=0; x< student.length;x++)
         if (student[x] != null)
 texto += student[x].getId() + " " +  student[x].getName() + ", " + student[x].getSurname() +"\n";
 } else
     texto += "\nThere is no student";

 texto += "\n\nThe revenue for this course is:" + String.format("%.2f", getRevenue());
 return texto;
    } // end to string

public double getRevenue() {
    int count = 0;
    double revenue = 0;

    if (student != null){

        for (int x = 0; x < student.length; x++)
            if (student[x] != null)
                count++;

            revenue = getPrice() * count;
    }else
    revenue = 0;


return revenue;
} // end get revenue

} // end class
