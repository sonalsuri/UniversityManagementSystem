package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Demo extends JFrame implements Runnable {

    Thread t;
    int count = 0;
    String[] images = {"icons/pic2.jpg","icons/p.jpg","icons/pic.jpg","icons/pic3.jpeg","icons/pic1.jpg","icons/pic4.jpg","icons/proj.jpeg","icons/third.jpg","icons/pic5.jpg" }; // add your image paths here
    JLabel imageLabel;
    JButton loginButton;

    Demo() {
        setUndecorated(true);
        setLayout(new BorderLayout());

        imageLabel = new JLabel();
        add(imageLabel, BorderLayout.CENTER);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                Login login = new Login();
                login.setVisible(true);
            }
        });
        add(loginButton, BorderLayout.SOUTH);

        t = new Thread(this);
        t.start();

        setSize(1200, 700); // set the size of the splash screen
        setLocationRelativeTo(null); // center the splash screen
        setVisible(true);
    }

    public void run() {
        while (true) {
            try {
                for (int i = 0; i < images.length; i++) {
                    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource(images[i]));
                    Image i2 = i1.getImage().getScaledInstance(1200, 800, Image.SCALE_DEFAULT);
                    ImageIcon i3 = new ImageIcon(i2);
                    imageLabel.setIcon(i3);

                    Thread.sleep(400); // change the sleep time to adjust the duration of each image
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Demo();
    }
}