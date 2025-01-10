package university.management.system;

import javax.swing.*;
import java.awt.*;

public class About extends JFrame {

    About() {
        setSize(800, 700);
        setLocation(500, 100);
        getContentPane().setBackground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 350, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(100, 110, 600, 350);
        add(image);
        
        JLabel heading = new JLabel("University Management System");
        heading.setBounds(120, 0, 700, 150);
        heading.setFont(new Font("Tahoma", Font.BOLD, 35));
        add(heading);
        
        JLabel name = new JLabel(" Sonal Kumari ");
        name.setBounds(260, 470, 550, 40);
        name.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(name);
        
        JLabel clgId = new JLabel("College ID: 15333");
        clgId.setBounds(260, 510, 550, 40);
        clgId.setFont(new Font("Tahoma", Font.PLAIN, 30));
        add(clgId);
        
        JLabel Roll = new JLabel("Roll No: A-22 (Batch 1)");
        Roll.setBounds(270, 550, 550, 40);
        Roll.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(Roll);
        
        JLabel course = new JLabel("Course: BCA-AKU 6TH SEM ");
        course.setBounds(260, 585, 550, 40);
        course.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(course);
        
        JLabel Reg = new JLabel("REGISTRATION NO.: 21303310116 ");
        Reg.setBounds(220, 620, 550, 40);
        Reg.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(Reg);
        
        setLayout(null);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new About();
    }
}
