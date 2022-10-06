import javax.security.auth.login.AppConfigurationEntry;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Login extends JDialog implements ActionListener{


JFrame loginFrame = new JFrame("Login Access");

    JPanel formPanel = new JPanel(new GridLayout(3,2));
JLabel idLabel = new JLabel("ID:");
JLabel passwordLabel = new JLabel("Password:");
JLabel blanckLabel = new JLabel(" ");

    JTextField idTextField = new JTextField(15);
JPasswordField passwordField = new JPasswordField(20);

JButton loginButton = new JButton("Login");

public Login (JFrame f)
{
   super( f, true);



  loginFrame.setLayout(new FlowLayout());

    loginButton.addActionListener(this);

    loginFrame.add(idLabel);
    loginFrame.add(idTextField);
    formPanel.add(passwordLabel);
    formPanel.add(passwordField);
    formPanel.add(blanckLabel);
    formPanel.add(loginButton);


    loginFrame.add(formPanel);
    loginFrame.setSize(500,250);


loginFrame.setVisible(true);
} // end constructor
/*
    public static void main(String[] args) {
JFrame f = new JFrame("hello");
                    Login l = new Login(f);

    }

 */

        public void actionPerformed  (ActionEvent e)
        {
            String option = e.getActionCommand();

            switch(option)
            {
                case "Login": JOptionPane.showMessageDialog(null , "pass", "pass" , JOptionPane.PLAIN_MESSAGE);


loginFrame.dispose();
            }

    } // end cation performed





} // end class

