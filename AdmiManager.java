import javax.swing.*;
import java.awt.*;

class AdmiManager1 extends JFrame{
    AdmiManager1()
    {
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);
        Font f3 = new Font("Calibri", Font.BOLD, 22);

        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label=new JLabel(image);

        ImageIcon profile=new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        JLabel prof=new JLabel(profile);


        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        JLabel Sname= new JLabel();
        JLabel Classs=new JLabel("Email:");
        JLabel classs=new JLabel();
        JLabel roll=new JLabel("Salary");
        JLabel Rollno=new JLabel();
        JLabel Address=new JLabel("Address:");
        JLabel address=new JLabel();
        JLabel Contact=new JLabel("Contact :");
        JLabel contact=new JLabel();
        JLabel Gender =new JLabel("Gender");
        JLabel gender=new JLabel();

        JButton type=new JButton("Student Records");
        JButton b1 = new JButton("New Record");
        JButton b2 = new JButton("Check Records");
        JButton b3 = new JButton("Edit Record");
        JButton b4 = new JButton("Delete Record");


        JButton type1=new JButton("Teacher Records");
        JButton b6 = new JButton("New Record");
        JButton b7 = new JButton("Check Records");
        JButton b8 = new JButton("Edit Record");
        JButton b9 = new JButton("Delete Record");

        JButton b5=new JButton("Back");
        JButton b10=new JButton("Notices");
        JButton b11=new JButton("Log Out");

        b5.setFont(f2);
        b10.setFont(f2);
        b11.setFont(f2);
        type.setFont(f2);
        type1.setFont(f2);

        Sname.setFont(f3);
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

        sidepanel.add(type);
        sidepanel.add(type1);


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
        prof.setBounds(530,50,80,80);
        b10.setBounds(10,150,220,45);
        type.setBounds(10,205,220,45);
        type1.setBounds(10,265,220,45);
        b11.setBounds(10,320,220,45);
//        b11.setBounds(10,375,220,45);





        b5.setBackground(new Color(249, 249, 249));
        b10.setBackground(new Color(249, 249, 249));
        b11.setBackground(new Color(168, 7, 33));
        b11.setForeground(new Color(249, 249, 249));
        type.setBackground(new Color(249, 249, 249));
        type1.setBackground(new Color(249, 249, 249));

        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b11.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type1.setCursor(new Cursor(Cursor.HAND_CURSOR));


        Container c = getContentPane();
        c.setLayout(null);
//        c.add(b5);
        c.add(b10);
        c.add(b11);
        c.add(type);
        c.add(type1);
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

        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.WEST);

        type.addActionListener(
                a -> {
                    new ManageStudentRecords1();
                    dispose();
                }
        );

        type1.addActionListener(
                a->{
                    new ManageTeacherRecords1();
                    dispose();
                }
        );

        b5.addActionListener(
                a->{
                    new AdmiManaLog1();
                    dispose();
                }
        );

        b11.addActionListener(
                a->{
                    new AdmiManaLog1();
                    dispose();
                }
        );

        b10.addActionListener(
                a->{
                    new ManageNotice1();
                    dispose();
                }
        );


        setVisible(true);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Admistration Manager");
    }
}
class AdmiManager{
    public static void main(String[] args) {
        AdmiManager1 obj =new AdmiManager1();
    }
}