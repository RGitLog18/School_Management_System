import javax.swing.*;
import java.awt.*;

class ManageTeacherRecords1 extends JFrame{
    ManageTeacherRecords1()
    {
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label=new JLabel(image);

        ImageIcon profile=new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        JLabel prof=new JLabel(profile);

        ImageIcon image1=new ImageIcon("C:/myprojects/rdclasses/managestudrec7.jpg");
        Image imageIcon2 = image1.getImage().getScaledInstance(750, 600, Image.SCALE_SMOOTH);
        image1= new ImageIcon(imageIcon2);

        JLabel bgimg=new JLabel(image1);

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        JButton type=new JButton("Student Records");
        JButton type1=new JButton("Profile");
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
        b6.setFont(f3);
        b7.setFont(f3);
        b8.setFont(f3);
        b9.setFont(f3);

        sidepanel.add(type);
        sidepanel.add(type1);

        bgimg.setBounds(250,0,700,500);
        prof.setBounds(70,30,90,90);
        b10.setBounds(10,150,220,45);
        type.setBounds(10,205,220,45);
        type1.setBounds(10,265,220,45);
        b5.setBounds(10,320,220,45);
        b11.setBounds(10,320,220,45);
        b6.setBounds(450,100,220,60);
        b7.setBounds(450,180,220,60);
        b8.setBounds(450,260,220,60);
        b9.setBounds(450,340,220,60);

        b5.setBackground(new Color(249, 249, 249));
        b10.setBackground(new Color(249, 249, 249));
        b11.setBackground(new Color(168, 7, 33));
        b11.setForeground(new Color(249, 249, 249));
        type.setBackground(new Color(249, 249, 249));
        type1.setBackground(new Color(249, 249, 249));

        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b6.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b7.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b8.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b9.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b11.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type1.setCursor(new Cursor(Cursor.HAND_CURSOR));

//        b6.setBackground(new Color(70,21,107));
//        b7.setBackground(new Color(70,21,107));
//        b8.setBackground(new Color(70,21,107));
//        b9.setBackground(new Color(70,21,107));
//
//        b6.setForeground(new Color(249,249,249));
//        b7.setForeground(new Color(249,249,249));
//        b8.setForeground(new Color(249,249,249));
//        b9.setForeground(new Color(249,249,249));


        Container c = getContentPane();
        c.setLayout(null);

//        c.add(b5);
        c.add(b6);
        c.add(b7);
        c.add(b8);
        c.add(b9);
        c.add(b10);
        c.add(b11);
        c.add(type);
        c.add(type1);
        c.add(prof);
        c.add(bgimg);
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
                    new AdmiManager1();
                    dispose();
                }
        );

        b11.addActionListener(
                a->{
                    new AdmiManaLog1();
                    dispose();
                }
        );

        b5.addActionListener(
                a->{
                    new AdmiManaLog1();
                    dispose();
                }
        );

        b6.addActionListener(
                a->
                {
                    new teachernewRecord1();
                    dispose();
                }
        );

        b7.addActionListener(
                a->
                {
                    new teacherrecords1();
                    dispose();
                }
        );

        b8.addActionListener(
                a->
                {
                    new teachereditRecord1();
                    dispose();
                }
        );

        b9.addActionListener(
                a->
                {
                    new teacherdeleterecord1();
                    dispose();
                }
        );

        b10.addActionListener(
                a->
                {
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
class ManageTeacherRecords{
    public static void main(String[] args) {
        ManageTeacherRecords1 obj =new ManageTeacherRecords1();
    }
}