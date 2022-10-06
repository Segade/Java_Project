import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;


/** an instantiable class which defines the ViewStudent class
 * it displays all the information about a student
 *Author: Ivan Segade Carou

 */

public class ViewStudent extends JFrame implements ActionListener {
    // I declare all the GUI  components
    JFrame viewStudentFrame = new JFrame("View student");
    JPanel searchPanel = new JPanel(new FlowLayout());


    JTextField searchTextField = new JTextField(10);

    JLabel searchLabel = new JLabel("Student ID: ");

    JButton searchButton = new JButton("Search");
    JButton cancelButton = new JButton("Cancel");


// I decclare the ArrayLIst where I save thedata retrieved from the Student file
    ArrayList<Student> allStudents = new ArrayList<>();
    int positionStudent;

    /** constructor with no arguments
     * it holds the form to search the student
      */

    public ViewStudent (){
        // I set up the main frame
        viewStudentFrame.setSize(500,500);
        viewStudentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        viewStudentFrame.setLayout(layout);

        // I add the listener to the buttons
        searchButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // I set up the search panel
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

// I set up the main frame
        viewStudentFrame.add(searchPanel, BorderLayout.NORTH       );

        viewStudentFrame.add(cancelButton, BorderLayout.SOUTH);

        // I call this method which reads the data from the file
        open();


        viewStudentFrame.setVisible(true);

    } // end constructor

    /** I declare the listener
     *
     * @param e
     */

    public void actionPerformed (ActionEvent e){
// I deal with the option picked by the user
        String option = e.getActionCommand();
// it depends on the optionone case will be executed or antoher
        switch (option){
            case "Search":
                if (searchStudent())
                    JOptionPane.showMessageDialog(null, allStudents.get(positionStudent), "pass", JOptionPane.PLAIN_MESSAGE);
else
                    JOptionPane.showMessageDialog(null, "Student not found", "Student not found", JOptionPane.ERROR_MESSAGE);

searchTextField.setText("");
searchTextField.requestFocus();
                break;

            case "Cancel":
                viewStudentFrame.dispose();
        } // end switch
    } // end action performed


    /**  method which reads the Student file and retrives data from it
     *
     */
    public void open() {
        try {


            File file = new File("students.data");

            if(file.exists()) { //this if-else added by JB
// if the file ixists I retrieve the data from it

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
    } // end open



    // method which searchs the student on the ArrayLIst

    /** method which searchs the student in th ArrayLIst
     *
     * @return
     */

    private  boolean searchStudent (){
        boolean pass = false;
        String idStudent = searchTextField.getText();
        int x= 0;

        while (x<allStudents.size()){
            Student                        c = allStudents.get(x);

            if (c.getId().equalsIgnoreCase(idStudent)) {
                pass = true;
                positionStudent = x;
                x = allStudents.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search student

} // end class
