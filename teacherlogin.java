import javax.swing.*;
import java.awt.*;
import java.sql.*;

class teacherLogin1 extends JFrame{
    teacherLogin1 ()
    {
        Font f1=new Font("Calibri",Font.BOLD,20);
        Font f2=new Font("Calibri",Font.PLAIN,18);
        Font f3=new Font("Calibri",Font.BOLD,25);

        JLabel l1=new JLabel("Enter Name:");
        JTextField t1=new JTextField(20);

        JLabel l2=new JLabel("Enter password:");
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
//                    String s1=p1.getPassword().toString();
                    if(t1.getText().isEmpty()&&t2.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null,"Please enter details");
                    }
                    else
                    {
                        String url = "jdbc:mysql://localhost:3306/rdclasses";
                        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
                            String sql = "Select * from teacher_details where name=? and phone=? ";
                            try (PreparedStatement pst = con.prepareStatement(sql)) {
                                pst.setString(1,t1.getText());
                                pst.setInt(2, Integer.parseInt(t2.getText().toString()));
                                ResultSet rs = pst.executeQuery();

                                if (rs.next())
                                {
                                    JOptionPane.showMessageDialog(null,"Login Successfull.");
                                    new teacherprof1(t1.getText(),Integer.parseInt(t2.getText().toString()));
                                    dispose();
                                    t1.setText("");
                                    t2.setText("");

                                }
                                else {
                                    JOptionPane.showMessageDialog(null,"Please enter valid details");
                                }
                            }
                        }
                        catch(Exception e)
                        {
                            JOptionPane.showMessageDialog(null,e.getMessage());
                        }
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
        setTitle("Teacher Login");
    }
}

class teacherLogin {
    public static void main(String[] args) {
        teacherLogin1 obj=new teacherLogin1();
    }

}

