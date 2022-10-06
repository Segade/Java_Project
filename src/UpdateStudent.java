import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.GregorianCalendar;

/** an instantiable class which defines the UpdateStudent
 * if updates the information of the student
 * Author: Ivan Segade Carou
 */

public class UpdateStudent extends JFrame implements ActionListener {
// I declare all the GUI  components
    JFrame updateStudentFrame = new JFrame("Update student");
    JPanel formPanel = new JPanel(new GridLayout(10,2, 5,5));
    JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));
    JPanel dobPanel = new JPanel(new FlowLayout());
    JPanel genderPanel = new JPanel(new FlowLayout());
    JPanel searchPanel = new JPanel(new GridLayout(1,3,5,5));


    BorderLayout layout = new BorderLayout(2, 2);



    JTextField searchTextField = new JTextField(10);
    JTextField nameTextField = new JTextField(20);
    JTextField surnameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(40);
    JTextField townTextField = new JTextField(20);
    JTextField countyTextField = new JTextField(20);

    JTextField phoneTextField = new JTextField(12);
    JTextField emailTextField = new JTextField(30);

    JComboBox dayComboBox = new JComboBox();
    JComboBox monthComboBox = new JComboBox();
    JComboBox yearComboBox = new JComboBox();

    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel addressLabel = new JLabel("Address:");
    JLabel townLabel = new JLabel("Town:");
    JLabel countyLabel = new JLabel("County:");
    JLabel dobLabel = new JLabel("Date of Birth:");
    JLabel slashLabel1  = new JLabel(" / ");
    JLabel slashLabel2 = new JLabel(" / ");
    JLabel genderLabel = new JLabel("Gender:");
    JLabel blank = new JLabel("   ");
    JLabel searchLabel = new JLabel("ID:");
    JLabel phoneLabel = new JLabel("Phone:");
    JLabel emailLabel = new JLabel("Email:");

    JButton searchButton = new JButton("Search");
    JButton updateButton = new JButton("Update");
    JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();

    // I declare the ArrayList where I will save the data from the Student file
    ArrayList<Student> allStudents = new ArrayList<>();
    Student t = null;


    /** constructor with arguments
     * It holds the forms to insert the required information
     *
     */

    UpdateStudent (){
// I set up the main frame
        updateStudentFrame.setSize(500,500);
        updateStudentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateStudentFrame.setLayout(layout);
// I add the listener to the search button
        searchButton.addActionListener(this);

        // I set up the panel with all the components for the search
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);


        // I set up the panel where I add all the components for the DOB
        dobPanel.add(dayComboBox);
        dobPanel.add(slashLabel1);
        dobPanel.add(monthComboBox);
        dobPanel.add(slashLabel2);
        dobPanel.add(yearComboBox);

        // I set up the panel where I add all the components for the gender
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);

// I set up the main form with the student information
        formPanel.add(nameLabel);
        formPanel.add(nameTextField);
        formPanel.add(surnameLabel);
        formPanel.add(surnameTextField);
        formPanel.add(addressLabel);
        formPanel.add(addressTextField);
        formPanel.add(townLabel);
        formPanel.add(townTextField);
        formPanel.add(countyLabel);
        formPanel.add(countyTextField);
        formPanel.add(dobLabel);
        formPanel.add(dobPanel);
        formPanel.add(genderLabel);
        formPanel.add(genderPanel);
        formPanel.add(phoneLabel);
        formPanel.add(phoneTextField);
        formPanel.add(emailLabel);
        formPanel.add(emailTextField);

        // I add the listener to the buttons
        updateButton.addActionListener(this);
        cancelButton.addActionListener(this);

        // I set upt the buttons panel where Update and Cancel will be
        buttonsPanel.add(updateButton, BorderLayout.EAST);
        buttonsPanel.add(cancelButton, BorderLayout.WEST);


        // I set up the main frame
        updateStudentFrame.add(searchPanel, BorderLayout.NORTH);
        updateStudentFrame.add(formPanel, BorderLayout.CENTER);
        updateStudentFrame.add(buttonsPanel, BorderLayout.SOUTH );
        formPanel.setVisible(false);
        updateStudentFrame.setVisible(true);
        updateButton.setVisible(false) ;


