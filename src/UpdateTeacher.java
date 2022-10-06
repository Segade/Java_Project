import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;
import java.util.GregorianCalendar;

/** an instantiable class which defines the UpdateTeacher class
 * it updates the information of a teacher
 *
 */





public class UpdateTeacher extends JFrame implements ActionListener {
    // I declare all the GUI components
    JFrame updateTeacherFrame = new JFrame("Update teacher");
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
    JTextField departmentTextField = new JTextField(25);
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
    JLabel departmentLabel = new JLabel("Department:");
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

// I declare the ArrayList where I will save the data from the Teacher file
    ArrayList<Teacher> allTeachers = new ArrayList<>();
    Teacher t = null;


    /** constructor with no arguments
     * it displays the form where the user will insert the data
     *
     */

     UpdateTeacher (){
// I set up the main frame
         updateTeacherFrame.setSize(500,500);
         updateTeacherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
updateTeacherFrame.setLayout(layout);

// I add the listener to the search button
searchButton.addActionListener(this);

// I set up the search panel
         searchPanel.add(searchLabel);
         searchPanel.add(searchTextField);
         searchPanel.add(searchButton);


         // I set up the panel for the DOB
         dobPanel.add(dayComboBox);
         dobPanel.add(slashLabel1);
         dobPanel.add(monthComboBox);
         dobPanel.add(slashLabel2);
         dobPanel.add(yearComboBox);


// I set up t he gender panel
         genderGroup.add(maleRadioButton);
         genderGroup.add(femaleRadioButton);
         genderPanel.add(maleRadioButton);
         genderPanel.add(femaleRadioButton);

         // I set up the main form
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
         formPanel.add(departmentLabel);
         formPanel.add(departmentTextField);

         // I add the listener to the buttons
updateButton.addActionListener(this);
cancelButton.addActionListener(this);

// I set up the buttons panel
buttonsPanel.add(updateButton, BorderLayout.EAST);
         buttonsPanel.add(cancelButton, BorderLayout.WEST);


         // I set up the main frame
         updateTeacherFrame.add(searchPanel, BorderLayout.NORTH);
         updateTeacherFrame.add(formPanel, BorderLayout.CENTER);
updateTeacherFrame.add(buttonsPanel, BorderLayout.SOUTH );
formPanel.setVisible(false);
updateTeacherFrame.setVisible(true);
updateButton.setVisible(false) ;

// I call this method to retrieve the data from the Teacher file
open();
     } // end constructor

    /** I declare the listener
     *
     * @param e
     */


    public  void actionPerformed (ActionEvent e) {
        // I deal with the option picked by the user
        String option = e.getActionCommand();
// It depends on the option one case will be executed or another
        switch (option) {
            case "Search":
                search();

                break;
            case "Update":
                String myDay = (String) dayComboBox.getSelectedItem();
                String myMonth = (String) monthComboBox.getSelectedItem();
                String myYear = (String) yearComboBox.getSelectedItem();


                String texto = Validator.validateForm(nameTextField.getText(), surnameTextField.getText(), addressTextField.getText(), townTextField.getText(), countyTextField.getText(), myDay, myMonth, myYear, phoneTextField.getText(), emailTextField.getText(), departmentTextField.getText());

                char gender = 'X';
                if (!maleRadioButton.isSelected() && !femaleRadioButton.isSelected())
                    texto += "\nEnter a gender";

                if (texto.equals("")) {
                    saveTeacher();
                    emptyForm();
                    formPanel.setVisible(false);
                    updateButton.setVisible(false);

                } else
                    JOptionPane.showMessageDialog(null, texto, "Error", JOptionPane.ERROR_MESSAGE);
                break;
            case "Cancel":
                updateTeacherFrame.dispose();
        } // end switch
    } // end listener


    /** it generates the days, omths and years
     * if selects the right date of birth for the teacher
     *
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

    /** method which reads the Teacher file and retrieves data from it
     *
     */

    public void open() {
        try {


            File file = new File("teachers.data");

            if(file.exists()) {
// if the file exists I eetrieve the data from the file

                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                allTeachers = (ArrayList<Teacher>) is.readObject();
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


    /** method which searches the required teacher in the ArrayList
     *
     */

    private void search (){
    String id = searchTextField.getText();
    int x= 0;
// I go through the ArrayList finding the id
while (x<allTeachers.size()){
    t = allTeachers.get(x);

    if (t.getId().equalsIgnoreCase(id)) {
        display(t);
x = allTeachers.size() +2;
    }
x++;
     } // end while
if (x == allTeachers.size()) {
    JOptionPane.showMessageDialog(null, "No match", "Error", JOptionPane.ERROR_MESSAGE);
    formPanel.setVisible(false);
    updateButton.setVisible(false);
}
    } // end search


    /** method which displays the information of the teacher on the form
     *
     * @param t
     */

    private void display(Teacher t){
         // I fill the form with the teacher data
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

         departmentTextField.setText(t.getDepartment());

         formPanel.setVisible(true);
         updateButton.setVisible(true);
    } // end display


    /** method which saves the ArrayList with the updated information
     *
     */

    private void saveTeacher()
    {

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

int y = 0;

for (int x=0; x<allTeachers.size() ;x++){

if (allTeachers.get(x).getId().equals(t.getId() )){
// if I find the teacher, I update his information
    allTeachers.get(x).setName(nameTextField.getText());
allTeachers.get(x).setSurname(surnameTextField.getText());
allTeachers.get(x).setAddress(addressTextField.getText());
allTeachers.get(x).setTown(townTextField.getText());
    allTeachers.get(x).setCounty(countyTextField.getText());
    allTeachers.get(x).setGender(gender);
    allTeachers.get(x).setDob(dob);
     allTeachers.get(x).setPhone(phoneTextField.getText());
     allTeachers.get(x).setEmail(emailTextField.getText());
y = x;
} // end if

        } // end for

        File outFile  = new File("teachers.data");
        try{
            FileOutputStream outStream = new FileOutputStream(outFile);

            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);


// I write the file with the updated ArrayList
            objectOutStream.writeObject(allTeachers);

            outStream.close();

            JOptionPane.showMessageDialog(null, "Teacher updated successfuly\n" + allTeachers.get(y).toString(), "Update success", JOptionPane.INFORMATION_MESSAGE);
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

    } // end save teacher


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
        departmentTextField.setText("");
        dayComboBox.setSelectedIndex(0);
        monthComboBox.setSelectedIndex(0);
        yearComboBox.setSelectedIndex(0);
        maleRadioButton.setSelected(false);
        femaleRadioButton.setSelected(false);
        searchTextField.requestFocus();
    }// end empty form


} // end class
