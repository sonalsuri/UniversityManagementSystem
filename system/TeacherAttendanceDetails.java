package university.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.print.PrinterException;

public class TeacherAttendanceDetails extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;
    JComboBox<String> empidChooser;
    JButton searchButton, editButton, printButton, backButton, cancelButton, refreshButton;
    
    TeacherAttendanceDetails() {
        setSize(900, 600);
        setLocation(350, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Faculty Attendance Details");
        heading.setBounds(300, 20, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);
        
        JLabel lblSearch = new JLabel("Search by Employee Id:");
        lblSearch.setBounds(50, 70, 200, 20);
        lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblSearch);
        
        // Employee Id chooser
        empidChooser = new JComboBox<>();
        try {
            Conn c = new Conn();
            String query = "select empid from teacher";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                empidChooser.addItem(rs.getString("empid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        empidChooser.setBounds(250, 70, 150, 25);
        add(empidChooser);
        
        
        searchButton = new JButton("Search");
        searchButton.setBounds(410, 70, 100, 25);
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(this);
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(searchButton);
        
        // Table setup
        String[] columnNames = {"Employee Id", "Date", "Attendance"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(150);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 800, 300);
        add(scrollPane);
        
        editButton = new JButton("Edit");
        editButton.setBounds(150, 450, 100, 25);
        editButton.setBackground(Color.BLACK);
        editButton.setForeground(Color.WHITE);
        editButton.addActionListener(this);
        editButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(editButton);
        
        printButton = new JButton("Print");
        printButton.setBounds(270, 450, 100, 25);
        printButton.setBackground(Color.BLACK);
        printButton.setForeground(Color.WHITE);
        printButton.addActionListener(this);
        printButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(printButton);
        
        backButton = new JButton("Back");
        backButton.setBounds(390, 450, 100, 25);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(backButton);
        
        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(510, 450, 100, 25);
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.addActionListener(this);
        cancelButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancelButton);
        
        refreshButton = new JButton("Refresh");
        refreshButton.setBounds(630, 450, 100, 25);
        refreshButton.setBackground(Color.BLACK);
        refreshButton.setForeground(Color.WHITE);
        refreshButton.addActionListener(this);
        refreshButton.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(refreshButton);
        
        setVisible(true);
        fetchAttendanceData("");
    }
    
    private void fetchAttendanceData(String empId) {
        model.setRowCount(0); // Clear existing data
        try {
            Conn c = new Conn();
            String query = "select * from teacherattendance";
            if (!empId.isEmpty()) {
                query += " where empid like '%" + empId + "%'";
            }
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                String employeeid = rs.getString("empid");
                String date = rs.getString("date");
                String status = rs.getString("status");
                model.addRow(new Object[]{employeeid, date, status});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchButton) {
            String empid = (String) empidChooser.getSelectedItem();
            fetchAttendanceData(empid);
        } else if (ae.getSource() == editButton) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String empId = (String) model.getValueAt(selectedRow, 0);
                String date = (String) model.getValueAt(selectedRow, 1);
                String currentStatus = (String) model.getValueAt(selectedRow, 2);
                new T_EditAttendance(empId, date, currentStatus);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a row to edit");
            }
        } else if (ae.getSource() == printButton) {
            try {
                boolean complete = table.print();
                if (complete) {
                    JOptionPane.showMessageDialog(null, "Print successful");
                } else {
                    JOptionPane.showMessageDialog(null, "Print canceled");
                }
            } catch (PrinterException pe) {
                pe.printStackTrace();
            }
        } else if (ae.getSource() == backButton) {
            setVisible(false);
            new TeacherAttendance();
        } else if (ae.getSource() == cancelButton) {
            setVisible(false);
        } else if (ae.getSource() == refreshButton) {
            fetchAttendanceData("");
            empidChooser.setSelectedIndex(0);
        }
    }

    public static void main(String[] args) {
        new TeacherAttendanceDetails();
    }
}