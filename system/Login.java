//package university.management.system;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.sql.*;
//
//public class Login extends JFrame implements ActionListener, FocusListener {
//
//    JButton login, cancel;
//    JTextField tfusername;
//    JPasswordField tfpassword;
//
//    Login() {
//
//        getContentPane().setBackground(Color.WHITE);
//        setLayout(null);
//
//        JLabel lblusername = new JLabel("Username");
//        lblusername.setBounds(40, 20, 100, 20);
//        add(lblusername);
//
//        tfusername = new JTextField();
//        tfusername.setBounds(150, 20, 150, 20);
//        tfusername.addFocusListener(this); // Adding focus listener
//        add(tfusername);
//
//        JLabel lblpassword = new JLabel("Password");
//        lblpassword.setBounds(40, 70, 100, 20);
//        add(lblpassword);
//
//        tfpassword = new JPasswordField();
//        tfpassword.setBounds(150, 70, 150, 20);
//        tfpassword.addFocusListener(this); // Adding focus listener
//        add(tfpassword);
//
//        login = new JButton("Login");
//        login.setBounds(40, 140, 120, 30);
//        login.setBackground(Color.BLACK);
//        login.setForeground(Color.WHITE);
//        login.addActionListener(this);
//        login.setFont(new Font("Tahoma", Font.BOLD, 15));
//        add(login);
//
//        cancel = new JButton("Cancel");
//        cancel.setBounds(180, 140, 120, 30);
//        cancel.setBackground(Color.BLACK);
//        cancel.setForeground(Color.WHITE);
//        cancel.addActionListener(this);
//        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
//        add(cancel);
//
//        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/about.jpg"));
//        Image i2 = i1.getImage().getScaledInstance(200, 210, Image.SCALE_DEFAULT);
//        ImageIcon i3 = new ImageIcon(i2);
//        JLabel image = new JLabel(i3);
//        image.setBounds(350, 10, 200, 210);
//        add(image);
//
//        setSize(600, 300);
//        setLocation(500, 250);
//        setVisible(true);
//    }
//
//    public void actionPerformed(ActionEvent ae) {
//        if (ae.getSource() == login) {
//            String username = tfusername.getText();
//            String password = new String(tfpassword.getPassword()); // Get password securely
//
//            if (username.equals("")) {
//                JOptionPane.showMessageDialog(null, "Username can't be empty");
//                return;
//            }
//            if (password.equals("")) {
//                JOptionPane.showMessageDialog(null, "Password can't be empty");
//                return;
//            }
//
//            // Additional validation if needed
//            if (!username.matches("[a-zA-Z]+")) {
//                JOptionPane.showMessageDialog(null, "Invalid username. Please enter only alphabets.");
//                return;
//                
//   
//            }
//
//            String query = "select * from login where username='" + username + "' and password='" + password + "'";
//
//            try {
//                Conn c = new Conn();
//                ResultSet rs = c.s.executeQuery(query);
//
//                if (rs.next()) {
//                    setVisible(false);
//                    new Project();
//                } else {
//                    JOptionPane.showMessageDialog(null, "Invalid username or password");
//                }
//                c.s.close();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else if (ae.getSource() == cancel) {
//            setVisible(false);
//        }
//    }
//    
//
//    @Override
//    public void focusGained(FocusEvent fe) {
//        JTextField source = (JTextField) fe.getComponent();
//        source.setBackground(Color.YELLOW);
//    }
//
//    @Override
//    public void focusLost(FocusEvent fe) {
//        JTextField source = (JTextField) fe.getComponent();
//        source.setBackground(Color.WHITE);
//    }
//
//    public static void main(String[] args) {
//        new Login();
//    }
//}



package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener, FocusListener {

    JButton login, cancel;
    JTextField tfusername;
    JPasswordField tfpassword;

    Login() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 20);
        tfusername.addFocusListener(this); // Adding focus listener
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 20);
        tfpassword.addFocusListener(this); // Adding focus listener
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        login.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/about.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 210, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 210);
        add(image);

        setSize(600, 300);
        setLocation(500, 250);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword()); // Get password securely

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username can't be empty");
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password can't be empty and must be at Strong");
                return;
            }

            // Check password strength
            if (!isStrongPassword(password)) {
                JOptionPane.showMessageDialog(null,"\"Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character.");
                return;
            }

            String query = "select * from login where username='" + username + "' and password='" + password + "'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    setVisible(false);
                    new Project();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
                c.s.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    private boolean isStrongPassword(String password) {
        // Password must be at least 8 characters long and include uppercase, lowercase, digit, and special character
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";
        return password.matches(regex);
    }

    @Override
    public void focusGained(FocusEvent fe) {
        JTextField source = (JTextField) fe.getComponent();
        source.setBackground(Color.YELLOW);
    }

    @Override
    public void focusLost(FocusEvent fe) {
        JTextField source = (JTextField) fe.getComponent();
        source.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {
        new Login();
    }
}
