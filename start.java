import javax.swing.*;
import java.awt.*;
import java.sql.*;

class start1 extends JFrame{
    start1()
    {
        Font l=new Font("Comic Sans",Font.ITALIC ^ Font.BOLD,45);
        Font b=new Font("Calibri",Font.BOLD,20);
        JLabel l1=new JLabel("Welcome to Nova Academy.");
        l1.setFont(l);

        JLabel tag=new JLabel("Grow and learn together!");

        JLabel address=new JLabel("XYZ,ABC,ADC,WER,TU-16");
        JLabel contactdet=new JLabel("Contact Us|About Us|Career|Privay Policy");
        JLabel cpyrt=new JLabel("Copyright RDClasses.All Rights Reserved.");

        ImageIcon image1=new ImageIcon("C:/myprojects/rdclasses/welcomepg.jpg");
        Image imageIcon1 = image1.getImage().getScaledInstance(750, 600, Image.SCALE_SMOOTH);
        image1= new ImageIcon(imageIcon1);


        ImageIcon image=new ImageIcon("C:/myprojects/rdclasses/rdlogo.jpg");
        Image imageIcon = image.getImage().getScaledInstance(90, 170, Image.SCALE_SMOOTH);
        image = new ImageIcon(imageIcon);

        JLabel label=new JLabel(image);


        JLabel side=new JLabel("Login-in as:");
        side.setFont(b);
        side.setForeground(new Color(249,249,249));
        JButton b1=new JButton("Admin");
        JButton b2=new JButton("Manager");
        JButton b3=new JButton("Student");
        JButton b4=new JButton("Teacher");
        JButton report=new JButton("Report");


        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));



//        GridBagConstraints gbc=new GridBagConstraints();
//        gbc.gridx=0;
//        gbc.gridy=GridBagConstraints.RELATIVE;//place buttns one below other
//        gbc.anchor=GridBagConstraints.CENTER;//center alignment



        b1.setAlignmentX(Component.CENTER_ALIGNMENT);
        b2.setAlignmentX(Component.CENTER_ALIGNMENT);
        b3.setAlignmentX(Component.CENTER_ALIGNMENT);
        b4.setAlignmentX(Component.CENTER_ALIGNMENT);
        side.setAlignmentX(Component.CENTER_ALIGNMENT);

        address.setAlignmentX(Component.CENTER_ALIGNMENT);
        cpyrt.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactdet.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidepanel.add(address);
        sidepanel.add(contactdet);
        sidepanel.add(cpyrt);
        sidepanel.add(side);
        sidepanel.add(b1);
        sidepanel.add(b2);
        sidepanel.add(b3);
        sidepanel.add(b4);
        sidepanel.add(label);

        sidepanel.add(address);
        sidepanel.add(contactdet);
        sidepanel.add(cpyrt);

        JPanel together=new JPanel();
        together.setPreferredSize(new Dimension(1000,600));
        together.add(new JLabel(image1));
            JLabel bgimg=new JLabel(image1);
        together.add(l1);
        together.add(address);
        together.add(tag);
        together.add(contactdet);
        together.add(cpyrt);
        together.add(b1);
        together.add(b2);
        together.add(b3);
        together.add(b4);

        b1.setFont(b);
        b2.setFont(b);
        b3.setFont(b);
        b4.setFont(b);
        tag.setFont(b);

        b1.setBackground(new Color(249, 249, 249));
        b2.setBackground(new Color(249, 249, 249));
        b3.setBackground(new Color(249, 249, 249));
        b4.setBackground(new Color(249, 249, 249));
//        label.setBounds(650,30,50,50);

//        tag.setForeground(new Color(249,249,249));
        address.setForeground(new Color(249,249,249));
        cpyrt.setForeground(new Color(249,249,249));
        contactdet.setForeground(new Color(249,249,249));

        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));


        l1.setBounds(310,30,600,70);

        side.setBounds(40,100,220,50);

        b1.setBounds(10,150,220,50);
        b2.setBounds(10,220,220,50);
        b3.setBounds(10,290,220,50);
        b4.setBounds(10,360,220,50);
        report.setBounds(770,10,150,30);
        bgimg.setBounds(250,0,750,600);
        label.setBounds(70,30,90,50);
        tag.setBounds(520,90,250,30);
        address.setBounds(50,460,250,10);
        contactdet.setBounds(5,480,250,10);
        cpyrt.setBounds(5,500,250,15);
        together.setBounds(0,0,750,600);
//        footer.setBounds(100,500,800,100);

        b1.addActionListener(
                a->{
                    new AdminLogin1();
                    dispose();
                }
        );

        b2.addActionListener(
                a->{
                  new AdmiManaLog1();
                  dispose();
                }
        );

        b3.addActionListener(
                a->{
                    new StudentLogin1();
                    dispose();
                }
        );

        b4.addActionListener(
                a->
                {
                    new teacherLogin1();
                    dispose();
                }
        );



        Container c=getContentPane();
        c.setLayout(null);
        c.add(l1);
        c.add(side);
        c.add(b1);
        c.add(b2);
        c.add(b3);
        c.add(b4);
        c.add(tag);
        c.add(bgimg);
        c.add(label);

        c.add(contactdet);
        c.add(address);
        c.add(cpyrt);

        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.WEST);

//        c.add(footer);
//        c.add(together);

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(1000,600);
        setTitle("Start");
    }
}
class start{
    public static void main(String[] args) {
    start1 obj=new start1();
    }
}
