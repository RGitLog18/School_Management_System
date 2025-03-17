import javax.swing.*;
import java.awt.*;

class Admin1 extends JFrame{
    Admin1()
    {
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);
        Font f3 = new Font("Calibri", Font.BOLD, 22);

        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/logo1.png");
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

        JLabel Sname= new JLabel("Heera Singh");
        JLabel Classs=new JLabel("Email:");
        JLabel classs=new JLabel("h@gmail.com");
        JLabel roll=new JLabel("Salary:");
        JLabel Rollno=new JLabel("₹ 300000");
        JLabel Address=new JLabel("Address:");
        JLabel address=new JLabel("Paral");
        JLabel Contact=new JLabel("Contact:");
        JLabel contact=new JLabel("4591648822");
        JLabel Gender =new JLabel("Gender:");
        JLabel gender=new JLabel("Male");

        JButton type=new JButton("Student Records");
        JButton b1 = new JButton("New Record");
        JButton b2 = new JButton("Check Records");
        JButton b3 = new JButton("Edit Record");
        JButton b4 = new JButton("Delete Record");


        JButton type1=new JButton("Teacher Records");
        JButton b7 = new JButton("Check Records");

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
        Sname.setBounds(520,150,200,30);
        Classs.setBounds(300,200,100,30);
        classs.setBounds(420,200,150,30);
        roll.setBounds(600,200,100,30);
        Rollno.setBounds(720,200,100,30);
        Address.setBounds(300,250,100,60);
        address.setBounds(420,250,300,60);
        Contact.setBounds(300,330,100,30);
        contact.setBounds(420,330,150,30);
        Gender.setBounds(600,330,100,30);
        gender.setBounds(720,330,100,30);
        prof.setBounds(530,50,80,80);
        b10.setBounds(10,150,220,45);
        type.setBounds(10,205,220,45);
        type1.setBounds(10,265,220,45);
        b11.setBounds(10,320,220,45);





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
                    new AdminStudentRecords1();
                    dispose();
                }
        );

        type1.addActionListener(
                a->{
                    new adminTecRec1();
                    dispose();
                }
        );

        b5.addActionListener(
                a->{
                    new AdminLogin1();
                    dispose();
                }
        );

        b11.addActionListener(
                a->{
                    new AdminLogin1();
                    dispose();
                }
        );

        b10.addActionListener(
                a->{
                    new adminNotice1();
                    dispose();
                }
        );


        setVisible(true);
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Admin");
    }
}
class Admin{
    public static void main(String[] args) {
        Admin1 obj =new Admin1();
    }
}
