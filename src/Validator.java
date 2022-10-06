import javax.swing.*;
import java.util.GregorianCalendar;

/** an uninstantiable class which defines the Validator class
 * it just holds methods which are used in more than one class
 * Author: Ivan Segade Carou
  */

public class Validator {


    /** method which validates the form which is common for teachers and students
     *
     * @param name
     * @param surname
     * @param address
     * @param town
     * @param county
     * @param myDay
     * @param myMonth
     * @param myYear
     * @param phone
     * @param email
     * @param department
     * @return
     */

    public static   String validateForm(String name, String surname, String address, String town, String county, String myDay, String myMonth, String myYear, String phone, String email, String department){
                                               String error = "";


        if (name.equals("") ||  !isLetter(name))
            error ="Enter a valid name";

        if (surname.equals("") || !isLetter(surname))
            error +="Enter a valid surname";

        if (address.equals(""))
            error +="\nEnter a address";

        if (town.equals("") || !isLetter(town))
            error +="\nEnter a valid town";


        if (county.equals("") || !isLetter(county))
            error +="\nEnter a county";



        if ( phone.length() == 10 &&  isNumber(phone)){
                if (!phone.startsWith("08") && !phone.startsWith("06"))
                    error += "\naaaaanPlease enter a valid phone number";
            }else
                error += "\nPlease enter a valid phone number";




        if (email.equals(""))
            error += "\nPlease, enter a valid email";

        if (!checkDate(myDay, myMonth, myYear))
            error += "\nEnter a valid Date of Birth";
        return error;
    } // end validate form teacher


    public static   String validateStudentForm(String name, String surname, String address, String town, String county, String myDay, String myMonth, String myYear, String phone, String email){
        String error = "";


        if (name.equals("") ||  !isLetter(name))
            error ="Enter a valid name";

        if (surname.equals("") || !isLetter(surname))
            error +="Enter a valid surname";

        if (address.equals(""))
            error +="\nEnter a address";

        if (town.equals("") || !isLetter(town))
            error +="\nEnter a valid town";

        if (county.equals("") || !isLetter(county))
            error +="\nEnter a county";


        if (!checkDate(myDay, myMonth, myYear))
            error += "\nEnter a valid Date of Birth";
        return error;
    } // end validate form student


    /** method which vlaidates if a string has only letters
     *
     * @param enter
     * @return
     */
    public static  boolean isLetter(String enter){
        int x = 0;

        while (x<enter.length()){
            if (Character.isDigit(enter.charAt(x)))
                x = enter.length() +1;
            else
                x++;
        } // end while

        if (x>enter.length())
            return false;

        return true;
    } // end is letter


    /** method which checks if the string is just number
     *
     * @param enter
     * @return
     */

    public static  boolean isNumber(String enter){
        int x = 0;

        while (x<enter.length()){
            if (!Character.isDigit(enter.charAt(x)))
                x = enter.length() +1;
            else
                x++;
        } // end while

        if (x>enter.length())
            return false;

        return true;
    } // end is letter


    /** method which checks the date entered is correct
     *
     * @param myDay
     * @param myMonth
     * @param myYear
     * @return
     */

    public static  boolean checkDate(String myDay, String myMonth, String myYear) {
        boolean correct = true;

        if (!myDay.equals("Day") && !myMonth.equals("Month") && !myYear.equals("Year"))
        {
            int month = Integer.parseInt(myMonth);
            int day =Integer.parseInt(myDay);
            int year = Integer.parseInt(myYear);

            switch (month) {
                case 4:
                case 6:
                case 9:
                case 11:
                    if (day == 31)
                        correct = false;

                    break;

                case 2:

                    if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                        if (day > 29)
                            correct = false;
                    }else
                    if (day >28)
                        correct = false;

            }// end switch
        } else
            correct = false;

        return correct;
    } //end check date



} // end class