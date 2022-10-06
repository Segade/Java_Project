import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

/** an instantiable class which assigns students to a course
 * Author: Ivan Segade Carou
 */

public class AssignStudent extends JFrame implements ActionListener {
    // I declare all the GUI componenets
    JFrame assignStudentFrame = new JFrame("Assign student");
    JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
    JPanel buttonsPanel = new JPanel(new FlowLayout());

    BorderLayout layout = new BorderLayout();

    JLabel courseLabel = new JLabel("Course ID:");
    JLabel studentLabel = new JLabel("Student ID:");
    JLabel blank = new JLabel("   ");

    JTextField courseTextField = new JTextField(10);
    JTextField studentTextField = new JTextField(10);

    JButton assignButton = new JButton("Assign");
    JButton unassignButton = new JButton("Unassign");
    JButton cancelButton = new JButton("Cancel");


// I declare the ArrayLists where I will save the data from the files
    ArrayList<Course> allCourses = new ArrayList<>();
    ArrayList<Student> allStudents = new ArrayList<>();

    Course c= null;
    Student s = null;
    int size = 0;
    // I keep the position where the course and the student are in the Arraylists
    int positionStudent;
    int positionCourse ;

    /**  I declare all GUI  components
     * frame
     * ppanels
     * textfields
     * labels
     * buttons
     */


    /** constructor which will ask the user the required data
     * course ID. the ID of the course
     * student ID. the ID of the course
      */


    public AssignStudent() {
// I set up the main frame
        assignStudentFrame.setSize(500, 500);
        assignStudentFrame.setLayout(layout);

        // I add the listener to the buttons
        assignButton.addActionListener(this);
        unassignButton.addActionListener(this);
cancelButton.addActionListener(this);

// I set up the panel where the buttons will be
buttonsPanel.add(assignButton);
buttonsPanel.add(unassignButton);

// I set up the main form
        formPanel.add(courseLabel);
        formPanel.add(courseTextField);
        formPanel.add(studentLabel);
        formPanel.add(studentTextField);
        formPanel.add(cancelButton);
        formPanel.add(buttonsPanel);

        // I add all the forms to the main frame
        assignStudentFrame.add(formPanel);
assignStudentFrame.setVisible(true);

// I call the methods which read the Course and Student files
open();
openStudents();
    } // end constructor


    /** I declare the listener
     *
     * @param e
     */

    public void actionPerformed(ActionEvent e) {
// I deal with the option picked by the user
        String option = e.getActionCommand();
// It depends on the picked option, one case will be run or another
switch (option){
    case "Assign":

        if (searchCourse()){
if (searchStudent()){

    if (assignStudent() ) {

        saveCourse();
        JOptionPane.showMessageDialog(null, allCourses.get(positionCourse).toString(), "Assign success", JOptionPane.PLAIN_MESSAGE );
    } // end if assign
            } else // if search student
            JOptionPane.showMessageDialog(null, "Student not found", "Student not found", JOptionPane.PLAIN_MESSAGE);
    } else // if search course
    JOptionPane.showMessageDialog(null, "Course not found", "Course not found", JOptionPane.PLAIN_MESSAGE);

     emptyForm();
break;

    case "Unassign":
 if (searchCourse()) {

     if (searchStudent()) {

         if (unassignStudent()) {
             saveCourse();
             JOptionPane.showMessageDialog(null, allCourses.get(positionCourse).toString(), "Unassign success", JOptionPane.PLAIN_MESSAGE);
         }
     } else // if student
         JOptionPane.showMessageDialog(null, "Student not found", "Student not found", JOptionPane.PLAIN_MESSAGE);
 } else // if course
     JOptionPane.showMessageDialog(null, "Course not found", "Course not found", JOptionPane.PLAIN_MESSAGE);

 emptyForm();
 break;

case "Cancel" : assignStudentFrame.dispose();
} // end switch
    } // end action performed


    /** method which reads the Course file and retrieves data from it
     *
      */

    public void open() {
        try {

            File file = new File("courses.data");

            if(file.exists()) {
// if the file already exists, I get the data on it

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allCourses = (ArrayList<Course>) is.readObject();
                is.close();


           }
            else{
                // if the file does not exist, I create it
                file.createNewFile();

            }
        }
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialised not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    } // end open


    /** method which reads the Student file and retrieves data from it
     *
     */

