package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EditAttendance extends JFrame implements ActionListener {

    JLabel lblRollNo, lblName, lblStatus;
    JRadioButton presentButton, absentButton;
    JButton saveButton, cancelButton;
    ButtonGroup group;
    String rollNo, date, currentStatus;

    EditAttendance(String rollNo, String date, String currentStatus) {
        this.rollNo = rollNo;
        this.date = date;
        this.currentStatus = currentStatus;

        setSize(400, 300);
        setLocation(500, 250);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Edit Attendance");
        heading.setBounds(100, 20, 200, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);

        lblRollNo = new JLabel("Roll Number: " + rollNo);
        lblRollNo.setBounds(50, 70, 300, 30);
        lblRollNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblRollNo);

        lblName = new JLabel("Name: " + getStudentName(rollNo));
        lblName.setBounds(50, 110, 300, 30);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblName);

        lblStatus = new JLabel("Attendance Status:");
        lblStatus.setBounds(50, 150, 200, 30);
        lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblStatus);

        presentButton = new JRadioButton("Present");
        presentButton.setBounds(210, 150, 100, 30);
        add(presentButton);

        absentButton = new JRadioButton("Absent");
        absentButton.setBounds(210, 180, 100, 30);
        add(absentButton);

        group = new ButtonGroup();
        group.add(presentButton);
        group.add(absentButton);

        if (currentStatus.equals("Present")) {
            presentButton.setSelected(true);
        } else {
            absentButton.setSelected(true);
        }

        saveButton = new JButton("Save");
        saveButton.setBounds(70, 230, 100, 25);
        saveButton.setBackground(Color.BLACK);
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(this);
        saveButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(saveButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 230, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancelButton);

        setVisible(true);
    }

    private String getStudentName(String rollNo) {
        try {
            Conn c = new Conn();
            String query = "select name from student where rollno = '" + rollNo + "'";
            ResultSet rs = c.s.executeQuery(query);
            if (rs.next()) {
                return rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == saveButton) {
            String status = presentButton.isSelected() ? "Present" : "Absent";
            try {
                Conn c = new Conn();
                String query = "update studentattendance set status = '" + status + "' where rollno = '" + rollNo + "' and date = '" + date + "'";
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Attendance Updated Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new EditAttendance("12345", "2023-07-07", "Absent"); // Example data
    }
}
