import javax.swing.*;
import java.awt.*;

class AdminStudentRecords1 extends JFrame{
    AdminStudentRecords1(){
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

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        JPanel card=new JPanel(new FlowLayout(FlowLayout.LEFT,20,10));
        card.setPreferredSize(new Dimension(480, 50));
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Card Border

        JButton type=new JButton("Profile");
        JButton b2 = new JButton("Check Records");

        JButton type1=new JButton("Teacher Records");


        JButton b5=new JButton("Profile");
        JButton b10=new JButton("Notices");
        JButton b11=new JButton("Log Out");

        JButton one= new JButton("First");
        JButton two= new JButton("Second");
        JButton three= new JButton("Third");
        JButton fourth= new JButton("Fourth");
        JButton fifth= new JButton("Five");
        JButton sixth= new JButton("Six");
        JButton seventh= new JButton("Seven");
        JButton eighth= new JButton("Eight");
        JButton nineth= new JButton("Nine");
        JButton tenth= new JButton("Ten");

        b5.setFont(f2);
        b10.setFont(f2);
        b11.setFont(f2);
        type.setFont(f2);
        type1.setFont(f2);
        sixth.setFont(f4);
        seventh.setFont(f4);
        one.setFont(f4);
        two.setFont(f4);
        three.setFont(f4);
        fourth.setFont(f4);
        fifth.setFont(f4);
        nineth.setFont(f4);
        tenth.setFont(f4);
        eighth.setFont(f4);
        b2.setFont(f3);
        b2.setEnabled(false);

        final String[] enable = new String[1];

        sidepanel.add(type);
        sidepanel.add(type1);

        bgimg.setBounds(250,0,700,500);
        prof.setBounds(70,30,90,90);
        b10.setBounds(10,150,220,45);
        type.setBounds(10,205,220,45);
        type1.setBounds(10,265,220,45);
        b11.setBounds(10,320,220,45);

        b2.setBounds(490,390,150,40);

        one.setBounds(320,10,220,60);
        two.setBounds(580,10,220,60);
        three.setBounds(320, 80, 220, 60);
        fourth.setBounds(580, 80, 220, 60);
        fifth.setBounds(320, 160, 220, 60);
        sixth.setBounds(580, 160, 220, 60);

        seventh.setBounds(320, 240, 220, 60);
        eighth.setBounds(580, 240, 220, 60);

        nineth.setBounds(320, 320, 220, 60);
        tenth.setBounds(580, 320, 220, 60);

        b5.setBackground(new Color(249, 249, 249));
        b10.setBackground(new Color(249, 249, 249));
        b11.setForeground(new Color(249, 249, 249));

        type.setBackground(new Color(249, 249, 249));
        type1.setBackground(new Color(249, 249, 249));

        b2.setBackground(new Color(70, 21, 107));
        b2.setForeground(new Color(249, 249, 249));
        b11.setBackground(new Color(168, 7, 33));
        b11.setForeground(new Color(249, 249, 249));

        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        one.setCursor(new Cursor(Cursor.HAND_CURSOR));
        two.setCursor(new Cursor(Cursor.HAND_CURSOR));
        three.setCursor(new Cursor(Cursor.HAND_CURSOR));
        fourth.setCursor(new Cursor(Cursor.HAND_CURSOR));
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

        c.add(b2);
        c.add(b10);
        c.add(b11);
        c.add(one);
        c.add(two);
        c.add(three);
        c.add(fourth);
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
                    new Admin1();
                    dispose();
                }
        );

        type1.addActionListener(
                a->{
                    new adminTecRec1();
                    dispose();
                }
        );

        b11.addActionListener(
                a->{
                    new AdminLogin1();
                    dispose();
                }
        );

        b5.addActionListener(
                a->{
                    new AdminLogin1();
                    dispose();
                }
        );

        b2.addActionListener(
                a->
                {
                    if(enable[0]=="One")
                    {   new One1();

                    }

                    else if(enable[0]=="Two")
                    {
                        new Two1();

                    }
                    else if (enable[0]=="Three")
                    {
                        new Three1();

                    }
                    else if (enable[0]=="Four")
                    {
                        new Four1();

                    }
                    else if(enable[0]=="Five")
                    {   new Fifth1();

                    }

                    else if(enable[0]=="Six")
                    {
                        new Six1();

                    }
                    else if (enable[0]=="Seven")
                    {
                        new Seventh1();

                    }
                    else if (enable[0]=="Eight")
                    {
                        new Eight1();

                    }
                    else if (enable[0]=="Nine") {
                        new Nine1();

                    }
                    else if (enable[0]=="Ten"){
                        new Tenth1();

                    }
                }
        );

        one.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="One";
                }
        );

        two.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Two";
                }
        );

        three.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Three";
                }
        );

        fourth.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Four";
                }
        );

        fifth.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Five";
                }
        );

        sixth.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Six";
                }
        );

        seventh.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Seven";
                }
        );

        eighth.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Eight";
                }
        );

        nineth.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Nine";
                }
        );

        tenth.addActionListener(
                a->
                {
                    b2.setEnabled(true);
                    enable[0] ="Ten";
                }
        );

        b10.addActionListener(
                a->
                {
                    new adminNotice1();
                    dispose();
                }
        );

        setVisible(true);
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Admin");
    }
}

class AdminStudentRecords{
    public static void main(String[] args) {
        AdminStudentRecords1 obj =new AdminStudentRecords1();
    }
}

