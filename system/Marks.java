package university.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.awt.print.*;

public class Marks extends JFrame implements ActionListener, Printable {

    String rollno;
    JButton cancel, print;

    Marks(String rollno) {
        this.rollno = rollno;

        setSize(500, 600);
        setLocation(500, 100);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("          AKU Univeristy");
        heading.setBounds(100, 10, 500, 25);
        heading.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(heading);

        JLabel subheading = new JLabel("Result of Examination 2022");
        subheading.setBounds(100, 50, 500, 20);
        subheading.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(subheading);

        JLabel lblrollno = new JLabel("Roll Number " + rollno);
        lblrollno.setBounds(60, 100, 500, 20);
        lblrollno.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblrollno);

        JLabel lblsemester = new JLabel();
        lblsemester.setBounds(60, 130, 500, 20);
        lblsemester.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(lblsemester);

        JLabel sub1 = new JLabel();
        sub1.setBounds(100, 200, 500, 20);
        sub1.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub1);

        JLabel sub2 = new JLabel();
        sub2.setBounds(100, 230, 500, 20);
        sub2.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub2);

        JLabel sub3 = new JLabel();
        sub3.setBounds(100, 260, 500, 20);
        sub3.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub3);

        JLabel sub4 = new JLabel();
        sub4.setBounds(100, 290, 500, 20);
        sub4.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub4);

        JLabel sub5 = new JLabel();
        sub5.setBounds(100, 320, 500, 20);
        sub5.setFont(new Font("Tahoma", Font.PLAIN, 18));
        add(sub5);

        JLabel totalMarks = new JLabel();
        totalMarks.setBounds(100, 350, 500, 20);
        totalMarks.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(totalMarks);

        JLabel result = new JLabel();
        result.setBounds(100, 380, 500, 20);
        result.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(result);

        try {
            Conn c = new Conn();

            ResultSet rs1 = c.s.executeQuery("select * from subject where rollno = '" + rollno + "'");
            while (rs1.next()) {
                sub1.setText(rs1.getString("subject1"));
                sub2.setText(rs1.getString("subject2"));
                sub3.setText(rs1.getString("subject3"));
                sub4.setText(rs1.getString("subject4"));
                sub5.setText(rs1.getString("subject5"));
            }

            ResultSet rs2 = c.s.executeQuery("select * from marks where rollno = '" + rollno + "'");
            while (rs2.next()) {
                sub1.setText(sub1.getText() + "------------" + rs2.getString("marks1"));
                sub2.setText(sub2.getText() + "------------" + rs2.getString("marks2"));
                sub3.setText(sub3.getText() + "------------" + rs2.getString("marks3"));
                sub4.setText(sub4.getText() + "------------" + rs2.getString("marks4"));
                sub5.setText(sub5.getText() + "------------" + rs2.getString("marks5"));
                lblsemester.setText("Semester " + rs2.getString("semester"));

                int marks1 = Integer.parseInt(rs2.getString("marks1"));
                int marks2 = Integer.parseInt(rs2.getString("marks2"));
                int marks3 = Integer.parseInt(rs2.getString("marks3"));
                int marks4 = Integer.parseInt(rs2.getString("marks4"));
                int marks5 = Integer.parseInt(rs2.getString("marks5"));

                int total = marks1 + marks2 + marks3 + marks4 + marks5;
                totalMarks.setText("Total Marks: " + total + "/500");

                if (total >= 300) {
                    result.setText("Result: Pass");
                    if (total >= 400) {
                        result.setText(result.getText() + " (Grade: A)");
                    } else if (total >= 375) {
                        result.setText(result.getText() + " (Grade: B)");
                    } else if (total >= 350) {
                        result.setText(result.getText() + " (Grade: C)");
                    }
                } else {
                    result.setText("Result: Fail");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        cancel = new JButton("Back");
        cancel.setBounds(150, 500, 120, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        cancel.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(cancel);

        print = new JButton("Print");
        print.setBounds(300, 500, 120, 25);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        print.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(print);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cancel) {
            setVisible(false);
        } else if (ae.getSource() == print) {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean ok = job.printDialog();
            if (ok) {
                try {
                    job.print();
                } catch (PrinterException ex) {
                    /* The job did not successfully complete */
                }
            }
        }
    }

    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        printAll(g);

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {
        new Marks("");
    }
}