import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/** an instantiable class which defines the ViewTeacher class
 * it displays all the information about a teacher
 *


 */

public class ViewTeacher extends JFrame implements ActionListener {
    // I declare all the GUI  components
    JFrame viewTeacherFrame = new JFrame("View teacher");
    JPanel searchPanel = new JPanel(new FlowLayout());


    JTextField searchTextField = new JTextField(10);

    JLabel searchLabel = new JLabel("Teacher ID: ");

    JButton searchButton = new JButton("Search");
    JButton cancelButton = new JButton("Cancel");




// I declare the ArrayList where I will save the data from the teacher file
    ArrayList<Teacher> allTeachers = new ArrayList<>();
    int positionTeacher;


    /** constructor with no arguments
     * it just holds the form to search the teacher
     *
      */

    public ViewTeacher (){
// I set up the main frame
        viewTeacherFrame.setSize(500,500);
        viewTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        viewTeacherFrame.setLayout(layout);

        // I add the listener to the buttons
        searchButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // I set up the search panel
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        // I set up the main frame
        viewTeacherFrame.add(searchPanel, BorderLayout.NORTH       );

        viewTeacherFrame.add(cancelButton, BorderLayout.SOUTH);

        // I call the method which reads the data from the Teache r file
        open();


        viewTeacherFrame.setVisible(true);

    } // end constructor

    /** I declare the listener
     *
     * @param e
     */

    public void actionPerformed (ActionEvent e){
// I deal with the option picked by the user
        String option = e.getActionCommand();
// it depends on the option one case will be executed or another
        switch (option){
            case "Search":
                if (searchTeacher())
                    JOptionPane.showMessageDialog(null, allTeachers.get(positionTeacher), "pass", JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null, "Teacher not found", "Teacher not found", JOptionPane.ERROR_MESSAGE);

                searchTextField.setText("");
                searchTextField.requestFocus();
                break;

            case "Cancel":
                viewTeacherFrame.dispose();
        } // end switch
    } // end action performed

    /** method which reads the Teacher file and retrieves data from it
     *
      */

    public void open() {
        try {


            File file = new File("teachers.data");

            if(file.exists()) { //this if-else added by JB
// if the file exists I retrieve the data from it

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allTeachers = (ArrayList<Teacher>) is.readObject();
                is.close();

             }
            else{
                // if the does not exist I create it
                file.createNewFile();

            }
        } //these individual catch clauses added by JB, replacing a single "Exception" catch clause
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


    /** method which searchs the teacher in the ArrayLIst
     * @return
     */

    // if it finds it it returns true, otherwise it returns falseb
    private  boolean searchTeacher (){
        boolean pass = false;
        String idTeacher = searchTextField.getText();
        int x= 0;

        while (x<allTeachers.size()){
            Teacher                        c = allTeachers.get(x);

            if (c.getId().equalsIgnoreCase(idTeacher)) {
                pass = true;
                positionTeacher = x;
                x = allTeachers.size() +2;
            }
            x++;
        } // end while

        return pass;
    } // end search teacher

} // end class
