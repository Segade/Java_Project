import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

/** an instantiable class which defines the ViewCourse class
 * it displays all the information about a course
 * Author: Ivan Segade Carou
 *


 */

public class ViewCourse extends JFrame implements ActionListener {
// I declare all the  GUI  componenets
        JFrame viewCourseFrame = new JFrame("View course");
JPanel searchPanel = new JPanel(new FlowLayout());


JTextField searchTextField = new JTextField(10);

JLabel searchLabel = new JLabel("Course ID: ");

JButton searchButton = new JButton("Search");
JButton cancelButton = new JButton("Cancel");

// I declare the ArrayLIst where |I will save the data from The Course file
ArrayList<Course> allCourses = new ArrayList<>();
int positionCourse;


        /** constructor with no arguments
         *it hilds the form to search the course
         */
        public ViewCourse (){
// I set up the main frame
        viewCourseFrame.setSize(500,500);
viewCourseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

BorderLayout layout = new BorderLayout();
viewCourseFrame.setLayout(layout);

// I add the listener to the  button
searchButton.addActionListener(this);
cancelButton.addActionListener(this);

// I set up the search panel
searchPanel.add(searchLabel);
searchPanel.add(searchTextField);
searchPanel.add(searchButton);




        viewCourseFrame.add(searchPanel, BorderLayout.NORTH       );
// I add the search panel to the main frame
        viewCourseFrame.add(cancelButton, BorderLayout.SOUTH);

        // I read the Course file and I retriefve the data from it
open();


viewCourseFrame.setVisible(true);

} // end constructor


        /** I declare the listener
         *
         * @param e
         */
        public void actionPerformed (ActionEvent e){
// I deal with option picked by the user
String option = e.getActionCommand();
// it depends on the option one case will be executed or another
switch (option){
        case "Search":
                if (searchCourse())
                        JOptionPane.showMessageDialog(null, allCourses.get(positionCourse), "pass", JOptionPane.PLAIN_MESSAGE);
                else
                        JOptionPane.showMessageDialog(null, "Course not found", "Course not found", JOptionPane.ERROR_MESSAGE);

                searchTextField.setText("");
                searchTextField.requestFocus();

break;

        case "Cancel":
                viewCourseFrame.dispose();
} // end switch
} // end action performed


        /** method which reads the Course file and retrieves data from it
         *
         */

        public void open() {
                try {


                        File file = new File("courses.data");

                        if (file.exists()) {
// if the file already exists I retrieve the data from it

                                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                                allCourses = (ArrayList<Course>) is.readObject();
                                is.close();


                        } else {
                                // if the file does not exist I  create it
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


        /** method which searchs the course in the ArrayList
         *
         * @return
         */

        private  boolean searchCourse (){
                boolean pass = false;
                String idCourse = searchTextField.getText();
                int x= 0;

                while (x<allCourses.size()){
Course                        c = allCourses.get(x);

                        if (c.getId().equalsIgnoreCase(idCourse)) {
                                pass = true;
                                positionCourse = x;
                                x = allCourses.size() +2;
                        }
                        x++;
                } // end while

                return pass;
        } // end search course

} // end class
