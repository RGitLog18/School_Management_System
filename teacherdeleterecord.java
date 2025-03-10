import javax.swing.*;
import java.awt.*;
import java.sql.*;

class teacherdeleterecord1 extends JFrame{
    teacherdeleterecord1()
    {
        Font f = new Font("Futura", Font.BOLD, 30);
        Font f2 = new Font("Calibri", Font.BOLD, 20);
        Font f3 = new Font("Calibri", Font.BOLD, 25);

        JLabel l1 = new JLabel("Teacher Name");
        JTextField t1 = new JTextField(50);

        JLabel l2=new JLabel("Phone:");
        JTextField t2 = new JTextField(50);

        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        JLabel title = new JLabel("Delete Teacher Record", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));

        JPanel sidepanel=new JPanel();
        sidepanel.setPreferredSize(new Dimension(250,0));///height auto adjust
        sidepanel.setBackground(new Color(70, 21, 107));
        sidepanel.setLayout(new BoxLayout(sidepanel,BoxLayout.Y_AXIS));

        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        b1.setFont(f3);
        b2.setFont(f3);

        Container c = getContentPane();
        c.setLayout(null);

        int labelX = 120, fieldX = 270, yStart = 120, width = 150, height = 30, gap = 40;

        title.setBounds(100, 30, 350, 40);

        l1.setBounds(labelX, yStart, width, height);
        t1.setBounds(fieldX, yStart, width, height);

        l2.setBounds(labelX, yStart+gap, width, height);
        t2.setBounds(fieldX, yStart+gap, width, height);

        b1.setBounds(150, yStart + 4 * gap, 120, 40);
        b2.setBounds(300, yStart + 4 * gap, 120, 40);

        b1.setForeground(new Color(249,249,249));
        b2.setForeground(new Color(249,249,249));

        b1.setBackground(new Color(70,21,107));
        b2.setBackground(new Color(70,21,107));

        b1.addActionListener(
                a->{
                    String url="jdbc:mysql://localhost:3306/rdclasses";
                    try(Connection con=DriverManager.getConnection(url,"root","R1a2j3#*"))
                    {

                        String sql="delete from teacher_details where name=? and phone=?";
                        try(PreparedStatement pst=con.prepareStatement(sql))
                        {
                            pst.setString(1,t1.getText());
                            pst.setString(2,t2.getText());

                            pst.executeUpdate();
                            JOptionPane.showMessageDialog(null,"Deleted Successfully.");

                        }
                    }
                    catch(Exception e)
                    {
                        JOptionPane.showMessageDialog(null,e.getMessage());
                        e.getMessage();
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new ManageTeacherRecords1();
                    dispose();
                }
        );

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(b1);
        c.add(b2);
        c.setLayout(new BorderLayout(10, 10));
        c.add(sidepanel,BorderLayout.EAST);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(800,500);
        setTitle("New Admission");
    }
}

class teacherdeleterecord{
    public static void main(String[] args) {
        teacherdeleterecord1 obj=new teacherdeleterecord1();
    }
}


