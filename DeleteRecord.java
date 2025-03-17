import javax.swing.*;
import java.awt.*;
import java.sql.*;

class DeleteRecord1 extends JFrame{
    DeleteRecord1(String Class)
    {
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.BOLD, 20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel head=new JLabel("Delete Record" ,JLabel.CENTER);
        head.setFont(f);

        JLabel l1 = new JLabel("Student Name:");
        JTextField t1 = new JTextField(50);

        JLabel l2 = new JLabel("Class:");
        JTextField t2 = new JTextField(Class);

        JLabel l4 = new JLabel("Phone:");
        JTextField t4 = new JTextField(15);

        JButton b1 = new JButton("Delete");
        JButton b2 = new JButton("Back");

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        b1.setBackground(new Color(70,21,102));
        b2.setBackground(new Color(70,21,102));

        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));


        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);

        l4.setFont(f2);
        t4.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 100, fieldX = 300, yStart = 80, width = 150, height = 50, gap = 60;

       head.setBounds(150, 10, 400, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart + gap, width, height);
        t2.setBounds(fieldX, yStart + gap, width, height);



        l4.setBounds(labelX, yStart + 2 * gap, width, height);
        t4.setBounds(fieldX, yStart + 2 * gap, width, height);

//        l5.setBounds(labelX, yStart + 4 * gap, width, height);
//        t5.setBounds(fieldX, yStart + 4 * gap, width, height);


        b1.setBounds(150, yStart + 4 * gap, 120, 40);
        b2.setBounds(300, yStart + 4 * gap, 120, 40);

        b1.addActionListener(
                a->{
                    String url="jdbc:mysql://localhost:3306/rdclasses";
                    try(Connection con=DriverManager.getConnection(url,"root","R1a2j3#*"))
                    {

                        String sql="delete from "+t2.getText().toString()+"th_std where phone=?";
                        try(PreparedStatement pst=con.prepareStatement(sql))
                        {
                            pst.setString(1,t4.getText());


                            pst.execute();
                            t1.setText("");
                            t4.setText("");
                            JOptionPane.showMessageDialog(null,"Deletion Successfull");
                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                        e.printStackTrace();
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new ManageStudentRecords1();
                    dispose();
                }
        );

        c.add(head);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(l4);
        c.add(t4);
        c.add(b1);
        c.add(b2);
        c.setLayout(new BorderLayout(20, 20));
        c.add(sidepanel,BorderLayout.EAST);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setSize(800,550);
        setLocationRelativeTo(null);

        setTitle("Delete Record");
    }
}
class DeleteRecord{
    public static void main(String[] args) {
       DeleteRecord1 obj=new DeleteRecord1("Five");
    }
}

