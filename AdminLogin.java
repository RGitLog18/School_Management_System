import javax.swing.*;
import java.awt.*;
import java.sql.*;

class AdminLogin1 extends JFrame{
    AdminLogin1 ()
    {
        Font f1=new Font("Calibri",Font.BOLD,20);
        Font f2=new Font("Calibri",Font.PLAIN,20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel l1=new JLabel("Enter Username:");
        JTextField t1=new JTextField(20);

        JLabel l2=new JLabel("Enter Password:");
        JPasswordField t2=new JPasswordField(20);

        JButton b1=new JButton("Back");
        JButton b2=new JButton("Submit");

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        l1.setBounds(100,40,350, 50);
        t1.setBounds(100,90,250,50);
        l2.setBounds(100,140,250,50);
        t2.setBounds(100,190,250,50);
        b2.setBounds(70,280,150,50);
        b1.setBounds(230,280,150,50);


        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));
        b1.setBackground(new Color(70,21,102));
        b2.setBackground(new Color(70,21,102));

        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        l1.setFont(f1);
        t1.setFont(f2);
        l2.setFont(f1);
        b1.setFont(f3);
        b2.setFont(f3);
        t2.setFont(f2);

        b1.addActionListener(a->{
            new start1();
            dispose();

        });

        b2.addActionListener(
                a->{
                    if(t1.getText().isEmpty()&&t2.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"Please enter username");
                    }
                    else if (t1.getText().equals("Admin")&&t2.getText().toString().equals("1234"))
                    {
                        new Admin1();
                        dispose();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Please enter valid details");
                    }

                }
        );





        Container c=getContentPane();
        c.setLayout(null);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(b1);
        c.add(b2);
        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(700,500);
        setLocationRelativeTo(null);
        setTitle("Admin Login");
    }
}

class AdminLogin {
    public static void main(String[] args) {
        AdminLogin1 obj=new AdminLogin1();
    }

}