// I call this method to read the data in the Student file
        open();
    } // end constructor


    /**
     *I declare the listener
     * @param e
     */
    public  void actionPerformed (ActionEvent e){
// I deal with the option picked by the user
        String option = e.getActionCommand();

        // It depends on the option, one case will be executed ot another
        switch (option)
        {
            case "Search":
                search();

                break;
            case "Update":
                String myDay = (String) dayComboBox.getSelectedItem();
                String myMonth = (String) monthComboBox.getSelectedItem();
                String myYear = (String)yearComboBox.getSelectedItem();


                String texto = Validator.validateForm(nameTextField.getText(), surnameTextField.getText(), addressTextField.getText(),townTextField.getText(), countyTextField.getText(), myDay, myMonth, myYear, phoneTextField.getText(), emailTextField.getText(), "----");

                char gender = 'X';
                if (! maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
                    texto += "\nEnter a gender";

                if (texto.equals("") ) {
                    saveStudent();
emptyForm();
                    formPanel.setVisible(false);
                    updateButton.setVisible(false);

                }else
                    JOptionPane.showMessageDialog(null, texto , "Error", JOptionPane.ERROR_MESSAGE);

                break;
            case "Cancel":
                updateStudentFrame.dispose();
        } // end switch
    }// end action performed

    /** method which fills the comboboxes with the right data
     * it generates the days, months and years
     * it selects the right date for the student
     * @param day
     * @param month
     * @param year
     */

    public void setComboBoxes(String day, String month, String year)
    {

        dayComboBox.addItem("Day");;
        for (int x=1;x<32;x++) {
            dayComboBox.addItem(Integer.toString(x));
            if (day.equals(Integer.toString(x)))
                dayComboBox.setSelectedIndex(x);
        } // end for day

        monthComboBox.addItem("Month");
        for (int x=1;x<13;x++) {
            monthComboBox.addItem(Integer.toString(x));
            if (month.equals(Integer.toString(x)))
                monthComboBox.setSelectedIndex(x);
        } // end for month

        int y = 0;
        yearComboBox.addItem("Year");;
        for (int x=2021;x>1910;x--) {
            yearComboBox.addItem(Integer.toString(x));
            y++;
            if (year.equals(Integer.toString(x)))
                yearComboBox.setSelectedIndex(y);
        } // end for year

    } // end generate combo boxes

    /** method which reads the Student file and retrieves data from it
     *
     */

    public void open() {
        try {


            File file = new File("students.data");

            if(file.exists()) { //this if-else added by JB
// if the file already exists I retrives the data from it

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allStudents = (ArrayList<Student>) is.readObject();
                is.close();




            }
            else{ //This code added by JB
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


    /** method which searchs the student on the ArrayList
     *
     */

    private void search (){
        String id = searchTextField.getText();
        int x= 0;

        while (x<allStudents.size()){
            t = allStudents.get(x);

            if (t.getId().equalsIgnoreCase(id)) {
                display(t);
                x = allStudents.size() +2;
            }
            x++;
        } // end while
        if (x == allStudents.size()) {
            JOptionPane.showMessageDialog(null, "No match", "Error", JOptionPane.ERROR_MESSAGE);
            formPanel.setVisible(false);
            updateButton.setVisible(false);
        }
    } // end search


    /** method which displays the information of the student on the form
     *
     * @param t
     */

    private void display(Student t){
        nameTextField.setText(t.getName());
        surnameTextField.setText(t.getSurname());
        addressTextField.setText(t.getAddress());
        townTextField.setText(t.getTown());
        countyTextField.setText(t.getCounty());
phoneTextField.setText(t.getPhone());
        emailTextField.setText(t.getEmail());

        int year =t.getDob().get(GregorianCalendar.YEAR);
        int month =t.getDob().get(GregorianCalendar.MONTH);
        int day =t.getDob().get(GregorianCalendar.DATE);
        setComboBoxes(Integer.toString(day),  Integer.toString(month), Integer.toString(year));

        char gender = t.getGender();
        if (gender == 'M')
            maleRadioButton.setSelected(true);
        else
            femaleRadioButton.setSelected(true);


        formPanel.setVisible(true);
        updateButton.setVisible(true);
    } // end display


    /** method which saves the ArrayList with data in the Student file
     *
     */

    private void saveStudent()
    {

        String myDay = (String) dayComboBox.getSelectedItem();
        String myMonth = (String) monthComboBox.getSelectedItem();
        String myYear = (String)yearComboBox.getSelectedItem();

        GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(myYear), Integer.parseInt(myMonth), Integer.parseInt(myDay));
        char gender = 'X';

        if (maleRadioButton.isSelected())
            gender = 'M';
        else
            gender = 'F';

        int y = 0;

        for (int x=0; x<allStudents.size() ;x++){

            if (allStudents.get(x).getId().equals(t.getId() )){
// if I find the student I modify his data
                allStudents.get(x).setName(nameTextField.getText());
                allStudents.get(x).setSurname(surnameTextField.getText());
                allStudents.get(x).setAddress(addressTextField.getText());
                allStudents.get(x).setTown(townTextField.getText());
                allStudents.get(x).setCounty(countyTextField.getText());
                allStudents.get(x).setGender(gender);
                allStudents.get(x).setDob(dob);
                allStudents.get(x).setPhone(phoneTextField.getText());
                allStudents.get(x).setEmail(emailTextField.getText());
                y = x;
            } // end if

        } // end for

        File outFile  = new File("students.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


// I write the ArrayLIst with the updated information
            objectOutStream.writeObject(allStudents);

            outStream.close();

            JOptionPane.showMessageDialog(null, "Student updated successfuly\n" + allStudents.get(y).toString(), "Update success", JOptionPane.INFORMATION_MESSAGE);
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

    } // end save student


    /** method which resets the form
     *
     */

    private void emptyForm(){
        searchTextField.setText("");
        nameTextField.setText("");
        surnameTextField.setText("");
        addressTextField.setText("");
        townTextField.setText("");
        countyTextField.setText("");
        phoneTextField.setText("");
        emailTextField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
         searchTextField.requestFocus();
    }// end empty form


} // end class
