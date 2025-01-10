package university.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.ButtonGroup;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class StudentAttendance extends JFrame implements ActionListener {

    JTable table;
    DefaultTableModel model;
    JDateChooser dcdate;
    JButton submit, cancel;
    ArrayList<String> studentRollNos;

    StudentAttendance() {
        setSize(900, 600);
        setLocation(350, 50);
        setLayout(null);
        
        getContentPane().setBackground(Color.WHITE);
        
        JLabel heading = new JLabel("Student Attendance");
        heading.setBounds(300, 20, 300, 30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        add(heading);
        
        JLabel lbldate = new JLabel("Date:");
        lbldate.setBounds(50, 70, 100, 20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lbldate);
        
        dcdate = new JDateChooser();
        dcdate.setBounds(150, 70, 200, 25);
        add(dcdate);

        // Table setup
        String[] columnNames = {"Serial No.", "Roll Number", "Student Name", "Attendance"};
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model) {
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Only allow editing on the "Attendance" column
            }
        };
        table.getColumnModel().getColumn(3).setCellEditor(new RadioButtonEditor());
        table.getColumnModel().getColumn(3).setCellRenderer(new RadioButtonRenderer());
        table.setRowHeight(30);
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(150);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 120, 800, 300);
        add(scrollPane);

        // Fetch and add student data to table
        studentRollNos = new ArrayList<>();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from student");
            int serialNo = 1;
            while (rs.next()) {
                String rollNo = rs.getString("rollno");
                String name = rs.getString("name");
                studentRollNos.add(rollNo);
                model.addRow(new Object[]{serialNo++, rollNo, name, "Present"});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        submit = new JButton("Submit");
        submit.setBounds(200, 450, 100, 25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(submit);
        
        cancel = new JButton("Cancel");
        cancel.setBounds(350, 450, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();
            
            try {
                Conn c = new Conn();
                for (int i = 0; i < model.getRowCount(); i++) {
                    String rollno = (String) model.getValueAt(i, 1);
                    String status = (String) model.getValueAt(i, 3);
                    
                    String query = "insert into studentattendance (rollno, date, status) values('"+rollno+"', '"+date+"', '"+status+"')";
                    c.s.executeUpdate(query);
                }
                JOptionPane.showMessageDialog(null, "Attendance submitted successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentAttendance();
    }
    
    // Custom renderer for JRadioButton in JTable
    class RadioButtonRenderer implements TableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JPanel panel = new JPanel();
            JRadioButton presentButton = new JRadioButton("Present");
            JRadioButton absentButton = new JRadioButton("Absent");
            ButtonGroup group = new ButtonGroup();
            group.add(presentButton);
            group.add(absentButton);
            panel.add(presentButton);
            panel.add(absentButton);
            if (value.equals("Present")) {
                presentButton.setSelected(true);
            } else {
                absentButton.setSelected(true);
            }
            return panel;
        }
    }

    // Custom editor for JRadioButton in JTable
    class RadioButtonEditor extends AbstractCellEditor implements TableCellEditor {
        JPanel panel;
        JRadioButton presentButton;
        JRadioButton absentButton;
        ButtonGroup group;

        public RadioButtonEditor() {
            panel = new JPanel();
            presentButton = new JRadioButton("Present");
            absentButton = new JRadioButton("Absent");
            group = new ButtonGroup();
            group.add(presentButton);
            group.add(absentButton);
            panel.add(presentButton);
            panel.add(absentButton);

            presentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    stopCellEditing();
                }
            });

            absentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    stopCellEditing();
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (value.equals("Present")) {
                presentButton.setSelected(true);
            } else {
                absentButton.setSelected(true);
            }
            return panel;
        }

        public Object getCellEditorValue() {
            if (presentButton.isSelected()) {
                return "Present";
            } else {
                return "Absent";
            }
        }
    }
}