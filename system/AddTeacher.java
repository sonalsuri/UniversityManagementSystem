package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.regex.Pattern;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;

public class AddTeacher extends JFrame implements ActionListener, FocusListener {
    
    JTextField tfname, tffname, tfaddress, tfphone, tfemail, tfx, tfxii, tfaadhar;
    JLabel labelempid;
    JDateChooser dcdob;
    JComboBox cbcourse, cbbranch;
    JButton submit, cancel;
    
    Random ran = new Random();
    long first4 = Math.abs((ran.nextLong() % 9000L) + 1000L);
    
    AddTeacher() {
        setSize(900, 700);
        setLocation(350, 50);
        setLayout(null);
        
        JLabel heading = new JLabel("New Teacher Details");
        heading.setBounds(310, 30, 500, 50);
        heading.setFont(new Font("serif", Font.BOLD, 30));
        add(heading);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(50, 150, 100, 30);
        lblname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblname);
        
        tfname = new JTextField();
        tfname.setBounds(200, 150, 150, 30);
        tfname.addFocusListener(this);
        add(tfname);
        
        JLabel lblfname = new JLabel("Father's Name");
        lblfname.setBounds(400, 150, 200, 30);
        lblfname.setFont(new Font("serif", Font.BOLD, 20));
        add(lblfname);
        
        tffname = new JTextField();
        tffname.setBounds(600, 150, 150, 30);
        tffname.addFocusListener(this);
        add(tffname);
        
        JLabel lblempid = new JLabel("Employee Id");
        lblempid.setBounds(50, 200, 200, 30);
        lblempid.setFont(new Font("serif", Font.BOLD, 20));
        add(lblempid);
        
        labelempid = new JLabel("101" + first4);
        labelempid.setBounds(200, 200, 200, 30);
        labelempid.setFont(new Font("serif", Font.BOLD, 20));
        add(labelempid);
        
        JLabel lbldob = new JLabel("Date of Birth");
        lbldob.setBounds(400, 200, 200, 30);
        lbldob.setFont(new Font("serif", Font.BOLD, 20));
        add(lbldob);
        
        dcdob = new JDateChooser();
        dcdob.setBounds(600, 200, 150, 30);
        add(dcdob);
        
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(50, 250, 200, 30);
        lbladdress.setFont(new Font("serif", Font.BOLD, 20));
        add(lbladdress);
        
        tfaddress = new JTextField();
        tfaddress.setBounds(200, 250, 150, 30);
        tfaddress.addFocusListener(this);
        add(tfaddress);
        
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(400, 250, 200, 30);
        lblphone.setFont(new Font("serif", Font.BOLD, 20));
        add(lblphone);
        
        tfphone = new JTextField();
        tfphone.setBounds(600, 250, 150, 30);
        tfphone.addFocusListener(this);
        add(tfphone);
        
        JLabel lblemail = new JLabel("Email Id");
        lblemail.setBounds(50, 300, 200, 30);
        lblemail.setFont(new Font("serif", Font.BOLD, 20));
        add(lblemail);
        
        tfemail = new JTextField();
        tfemail.setBounds(200, 300, 150, 30);
        tfemail.addFocusListener(this);
        add(tfemail);
        
        JLabel lblx = new JLabel("Class X (%)");
        lblx.setBounds(400, 300, 200, 30);
        lblx.setFont(new Font("serif", Font.BOLD, 20));
        add(lblx);
        
        tfx = new JTextField();
        tfx.setBounds(600, 300, 150, 30);
        tfx.addFocusListener(this);
        add(tfx);
        
        JLabel lblxii = new JLabel("Class XII (%)");
        lblxii.setBounds(50, 350, 200, 30);
        lblxii.setFont(new Font("serif", Font.BOLD, 20));
        add(lblxii);
        
