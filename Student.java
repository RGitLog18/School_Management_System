import javax.swing.*;
import java.sql.*;
import java.awt.*;

class student1 extends JFrame{
    student1(String name,String Class,int phone)
    {
        Font f1=new Font("Calibri",Font.BOLD,20);
        Font f2=new Font("Calibri",Font.PLAIN,20);
        Font f3=new Font("Calibri",Font.BOLD,25);

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label=new JLabel(image);

        ImageIcon profile=new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        JLabel prof=new JLabel(profile);


        JLabel Sname= new JLabel();
        JLabel Classs=new JLabel("Class:");
        JLabel classs=new JLabel(Class);
        JLabel roll=new JLabel("Roll no:");
        JLabel Rollno=new JLabel();
        JLabel Address=new JLabel("Address:");
        JLabel address=new JLabel();
        JLabel Contact=new JLabel("Contact :");
        JLabel contact=new JLabel();
        JLabel Gender =new JLabel("Gender");
        JLabel gender=new JLabel();

        JButton b1=new JButton("Assignments");
        JButton b2=new JButton("Notices");
        JButton b3=new JButton("Fee Details");
        JButton b4=new JButton("Log Out");

        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);

        Sname.setFont(f1);
        Classs.setFont(f2);
        classs.setFont(f2);
        roll.setFont(f2);
        Rollno.setFont(f2);
        Address.setFont(f2);
        address.setFont(f2);
        Contact.setFont(f2);
        contact.setFont(f2);
        Gender.setFont(f2);
        gender.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);
        b3.setFont(f3);
        b4.setFont(f3);

        b1.setBackground(new Color(249, 249, 249));
        b2.setBackground(new Color(249, 249, 249));
        b3.setBackground(new Color(249, 249, 249));
        b4.setForeground(new Color(249, 249, 249));
        b4.setBackground(new Color(201, 19, 19));


        label.setBounds(70,30,90,90);

        prof.setBounds(530,50,80,80);

        Sname.setBounds(550,150,100,30);

        Classs.setBounds(300,200,80,30);
        classs.setBounds(400,200,80,30);

        roll.setBounds(560,200,80,30);
        Rollno.setBounds(660,200,30,30);

        Address.setBounds(300,250,80,60);
        address.setBounds(400,250,80,60);

        Contact.setBounds(300,330,80,30);
        contact.setBounds(400,330,80,30);

        Gender.setBounds(560,330,80,30);
        gender.setBounds(660,330,80,30);

        b1.setBounds(10,150,220,50);
        b2.setBounds(10,220,220,50);
        b3.setBounds(10,290,220,50);
        b4.setBounds(10,360,220,50);

        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        b1.addActionListener(
                a->{
                    new StudentAssignment(name,Class,phone);
                    dispose();
                }
        );

        b2.addActionListener(
                a->{
                    new student_notice1(name,Class,phone);
                    dispose();

                    Font f11 = new Font("Calibri", Font.BOLD, 20);
                    Font f21 = new Font("Calibri", Font.PLAIN, 20);
                    Font f31 = new Font("Calibri", Font.ITALIC, 20);

                }
        );

        b3.addActionListener(
                a->{
                    new fee_details1(name,Class,phone);
                    dispose();
                }
        );

        b4.addActionListener(
                a->
                {
                    new StudentLogin1();
                    dispose();
                }
        );

        Container c=getContentPane();
        c.setBackground(new Color(249,249,249));
        c.setLayout(null);
        c.add(label);
        c.add(prof);
        c.add(Sname);
        c.add(Classs);
        c.add(classs);
        c.add(roll);
        c.add(Rollno);
        c.add(Address);
        c.add(address);
        c.add(Gender);
        c.add(gender);
        c.add(Contact);
        c.add(contact);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);
        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.WEST);


        String url = "jdbc:mysql://localhost:3306/rdclasses";
        try (Connection con = DriverManager.getConnection(url, "root", "R1a2j3#*")) {
            String sql = "Select * from " + Class + "th_std where name=? and phone=? ";
            try (PreparedStatement pst = con.prepareStatement(sql)) {
                pst.setString(1,name);
                pst.setInt(2, phone);
                ResultSet rs = pst.executeQuery();

                if (rs.next())
                {
                    Sname.setText(rs.getString("name"));
                    contact.setText(rs.getString("phone"));
                    address.setText(rs.getString("address"));
                    gender.setText(rs.getString("gender"));
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

//        setLayout(null);
        setSize(900,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Student Profile");
        setLocationRelativeTo(null);

    }
}

class Student {
    public static void main(String[] args) {
        student1 obj=new student1("Rajshree","Eight",12345);
    }
}
