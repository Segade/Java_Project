import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/** an instantiable class which adds new teacher to the Teacher file
 *Author: Ivan Segade Carou
 */

public class AddTeacher extends JFrame implements ActionListener{
    // I declare all the GUI components
    JFrame addTeacherFrame = new JFrame("Add teacher");

    JPanel formPanel = new JPanel(new GridLayout(10,2, 5,5));
    JPanel buttonsPanel = new JPanel(new BorderLayout(10, 10));
    JPanel dobPanel = new JPanel(new FlowLayout());
    JPanel genderPanel = new JPanel(new FlowLayout());

    BorderLayout layout = new BorderLayout(2, 2);

    JLabel nameLabel = new JLabel("Name:");
    JLabel surnameLabel = new JLabel("Surname:");
    JLabel addressLabel = new JLabel("Address:");
    JLabel townLabel = new JLabel("Town:");
    JLabel countyLabel = new JLabel("County:");
    JLabel dobLabel = new JLabel("Date of Birth:");
    JLabel slashLabel1  = new JLabel(" / ");
    JLabel slashLabel2 = new JLabel(" / ");
    JLabel genderLabel = new JLabel("Gender:");
    JLabel departmentLabel = new JLabel("Department:");
    JLabel blank = new JLabel("   ");
    JLabel phoneLabel = new JLabel("Phone:");
    JLabel emailLabel = new JLabel("Email:");

    JTextField nameTextField = new JTextField(20);
    JTextField surnameTextField = new JTextField(30);
    JTextField addressTextField = new JTextField(40);
    JTextField townTextField = new JTextField(20);
    JTextField countyTextField = new JTextField(20);
    JTextField departmentTextField = new JTextField(25);
    JTextField phoneTextField = new JTextField(12);
    JTextField emailTextField = new JTextField(30);

    JComboBox dayComboBox = new JComboBox();
    JComboBox monthComboBox = new JComboBox();
    JComboBox yearComboBox = new JComboBox();

    JButton addButton = new JButton("Add");
    JButton cancelButton = new JButton("Cancel");

    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    ButtonGroup genderGroup = new ButtonGroup();



    // I declare the ArrayList that will hold the data from the Teacher file
    ArrayList<Teacher> allTeachers = new ArrayList<>();
int size;

    /** I declare all the GUI components
     * frame
     * panels
     * textfields
     * labels
     * buttons
     * comboboxes
     * radiobuttons
     */


/*     * constructor with all the forms
     * the programme will ask the user to enter the required data
     * name. the name of the teacher
     * surname. the surname of the teacher
     * address. the address of the teacher
     * town. the town of the teacher
     * county. the county of the teacher
     *Data of Birth. the date of Birth of teacher
     * gender. the gender of the teacher
     * phone. the phone of the teacher
     * email. the email of the teacher
    *department. the department of the teacher
     */



    AddTeacher ()
    {
// I set upt the main frame
        addTeacherFrame.setSize(500,500);
        addTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(layout);

        // I call this method to fill the ComboBoxes for the DOB
        generateComboBoxes();

        // I set up the panel where the ComboBoxes will be
        dobPanel.add(dayComboBox);
        dobPanel.add(slashLabel1);
        dobPanel.add(monthComboBox);
        dobPanel.add(slashLabel2);
        dobPanel.add(yearComboBox);
// I set up the panel where the RadioButtons for the gender will be
        genderGroup.add(maleRadioButton);
genderGroup.add(femaleRadioButton);
        genderPanel.add(maleRadioButton);
                genderPanel.add(femaleRadioButton);

        // I add all the components to the main form
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
        formPanel.add(departmentLabel);
        formPanel.add(departmentTextField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneTextField);
        formPanel.add(emailLabel);
        formPanel.add(emailTextField);


// I add the listener to the buttons
        addButton.addActionListener(this);
        cancelButton.addActionListener(this);


        // I set up the panel where the buttons will be
        buttonsPanel.add(addButton, BorderLayout.EAST);
        buttonsPanel.add(cancelButton, BorderLayout.WEST);

        // I add the panels to the main frame
        addTeacherFrame.add(formPanel, BorderLayout.CENTER);
        addTeacherFrame.add(buttonsPanel, BorderLayout.SOUTH);
        formPanel.setVisible(true);
        addTeacherFrame.setVisible(true);

        // I call this method to read the Teacher file
        open();
    } // end constructor


