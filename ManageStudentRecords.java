import javax.swing.*;
import java.awt.*;

class ManageStudentRecords1 extends JFrame{
    ManageStudentRecords1()
    {
        double balance = 0.0;
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);
        Font f3 = new Font("Calibri", Font.BOLD, 18);
        Font f4 = new Font("Calibri", Font.BOLD, 22);

        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label=new JLabel(image);

        ImageIcon profile=new ImageIcon("C:/myprojects/rdclasses/profileicon.png");
        Image imageIcon1 = profile.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        profile = new ImageIcon(imageIcon1);

        ImageIcon image1=new ImageIcon("C:/myprojects/rdclasses/managestudrec.jpg");
        Image imageIcon2 = image1.getImage().getScaledInstance(750, 600, Image.SCALE_SMOOTH);
        image1= new ImageIcon(imageIcon2);

        JLabel bgimg=new JLabel(image1);

        JLabel prof=new JLabel(profile);
        JLabel head= new JLabel("Select a Class");

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        JPanel card=new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
        card.setPreferredSize(new Dimension(480, 50));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Card Border

        JButton type=new JButton("Profile");
        JButton b1 = new JButton("New Record");
        JButton b2 = new JButton("Check Records");
        JButton b3 = new JButton("Edit Record");
        JButton b4 = new JButton("Delete Record");

        JButton type1=new JButton("Teacher Records");


        JButton b5=new JButton("Profile");
        JButton b10=new JButton("Notices");
        JButton b11=new JButton("Log Out");

        JButton fifth= new JButton("Five");
        JButton sixth= new JButton("Six");
        JButton seventh= new JButton("Seven");
        JButton eighth= new JButton("Eight");
        JButton nineth= new JButton("Nine");
        JButton tenth= new JButton("Ten");

        head.setBounds(450, 20, 350, 50);

        head.setFont(f);
        b5.setFont(f2);
        b10.setFont(f2);
        b11.setFont(f2);
        type.setFont(f2);
        type1.setFont(f2);
        sixth.setFont(f4);
        seventh.setFont(f4);
        fifth.setFont(f4);
        nineth.setFont(f4);
        tenth.setFont(f4);
        eighth.setFont(f4);
        b1.setFont(f3);
        b2.setFont(f3);
        b3.setFont(f3);
        b4.setFont(f3);

        b1.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        final String[] enable = new String[1];

        sidepanel.add(type);
        sidepanel.add(type1);

        bgimg.setBounds(250,0,700,500);
        prof.setBounds(70,30,90,90);
        b10.setBounds(10,150,220,45);
        type.setBounds(10,205,220,45);
        type1.setBounds(10,265,220,45);
        b11.setBounds(10,320,220,45);
//        b11.setBounds(10,375,220,45);

        b1.setBounds(260,390,150,40);
        b2.setBounds(430,390,150,40);
        b3.setBounds(600,390,150,40);
        b4.setBounds(770,390,150,40);

        b5.setBackground(new Color(249, 249, 249));
        b10.setBackground(new Color(249, 249, 249));
        b11.setForeground(new Color(249, 249, 249));

        type.setBackground(new Color(249, 249, 249));
        type1.setBackground(new Color(249, 249, 249));

        b1.setBackground(new Color(70, 21, 107));
        b2.setBackground(new Color(70, 21, 107));
        b3.setBackground(new Color(70, 21, 107));
        b4.setBackground(new Color(70, 21, 107));

        b1.setForeground(new Color(249, 249, 249));
        b2.setForeground(new Color(249, 249, 249));
        b3.setForeground(new Color(249, 249, 249));
        b4.setForeground(new Color(249, 249, 249));
        b11.setBackground(new Color(168, 7, 33));
        b11.setForeground(new Color(249, 249, 249));


        fifth.setBounds(320, 80, 220, 60);
        sixth.setBounds(580, 80, 220, 60);

        seventh.setBounds(320, 160, 220, 60);
        eighth.setBounds(580, 160, 220, 60);

        nineth.setBounds(320, 240, 220, 60);
        tenth.setBounds(580, 240, 220, 60);

        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fifth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sixth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        seventh.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eighth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        nineth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tenth.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b10.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b11.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type.setCursor(new Cursor(Cursor.HAND_CURSOR));
        type1.setCursor(new Cursor(Cursor.HAND_CURSOR));


        Container c = getContentPane();
        c.setLayout(null);

        c.add(head);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);
//        c.add(b5);
        c.add(b10);
        c.add(b11);
        c.add(fifth);
        c.add(sixth);
        c.add(seventh);
        c.add(eighth);
        c.add(nineth);
        c.add(tenth);
        c.add(type);
        c.add(type1);
        c.add(prof);
        c.add(card);
        c.add(bgimg);

        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.WEST);
        type.addActionListener(
                a -> {
                    new AdmiManager1();
                    dispose();
                }
        );

        type1.addActionListener(
                a->{
                    new ManageTeacherRecords1();
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
                    new AdmiManager1();
                    dispose();
                }
        );

        b1.addActionListener(
                a->
                {

                    new NewAdmission1(enable[0]);
                    dispose();
                }
        );

        b2.addActionListener(
                a->
                {
                    if(enable[0]=="Five")
                    {   new fifth1();
                        dispose();
                    }
                    else if(enable[0]=="Six")
                    {
                        new sixth1();
                        dispose();
                    }
                    else if (enable[0]=="Seven")
                    {
                        new seventh1();
                        dispose();
                    }
                    else if (enable[0]=="Eight")
                    {
                        new eighth1();
                        dispose();
                    }
                    else if (enable[0]=="Nine") {
                        new nine1();
                        dispose();
                    }
                    else if (enable[0]=="Ten"){
                        new tenth1();
                        dispose();
                    }
                }
        );

        b3.addActionListener(
                a->
                {
                    new EditRecord1(enable[0]);
                    dispose();
                }
        );

        b4.addActionListener(
                a->
                {
                    new DeleteRecord1(enable[0]);
                    dispose();
                }
        );

        fifth.addActionListener(
                a->
                {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    enable[0] ="Five";
                }
        );

        sixth.addActionListener(
                a->
                {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    enable[0] ="Six";
                }
        );

        seventh.addActionListener(
                a->
                {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    enable[0] ="Seven";
                }
        );

        eighth.addActionListener(
                a->
                {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    enable[0] ="Eight";
                }
        );

        nineth.addActionListener(
                a->
                {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    enable[0] ="Nine";
                }
        );

        tenth.addActionListener(
                a->
                {
                    b1.setEnabled(true);
                    b2.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                    enable[0] ="Ten";
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
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Admistration Manager");
    }
}
class ManageStudentRecords{
    public static void main(String[] args) {
        ManageStudentRecords1 obj =new ManageStudentRecords1();
    }
}