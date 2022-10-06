    import javax.swing.*;
    import javax.swing.text.View;
    import java.awt.*;
import java.awt.event.*;
    import javax.swing.*;
    import javax.swing.text.View;
    import java.awt.*;
import java.awt.event.*;

/** an instantiable class which defines the class object
 * it is the core of the programme
 * it is the only class with the main method
 */


/** declaration section
    * // all the GUI components
 * * the whole menu
 * file
 * teacher options
 *course options
 * student options
 * */

    public class Academy extends JFrame implements ActionListener{
    JFrame academyFrame;


    JMenuBar mainMenu = new  JMenuBar();
 JMenu fileMenu = new JMenu("File");
    JMenuItem exitMenuItem = new JMenuItem("Exit");

    JMenu teacherMenu = new JMenu("Teachers");
    JMenuItem addTeacherMenuItem = new JMenuItem("Add teacher");
    JMenuItem updateTeacherMenuItem = new JMenuItem("Update/Delete teacher");
    JMenuItem viewTeacherMenuItem = new JMenuItem("View teacher");

    JMenu courseMenu = new JMenu("Courses");
    JMenuItem addCourseMenuItem = new JMenuItem("Add course");
    JMenuItem assignMenuItem = new JMenuItem("Assign student");
    JMenuItem viewCourseMenuItem = new JMenuItem("View course");

            JMenu studentMenu = new JMenu("Students");
    JMenuItem addStudentMenuItem = new JMenuItem("Add student");
    JMenuItem updateStudentMenuItem = new JMenuItem( "Update student");
    JMenuItem ViewStudentMenuItem = new JMenuItem("View student");

    JMenuItem  MenuItem = new JMenuItem("");

public boolean success;
     public int attempts;

    /** constructor  with o argument
     *
     */
    public Academy( ){

        /*
        while (!success) {
             Login l = new Login(this);
attempts++;
success = true;
         }
         */
// I set the frame
    academyFrame = new JFrame("Academy");
    academyFrame.setSize(1000, 1000);
    academyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    FlowLayout myFlow = new FlowLayout();
    academyFrame.setLayout(myFlow);

    // I set all the menu
         // I add the listener to the menu items
exitMenuItem.addActionListener(this);
    addTeacherMenuItem.addActionListener(this);
    updateTeacherMenuItem.addActionListener(this);
    viewTeacherMenuItem.addActionListener(this);
    addCourseMenuItem.addActionListener(this);
    assignMenuItem.addActionListener(this);
    viewCourseMenuItem.addActionListener(this);
    addStudentMenuItem.addActionListener(this);
    updateStudentMenuItem.addActionListener(this);
    ViewStudentMenuItem.addActionListener(this);


    // I add all the menu items to menu
    fileMenu.add(exitMenuItem);
    fileMenu.setMnemonic('f');

    teacherMenu.add(addTeacherMenuItem);
    teacherMenu.add(updateTeacherMenuItem);
    teacherMenu.add(viewTeacherMenuItem);

    courseMenu.add(addCourseMenuItem);
    courseMenu.add(assignMenuItem);
    courseMenu.add(viewCourseMenuItem);

    studentMenu.add(addStudentMenuItem);
    studentMenu.add(updateStudentMenuItem);
    studentMenu.add(ViewStudentMenuItem);

    mainMenu.add(fileMenu);
    mainMenu.add(teacherMenu);
    mainMenu.add(courseMenu);
    mainMenu.add(studentMenu);


    academyFrame.add(mainMenu);
    academyFrame.setVisible(true);


    } // end constructor

    /** main menu only holds the academy class
     */


    public static void main(String[] args) {

        Academy myAcademy = new Academy();
    } // end main


// I inicialise the listener
    public void actionPerformed  (ActionEvent e)
    {
//  I het which option the user picked
        String option = e.getActionCommand();

        // I deal with the options and it depends on the option I run one of the correcponding class
        switch(option)
        {
            case "Exit":
int exit = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
if (exit == JOptionPane.YES_OPTION)
    System.exit(0);
                break;
            case "Add teacher": AddTeacher addTeacher = new AddTeacher();
                break;

             case "Update/Delete teacher": UpdateTeacher updateTeacher = new UpdateTeacher();
                 break;

             case "Add course": AddCourse addCourse = new AddCourse();
                 break;
            case "Assign student": AssignStudent assignStudent = new AssignStudent();
break;
             case "Add student":
             AddStudent addStudent = new  AddStudent();
                 break;

            case "View course":
                ViewCourse viewCourse = new ViewCourse();
                break;

            case "View student" : ViewStudent viewStudent = new ViewStudent();
            break;

            case "View teacher":
                ViewTeacher viewTeacher = new ViewTeacher();
break;
            case "Update student" :
                UpdateStudent updateStudent = new UpdateStudent();
        break;
        } // end switch


    } // end actionperformed

} // end of class