    /** I declare the listener
     *
     * @param e
     */


    public void actionPerformed(ActionEvent e)
    {
        // I deal with theoption picked by the user
        String option = e.getActionCommand();
// It depends on the option the programme will run one case or another
        switch(option)
        {
            case "Add":
                String myDay = (String) dayComboBox.getSelectedItem();
                String myMonth = (String) monthComboBox.getSelectedItem();
                String myYear = (String)yearComboBox.getSelectedItem();


                // I call the validation method in the Validator class
                String texto = Validator.validateForm(nameTextField.getText(), surnameTextField.getText(), addressTextField.getText(),townTextField.getText(), countyTextField.getText(), myDay, myMonth, myYear, phoneTextField.getText(), emailTextField.getText(), departmentTextField.getText());

                char gender = 'X';
if (! maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
    texto += "\nEnter a gender";

                if (texto.equals("") ) {
                    saveTeacher();
                    emptyForm();
                }else
                    JOptionPane.showMessageDialog(null, texto , "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Cancel":
  addTeacherFrame.dispose();
                break;

        } // end switch

    } //end actionperformed


    /** method which saves the data in the Teacher file
     *
     */

    private void saveTeacher()
    {
        // I get all the information entered by the user
        String id = "TEA" + String.format("%03d", (size+1));
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        String address = addressTextField.getText();
        String town = townTextField.getText();
        String county = countyTextField.getText();
        String email = emailTextField.getText();
        String phone = phoneTextField.getText();
        String myDay = (String) dayComboBox.getSelectedItem();
        String myMonth = (String) monthComboBox.getSelectedItem();
        String myYear = (String)yearComboBox.getSelectedItem();
         GregorianCalendar dob = new GregorianCalendar(Integer.parseInt(myYear), Integer.parseInt(myMonth), Integer.parseInt(myDay));
        String department = departmentTextField.getText();
        char gender = 'X';

if (maleRadioButton.isSelected())
    gender = 'M';
else
    gender = 'F';

// I create a new Teacher class to add it to the ArrayList and then add it to the Teacher file
        Teacher t = new Teacher(id, name, surname, address, town, county, dob, email, phone, gender, department );
allTeachers.add(t);
        File outFile  = new File("teachers.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


// I save the ArrayList with the new data on it
            objectOutStream.writeObject(allTeachers);

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
JOptionPane.showMessageDialog(null, "Teacher added successfuly\n" + t, "Add success", JOptionPane.INFORMATION_MESSAGE);
    } // end save teacher



    /**
     * method which fills the comoboxes for the data of birth
     * it enters the days, months and years
     */

    public void generateComboBoxes()
    {

        dayComboBox.addItem("Day");;
        for (int x=1;x<32;x++)
            dayComboBox.addItem(Integer.toString(x));

        monthComboBox.addItem("Month");
        for (int x=1;x<13;x++)
            monthComboBox.addItem(Integer.toString(x));

        yearComboBox.addItem("Year");;
        for (int x=2021;x>1910;x--)
            yearComboBox.addItem(Integer.toString(x));

    } // end generate combo boxes


    /** method which reads the Teacher file and retrieves the data from on it
     *
     */


    public void open() {
        try {

             File file = new File("teachers.data");

            if(file.exists()) { //this if-else added by JB

// if the file exists I get the data from it
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                    allTeachers = (ArrayList<Teacher>) is.readObject();
 size = allTeachers.size();
                is.close();




            }
            else{
                // if the file does not exists I create it
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

    /** method which resets the form
     *
     */


    private void emptyForm(){
nameTextField.setText("");
surnameTextField.setText("");
addressTextField.setText("");
townTextField.setText("");
countyTextField.setText("");
phoneTextField.setText("");
    emailTextField.setText("");
departmentTextField.setText("");
dayComboBox.setSelectedIndex(0);
monthComboBox.setSelectedIndex(0);
yearComboBox.setSelectedIndex(0);
maleRadioButton.setSelected(false);
femaleRadioButton.setSelected(false);
nameTextField.requestFocus();
}// end empty form



} // end class