    public void openStudents() {
        try {


            File file = new File("students.data");

            if(file.exists()) { //this if-else added by JB
// if the file already exists I get the data on it

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allStudents = (ArrayList<Student>) is.readObject();
                is.close();

            }
            else{
                // if the file does not exist I create it
                file.createNewFile();

            }
        }
        catch(ClassNotFoundException cce) {
            JOptionPane.showMessageDialog(null,"Class of object deserialised not a match for anything used in this application","Error",JOptionPane.ERROR_MESSAGE);
            cce.printStackTrace();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null,"File not found","Error",JOptionPane.ERROR_MESSAGE);
            fnfe.printStackTrace();
        }
        catch (IOException ioe) {
            JOptionPane.showMessageDialog(null,"Problem reading from the file","Error",JOptionPane.ERROR_MESSAGE);
            ioe.printStackTrace();
        }
    } // end open student

    /** method which searches the required course on the ArrayLIst
     *
      * @return
     */

    private  boolean searchCourse (){
        boolean pass = false;
        String idCourse = courseTextField.getText();
        int x= 0;
// I go through the ArrayList if I find the id
        while (x<allCourses.size()){
            c = allCourses.get(x);

            if (c.getId().equalsIgnoreCase(idCourse)) {
                pass = true;
                positionCourse = x;
                x = allCourses.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search course

    /** method which searches the student in the ArrayList
     *
     * @return
     */

    private  boolean searchStudent (){
        boolean pass = false;
        String idStudent = studentTextField.getText();
 int x=0;

 // I go through the ArrayList if I find the id
        while (x<allStudents.size()){
            s = allStudents.get(x);

            if (s.getId().equalsIgnoreCase(idStudent)) {
                pass = true;
                positionStudent = x;
                x = allStudents.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search student


    /** method which assigns the student to a course, saving it in the ArrayList
     *
     * @return
     */

    public boolean assignStudent() {
        boolean pass = false;


        if (c.getStudent() == null) {
// if the student array on the course is empty
            Student student[] = new Student[30];
            student[0] = s;
            c.setStudent(student);
            pass = true;
        } else {
            int x = 0;

            while (x < c.getStudent().length && !pass) {
                if (c.getStudent()[x] == null){
                    // I assign the student to the first free position on the array
                    Student student[] = c.getStudent();
student[x] = s;

allCourses.get(positionCourse).setStudent(student);
x = c.getStudent().length;
pass = true;

                } else if (c.getStudent()[x].getId().equalsIgnoreCase(studentTextField.getText())   )
                {
                    // this student is already assigned to this course
                    x = c.getStudent().length + 2;
                    JOptionPane.showMessageDialog(null, "This student already is assigned to this course", "already assigned", JOptionPane.ERROR_MESSAGE);
                } // if c student id is = student text filed

x++;
            } // end while
if (x == c.getStudent().length)
    JOptionPane.showMessageDialog(null, "This course is full", "Full", JOptionPane.ERROR_MESSAGE);
        } // if student array is null

    return pass;
    } // end method assign student


    /** method which unassigns a student from a course, removing it in the ArrayList
     *
      * @return
     */


    public boolean unassignStudent() {
        boolean pass = false;

        if (c.getStudent() == null) {
            JOptionPane.showMessageDialog(null, "This course is empty", "Course empty", JOptionPane.PLAIN_MESSAGE);

        } else {
            int x = 0;
            while (x < c.getStudent().length && !pass) {
if (c.getStudent()[x] != null )
                if (c.getStudent()[x].getId().equalsIgnoreCase(studentTextField.getText())   )
                {
                    Student student[] = c.getStudent();
                    student[x] = null;
                    allCourses.get(positionCourse).setStudent(student);
pass = true;
                    x = c.getStudent().length + 2;

                } // if c student id is = student text fieled
                 x++;
            } // end while

              if (x == c.getStudent().length)
                JOptionPane.showMessageDialog(null, "This student is not in this course", "Student not assigned", JOptionPane.ERROR_MESSAGE);
        } // if student array is null

        return pass;
    } // end method unassign student


    /** method which saves the data on the Course file
     *
      */

    private void saveCourse()
    {

        File outFile  = new File("courses.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeObject(allCourses);

            outStream.close();
        }
        catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace();
            JOptionPane.showMessageDialog(null, "File could not be found!",
                    "Problem Finding File!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe){
            System.out.println(ioe.getStackTrace());
            JOptionPane.showMessageDialog(null,"File could not be written!",
                    "Problem Writing to File!",JOptionPane.ERROR_MESSAGE);
        }

    } // end save course


    /**  method which resets the form
     *
     */
    private void emptyForm(){
    courseTextField.setText("");
    studentTextField.setText("");
    courseTextField.requestFocus();
    } // end empty form

}// end cclass