        tfxii = new JTextField();
        tfxii.setBounds(200, 350, 150, 30);
        tfxii.addFocusListener(this);
        add(tfxii);
        
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(400, 350, 200, 30);
        lblaadhar.setFont(new Font("serif", Font.BOLD, 20));
        add(lblaadhar);
        
        tfaadhar = new JTextField();
        tfaadhar.setBounds(600, 350, 150, 30);
        tfaadhar.addFocusListener(this);
        add(tfaadhar);
        
        JLabel lblcourse = new JLabel("Qualification");
        lblcourse.setBounds(50, 400, 200, 30);
        lblcourse.setFont(new Font("serif", Font.BOLD, 20));
        add(lblcourse);
        
        String[] qualification = {"B.Tech", "M.Tech", "BBA", "BCA", "Bsc", "Msc", "MBA", "MCA", "MCom", "MA", "BA", "MS"};
        cbcourse = new JComboBox(qualification);
        cbcourse.setBounds(200, 400, 150, 30);
        cbcourse.setBackground(Color.WHITE);
        add(cbcourse);
        
        JLabel lblbranch = new JLabel("Department");
        lblbranch.setBounds(400, 400, 200, 30);
        lblbranch.setFont(new Font("serif", Font.BOLD, 20));
        add(lblbranch);
        
        String[] branch = {"Computer Science", "Electronics", "Mechanical", "Civil", "IT", "Accounts", "Management", "Arts", "Others"};
        cbbranch = new JComboBox(branch);
        cbbranch.setBounds(600, 400, 150, 30);
        cbbranch.setBackground(Color.WHITE);
        add(cbbranch);
        
        submit = new JButton("Submit");
        submit.setBounds(250, 550, 120, 30);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(450, 550, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String name = tfname.getText();
            String fname = tffname.getText();
            String empid = labelempid.getText();
            String dob = ((JTextField) dcdob.getDateEditor().getUiComponent()).getText();
            String address = tfaddress.getText();
            String phone = tfphone.getText();
            String email = tfemail.getText();
            String x = tfx.getText();
            String xii = tfxii.getText();
            String aadhar = tfaadhar.getText();
            String qualification = (String) cbcourse.getSelectedItem();
            String department= (String) cbbranch.getSelectedItem();
            
            // Validate name and father's name (only alphabets)
            if (!name.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(null, "Invalid name. Please enter only alphabets.");
                return;
            }
            
            if (!fname.matches("[a-zA-Z ]+")) {
                JOptionPane.showMessageDialog(null, "Invalid father's name. Please enter only alphabets.");
                return;
            }
            
            // Phone number validation (10 digits)
             if (!phone.matches("[6789]\\d{9}")) {
                JOptionPane.showMessageDialog(null, "Invalid phone number. Please enter a 10-digit number starting with 6, 7, 8, or 9.");
                return;
            }
            
            // Email validation
            if (!Pattern.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", email)) {
                JOptionPane.showMessageDialog(null, "Invalid email address. Please enter a valid email.");
                return;
            }
            
            // Class X and Class XII marks validation (numeric)
            if (!x.matches("\\d+(\\.\\d+)?") || !xii.matches("\\d+(\\.\\d+)?")) {
                JOptionPane.showMessageDialog(null, "Invalid marks. Please enter numeric values for Class X and Class XII marks.");
                return;
            }
            
            // Aadhar number validation (12 digits)
            if (!aadhar.matches("\\d{12}")) {
                JOptionPane.showMessageDialog(null, "Invalid Aadhar number. Please enter a 12-digit number.");
                return;
            }
            
            try {
                String query = "insert into teacher values('"+name+"', '"+fname+"', '"+empid+"', '"+dob+"', '"+address+"', '"+phone+"', '"+email+"', '"+x+"', '"+xii+"', '"+aadhar+"', '"+qualification+"', '"+department+"')";
                Conn con = new Conn();
                con.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Teacher Details Inserted Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }
     
    public static void main(String[] args) {
        new AddTeacher();
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
}
